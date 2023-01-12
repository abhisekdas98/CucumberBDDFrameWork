Feature: Validating Place API's

  Scenario: Verify the Plcae is being Added Successfully using AddPlaceAPI
    Given Add Place Payload
    When User Calls "AddPlaceAPI" with Post HTTP Request
    Then the API call got Success with status code 200
    And "status" in response body is "OK"
    And  "scope" in response body is "APP"
