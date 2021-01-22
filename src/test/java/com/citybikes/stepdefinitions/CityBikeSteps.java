package com.citybikes.stepdefinitions;

import com.google.common.collect.Iterables;
import io.cucumber.java8.En;
import io.cucumber.java8.Scenario;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;

import java.util.List;

import static org.testng.Assert.*;

public class CityBikeSteps implements En {

    Response response = null;
    Integer cityIndex;
    Scenario scenario;

    public CityBikeSteps() {

        Before((Scenario scenario) -> {
            this.scenario = scenario;
        });

        Given("the api base uri is {string}", (String uri) -> {
            RestAssured.baseURI = uri;
        });

        When("there is a GET request to {string}", (String endPoint) -> {
            response = RestAssured.when().get(endPoint);
        });

        Then("status code should be {int}", (Integer statusCode) -> {
            response.then().statusCode(statusCode);
        });

        And("the city {string} should be in {string}", (String city, String country) -> {
            List<String> cityNames = response.path("networks.location.city");
            cityIndex = Iterables.indexOf(cityNames, cityName -> cityName.equals(city));
            String countryCode = response.path("networks.location[" + cityIndex + "].country");
            assertEquals(countryCode, country);
        });

        And("return their  corresponded latitude and longitude", () -> {
            float latitude = response.path("networks.location[" + cityIndex + "].latitude");
            float longitude = response.path("networks.location[" + cityIndex + "].longitude");
            scenario.log("Latitude : " + latitude + " Logitude : " + longitude);
        });

    }
}