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

import groovy.transform.InheritConstructors
import org.kurron.feedback.exceptions.AbstractError
import org.springframework.http.HttpStatus

/**
 * Indicates that a URL is malformed somehow.
 */
@InheritConstructors
class MalformedUrlError extends AbstractError {

    @Override
    HttpStatus getHttpStatus() {
        HttpStatus.BAD_REQUEST
    }

    @Override
    String getDeveloperMessage() {
        'This does not appear to be a valid URL. Correct the problem and try again.'
    }
}
