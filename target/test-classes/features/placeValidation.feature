Feature: validation place API

Scenario Outline: verify if place is being successfully added using AddPlaceAPI
Given Add place payload "<name>""<language>""<address>"
When user calls "AddPlaceAPI" with "Post" http request
Then the API call got success with status code 200
And "status" in response body is "OK"
And verify place_Id created maps to "<name>" using "GetPlaceAPI"

Examples:
  |name  |language| address       |
  |AAhouse| English| world cross center|
  #|BBhouse| Marathi| sea cross center|
  
Scenario: verify if Delete place functionality is working 
Given DeletePlace payload
When user calls "DeletePlaceAPI" with "Post" http request
Then the API call got success with status code 200
And "status" in response body is "OK"