Feature: [organization-subscription-api] create organization


  Scenario: [organization-subscription-api] create valid organization
    Given Request url is "organization-subscription" url
    And Request path is "/organizations"
    When post with body
    """
    {
      "name": "Test Organization",
      "login": "test-organization",
      "mail": "contact@test-organization.com"
    }
    """
    Then response status is 200
