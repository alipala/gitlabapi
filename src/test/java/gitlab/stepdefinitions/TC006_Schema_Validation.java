package gitlab.stepdefinitions;

import gitlab.api.GitlabIssuesAPI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.module.jsv.JsonSchemaValidator;
import net.thucydides.core.annotations.Steps;
import org.apache.http.HttpStatus;

import static gitlab.utilities.AccessTokenProvider.getAccessToken;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

public class TC006_Schema_Validation {

    private static String token;
    private static final Integer ISSUE_ID = 8;
    private static final Integer PROJECT_ID = 29174825;


    @Steps
    GitlabIssuesAPI gitlab;

    @Given("user is authorized to validation")
    public void user_is_authorized_to_validation() {
        token = getAccessToken();
    }

    @When("user request to get an issue json format")
    public void user_request_to_get_an_issue_json_format() {
        gitlab.getSingleIssueByProject(ISSUE_ID, PROJECT_ID, token);
        restAssuredThat(response -> response.statusCode(HttpStatus.SC_OK));
    }

    @Then("request should be valid")
    public void request_should_be_valid() {
        restAssuredThat(response -> response.body(JsonSchemaValidator.matchesJsonSchemaInClasspath("schema.json")));
    }

}
