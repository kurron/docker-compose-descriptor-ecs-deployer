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

/**
 * An outbound gateway responsible for acquiring the Docker Compose descriptor.
 **/
interface DownloadService {

    /**
     * Fetches the descriptor from the specified location.
     * @param location where to grab the descriptor from.
     * @return the downloaded descriptor.
     */
    String acquire( URL location )
}
