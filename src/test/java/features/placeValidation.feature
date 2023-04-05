Feature: Validating Qa click Maps APIs

#  @AddPlace
#  Scenario: Verify if place is being successfully added using AddPlaceAPI
#    Given  Add Place Payload
#    When user calls "ADD_PLACE_API" with "Post" http request
#    Then the API call got success with status code 200
#    And "status" in response body is "OK"
#    And "scope" in response body is "APP"

  @AddPlaces
  Scenario Outline: Verify if place is being successfully added using AddPlaceAPI with custom payload
    Given  Add Place Payload "<name>" "<language>" "<address>"
    When user calls "ADD_PLACE_API" with "Post" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify place_Id created to "<name>" using "GET_PLACE_API"
    Examples:
      | name   | language | address             |
      | Ahouse | Eng      | world trade center  |
#      | Bhouse | Spn      | world trade center2 |

  @DeletePlace
  Scenario: Verify if place is being successfully deleted using DeletePlaceAPI
    Given  Delete Place Payload
    When user calls "DELETE_PLACE_API" with "POST" http request
    Then the API call got success with status code 200
    And "status" in response body is "OK"

