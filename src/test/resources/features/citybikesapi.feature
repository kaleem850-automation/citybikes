Feature: City Bikes API Tests
  As a biker I would like to know the exact location of city bikes around the world in a given application.

  Scenario Outline: As a user I want to verify that the city "<city>" is in "<country>" and return their corresponded latitude and longitude
    Given the api base uri is "http://api.citybik.es"
    When there is a GET request to "/v2/networks"
    Then status code should be 200
    And the city "<city>" should be in "<country>"
    And return their  corresponded latitude and longitude
    Examples:
      | city      | country |
      | Frankfurt | DE      |
      | Albacete  | ES      |
      | Gallipoli | IT      |


