package gitlab.stepdefinitions;

import io.cucumber.java.Before;
import io.restassured.RestAssured;

public class Hooks {

    @Before
    public void setUp() {
        System.out.println("Let's get started bb!");
        RestAssured.baseURI = "https://gitlab.com/api/v4";
    }
}
