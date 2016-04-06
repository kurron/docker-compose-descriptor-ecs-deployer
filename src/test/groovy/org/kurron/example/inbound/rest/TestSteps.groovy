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

import cucumber.api.PendingException
import cucumber.api.java.After
import cucumber.api.java.Before
import cucumber.api.java.en.Given
import cucumber.api.java.en.Then
import cucumber.api.java.en.When
import groovy.util.logging.Slf4j
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.test.context.ContextConfiguration

/**
 * Step definitions for the service. If you word your features in a consistent manner, then
 * steps will automatically get reused and you won't have to keep writing the same test code.
 **/
@SuppressWarnings( ['MethodCount'] )
@Slf4j
@ContextConfiguration( classes = [AcceptanceTestConfiguration], loader = SpringApplicationContextLoader )
class TestSteps {
    /**
     * This is state shared between steps and can be setup and torn down by the hooks.
     **/
    class MyWorld {
        String foo
    }

    /**
     * Shared between hooks and steps.
     **/
    MyWorld sharedState

    @Before
    void assembleSharedState() {
        log.info( 'Creating shared state' )
        sharedState = new MyWorld()
    }

    @After
    void destroySharedState() {
        log.info( 'Destroying shared state' )
        sharedState = null
    }

    @Given( '^a properly constructed hypermedia control$' )
    void '^a properly constructed hypermedia control$'() {
        throw new PendingException()
    }

    @Given( '^the Accept header set to application/hal\\+json$' )
    void '^the Accept header set to application/hal\\+json$'() {
        throw new PendingException()
    }

    @Given( '^the Content-Type header set to application/hal\\+json$' )
    void '^the Content-Type header set to application/hal\\+json$'() {
        throw new PendingException()
    }

    @Given( '^the X-Trace-ID header set to a UUID$' )
    void '^the X-Trace-ID header set to a UUID$'() {
        throw new PendingException()
    }

    @When( '^a PUT to the publication resource is made$' )
    void '^a PUT to the publication resource is made$'() {
        throw new PendingException()
    }

    @Then( '^an HTTP status code of (\\d+) is returned$' )
    void '^an HTTP status code of (\\d+) is returned$'( int statusCode ) {
        throw new PendingException()
    }

    @Then( '^a hypermedia control is returned$' )
    void '^a hypermedia control is returned$'() {
        throw new PendingException()
    }

    @When( '^the publication fails due to taking too long$' )
    void '^the publication fails due to taking too long$'() {
        throw new PendingException()
    }

    @When( '^the publication fails due to ECS saying the request cannot be completed$' )
    void '^the publication fails due to ECS saying the request cannot be completed$'() {
        throw new PendingException()
    }
}

