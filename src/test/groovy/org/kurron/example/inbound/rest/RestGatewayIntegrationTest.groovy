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

import org.junit.experimental.categories.Category
import org.kurron.categories.InboundIntegrationTest
import org.kurron.example.Application
import org.kurron.traits.GenerationAbility
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.boot.test.TestRestTemplate
import org.springframework.boot.test.WebIntegrationTest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.test.context.ContextConfiguration
import spock.lang.Specification

/**
 * An integration-level test of the SampleGateway object.
 **/
@Category( InboundIntegrationTest )
@WebIntegrationTest( randomPort = true )
@ContextConfiguration( classes = [Application, RestGatewayIntegrationTestConfiguration], loader = SpringApplicationContextLoader )
class RestGatewayIntegrationTest extends Specification implements GenerationAbility, RestCapable {

    @Value( '${local.server.port}' )
    int port

    @Autowired
    TestRestTemplate template

    def 'exercise GET happy path'() {
        given: 'a proper testing environment'
        assert port
        template

        when: 'we GET /descriptor/application'
        def uri = buildURI( port, '/descriptor/application', [:] )
        ResponseEntity<HypermediaControl> response = template.getForEntity( uri, HypermediaControl )

        then: 'we get a proper response'
        HttpStatus.OK == response.statusCode
        response.body.links
    }
}
