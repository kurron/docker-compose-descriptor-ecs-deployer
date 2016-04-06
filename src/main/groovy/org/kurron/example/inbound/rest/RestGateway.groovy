/*
 * Copyright (c) 2016. Ronald D. Kurr kurr@jvmguy.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kurron.example.inbound.rest

import java.time.Instant
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid
import org.kurron.example.MessagingContext
import org.kurron.example.core.DescriptorPublisher
import org.kurron.example.core.PublisherCommand
import org.kurron.feedback.AbstractFeedbackAware
import org.kurron.stereotype.InboundRestGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.ExposesResourceFor
import org.springframework.hateoas.mvc.ControllerLinkBuilder
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.HandlerMapping

/**
 * Inbound HTTP gateway that supports the descriptor resource.
 **/
@SuppressWarnings( 'GroovyUnusedDeclaration' )
@InboundRestGateway
@RequestMapping( path = '/descriptor' )
@ExposesResourceFor( HypermediaControl )
class RestGateway extends AbstractFeedbackAware {

    /**
     * Knows how to get the most accurate time.
     */
    private final DescriptorPublisher theComponent

    @Autowired
    RestGateway( final DescriptorPublisher aComponent ) {
        theComponent = aComponent
    }

    @RequestMapping( method = [RequestMethod.PUT],
                     consumes = [HypermediaControl.MIME_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE],
                     produces = [HypermediaControl.MIME_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE] )
    ResponseEntity<HypermediaControl> publishDescriptor( @RequestBody @Valid HypermediaControl upload,
                                                         Errors errors,
                                                         HttpServletRequest request ) {
        final ResponseEntity<HypermediaControl> response
        def control = defaultControl( request )
        control.add( ControllerLinkBuilder.linkTo( RestGateway ).withSelfRel() )

        if ( errors.hasErrors() ) {
            addErrors( errors, control )
            response = ResponseEntity.badRequest().body( control )
        }
        else {
            def url = toURL( upload )
            def event = theComponent.publish( new PublisherCommand( descriptorLocation: url ) )
            response = ResponseEntity.ok( control )
        }
        response
    }

    private URL toURL( HypermediaControl upload ) {
        def descriptorURL = upload.descriptorURL
        try {
            new URL( descriptorURL )
        }
        catch( MalformedURLException e ) {
            feedbackProvider.sendFeedback( MessagingContext.MALFORMED_URL, descriptorURL, e )
            throw new MalformedUrlError( MessagingContext.MALFORMED_URL, descriptorURL )
        }
    }

    private static HypermediaControl defaultControl( HttpServletRequest request ) {
        def path = request.getAttribute( HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE ) as String
        new HypermediaControl( status: HttpStatus.OK.value(), timestamp: Instant.now().toString(), path: path )
    }

    private static HypermediaControl addErrors( Errors errors, HypermediaControl toAugment ) {
        def validationErrors = errors.fieldErrors.collect { new ValidationError( field: it.field, reason: it.defaultMessage ) }
        toAugment.errorBlock = new ErrorBlock( code: MessagingContext.VALIDATION_ERROR.code,
                                               message: 'The uploaded descriptor is invalid.  Please correct the issues and try again.',
                                               developerMessage: 'Certain properties in the payload are invalid.',
                                               validationErrors: validationErrors )
        toAugment
    }
}
