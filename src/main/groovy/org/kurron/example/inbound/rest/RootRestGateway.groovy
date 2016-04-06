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
import org.kurron.example.core.TimeComponent
import org.kurron.feedback.AbstractFeedbackAware
import org.kurron.stereotype.InboundRestGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.mvc.ControllerLinkBuilder
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.servlet.HandlerMapping
import org.springframework.web.util.UriComponentsBuilder

/**
 * Inbound HTTP gateway that supports the resource browsing.
 **/
@SuppressWarnings( 'GroovyUnusedDeclaration' )
@InboundRestGateway
@RequestMapping( path = '/' )
class RootRestGateway extends AbstractFeedbackAware {

    /**
     * Knows how to get the most accurate time.
     */
    private final TimeComponent theComponent

    @Autowired
    RootRestGateway( final TimeComponent aComponent ) {
        theComponent = aComponent
    }

    @RequestMapping( path = '/',
                     method = [RequestMethod.GET],
                     produces = [HypermediaControl.MIME_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE] )
    ResponseEntity<HypermediaControl> resourceExplorer( HttpServletRequest request, UriComponentsBuilder builder ) {
        def control = defaultControl( request )
        control.add( ControllerLinkBuilder.linkTo( RootRestGateway ).withSelfRel() )
        control.add( ControllerLinkBuilder.linkTo( RestGateway ).withRel( 'deploy' ) )
        ResponseEntity.ok( control )
    }

    private static HypermediaControl defaultControl( HttpServletRequest request ) {
        def path = request.getAttribute( HandlerMapping.PATH_WITHIN_HANDLER_MAPPING_ATTRIBUTE ) as String
        new HypermediaControl( status: HttpStatus.OK.value(), timestamp: Instant.now().toString(), path: path )
    }
}
