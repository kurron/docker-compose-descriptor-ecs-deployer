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

package org.kurron.example.outbound.repository

import org.kurron.example.MessagingContext
import org.kurron.feedback.AbstractFeedbackAware
import org.kurron.stereotype.OutboundGateway
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.client.RestClientException
import org.springframework.web.client.RestOperations

/**
 * The "real" implementation of the download service that consults with an official authority.
 **/
@SuppressWarnings( 'GroovyUnusedDeclaration' )
@OutboundGateway
class RemoteDownloadGateway extends AbstractFeedbackAware implements DownloadService {

    /**
     * Manages HTTP communications.
     */
    private final RestOperations theTemplate

    @Autowired
    RemoteDownloadGateway( final RestOperations aTemplate ) {
        theTemplate = aTemplate
    }

    @Override
    String acquire( final URI location ) {
        try {
            ResponseEntity<String> response = theTemplate.getForEntity( location, String )
            response.body
        }
        catch( RestClientException e ) {
            feedbackProvider.sendFeedback( MessagingContext.UNABLE_TO_FETCH_DESCRIPTOR, e )
            def error = new DownloadError( MessagingContext.UNABLE_TO_FETCH_DESCRIPTOR, location.toString() )
            error.detail = e.message
            throw error
        }
    }
}
