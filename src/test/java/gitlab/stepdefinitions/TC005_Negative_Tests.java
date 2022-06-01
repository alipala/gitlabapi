package gitlab.stepdefinitions;

import gitlab.api.GitlabIssuesAPI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.apache.http.HttpStatus;

import static gitlab.helpers.DataGenerator.*;
import static gitlab.utilities.AccessTokenProvider.getAccessToken;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.equalTo;

public class TC005_Negative_Tests {

    private static String token;

    @Steps
    GitlabIssuesAPI gitlab;

    @Given("user is unauthorized")
    public void user_is_unauthorized() {
        token = getRandomToken();
    }

    @When("user wants to retrieve issue id {int} in project {int}")
    public void user_wants_to_retrieve_issue_id_in_project(Integer issueId, Integer projectId) {
        gitlab.getSingleIssueByProject(issueId, projectId, token);
    }

    @Then("should get {string} in response")
    public void should_get_in_response(String responseMessage) {
        restAssuredThat(response -> response.statusCode(HttpStatus.SC_UNAUTHORIZED));
        restAssuredThat(response -> response.body("message", equalTo(responseMessage)));
    }

    @Given("user is authorized")
    public void user_is_authorized() {
        token = getAccessToken();
    }
    @When("user wants to update an issue undefined project")
    public void user_wants_to_update_an_issue_undefined_project() {

        Integer projectId = getRandomProjectId();
        Integer issueId = getRandomIssueId();
        String invalidRequest = getRandomRequestBody().toString();

        gitlab.editIssueByProject(issueId, projectId, token, invalidRequest);
    }
    @Then("should see {string} in response")
    public void should_see_in_response(String responseMessage) {
        restAssuredThat(response -> response.statusCode(HttpStatus.SC_NOT_FOUND));
        restAssuredThat(response -> response.body("message", equalTo(responseMessage)));
    }
}
