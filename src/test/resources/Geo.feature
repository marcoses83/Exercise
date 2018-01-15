Feature: Geo
  So I can know the city of a customer
  As a Sherpa manager
  I want to store the user location information

  Scenario Outline: Store user city
    Given "<username>" user name
    And "<postalcode>" postal code
    When I request the "<city>" city
    Then the city is returned
    And information of user location is stored
    Examples:
      |username  |postalcode|city     |
      |myName1   |48010     |Bilbao   |
      |myName2   |40300     |Sepúlveda|
      |myName3   |28080     |Madrid   |
