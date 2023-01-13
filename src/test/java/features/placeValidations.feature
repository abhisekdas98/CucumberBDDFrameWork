Feature: Validating Place API's

  Scenario Outline: Verify the Plcae is being Added Successfully using AddPlaceAPI
    Given Add Place Payload with "<name>" "<language>" "<address>"
    When User Calls "ADDPLACEAPI" with "Post" HTTP Request
    Then the API call got Success with status code 200
    And "status" in response body is "OK"
    And  "scope" in response body is "APP"

    Examples:
    |name   | language  |address           |
    |AAhouse| English   |World Cross Center|
    |AAhouse1| English1   |World Cross Center1|
    |AAhouse2| English2  |World Cross Center2|

