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

package org.kurron.example.core

import org.kurron.example.outbound.repository.DownloadService
import org.kurron.feedback.AbstractFeedbackAware
import org.kurron.traits.GenerationAbility
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

/**
 * Orchestrates the steps needed to obtain and publish the descriptor.
 **/
@Component
class DefaultPublisher extends AbstractFeedbackAware implements DescriptorPublisher, GenerationAbility {

    /**
     * Knows how to acquire the descriptor.
     */
    private final DownloadService theDownloadService

    @Autowired
    DefaultPublisher( final DownloadService aDownloadService ) {
        theDownloadService = aDownloadService
    }

    @Override
    PublisherEvent publish( final PublisherCommand command ) {
        def descriptor = theDownloadService.acquire( command.descriptorLocation )
        new PublisherEvent()
    }
}
