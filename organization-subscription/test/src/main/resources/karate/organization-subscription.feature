Feature: [organization-subscription-api] create organization

  Background:
    * url organizationSubscription

  Scenario: [organization-subscription-api] create valid organization
    Given path '/organizations'
    When method POST
    And request
    """
    {
      "name": "Test Organization",
      "login": "test-organization",
      "mail": "contact@test-organization.com"
    }
    """
    Then status 200
