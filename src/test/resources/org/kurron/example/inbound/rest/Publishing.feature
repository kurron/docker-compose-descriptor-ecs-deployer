Feature: Amazon EC2 Container Service Publication Support
  As a release engineer, I need to be able to publish Docker Compose files to
  Amazon's EC2 Container Service.  This service should be able to pull a particular
  Docker Compose file (descriptor) from a URL and push that to ECS.  Since this orchestration
  will be done from Rundeck, I am willing to wait for the publication to complete so that I
  have confirmation things went according to plan.  Network failures do happen so we'll need the
  ability to retry a request without negative consequences (idempotent).

  Background: Proper Request Construction
    Given a properly constructed hypermedia control
    And the Accept header set to application/hal+json
    And the Content-Type header set to application/hal+json
    And the X-Trace-ID header set to a UUID

  @required
  Scenario: Successful Publication
    When a PUT to the publication resource is made
    Then an HTTP status code of 200 is returned
    And a hypermedia control is returned

  @required
  Scenario Outline: Failed Publication
    When a PUT to the publication resource is made
    And the publication fails due to <failure-cause>
    Then an HTTP status code of <status-code> is returned
    And a hypermedia control is returned

    Examples:
      | failure-cause                              | status-code |
      | taking too long                            | 504         |
      | ECS saying the request cannot be completed | 400         |