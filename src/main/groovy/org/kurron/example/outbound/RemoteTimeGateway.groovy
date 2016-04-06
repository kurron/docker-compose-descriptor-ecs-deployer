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

package org.kurron.example.outbound

import java.time.Instant
import org.kurron.example.MessagingContext
import org.kurron.feedback.AbstractFeedbackAware
import org.kurron.stereotype.OutboundGateway

/**
 * A "real" implementation of the time service that consults with an official authority.
 **/
@SuppressWarnings( 'GroovyUnusedDeclaration' )
@OutboundGateway
class RemoteTimeGateway extends AbstractFeedbackAware implements TimeService {

    @Override
    Instant checkTheTime() {
        // see FeignIntegrationTest for an example on a better way to
        // make HTTP calls

        // force an error to trigger the circuit-breaker
        def e = new UnsupportedOperationException( 'checkTime' )
        feedbackProvider.sendFeedback( MessagingContext.FORCED_ERROR, 1964 )
        throw e
    }

    @SuppressWarnings( ['GrMethodMayBeStatic'] )
    private Instant defaultTime() {
        // looks like we can't reach the time server, let's return our local time instead
        Instant.now()
    }
}
