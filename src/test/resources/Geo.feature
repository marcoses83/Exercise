Feature: Geo
  So I can know the city of a customer
  As a Sherpa manager
  I want to store the user location information

  Scenario Outline: Store user city
    Given "<username>" user name
    And "<postalcode>" postal code
    When I request the city
    Then the request is successful
    And the city "<city>" is returned
    Examples:
      |username  |postalcode|city                      |
      |myName1   |48010     |Bilbao                    |
      |myName2   |23700     |Vega Santa Maria (Linares)|
      |myName3   |28080     |Madrid                    |
      |myName3   |03400     |Villena                   |
