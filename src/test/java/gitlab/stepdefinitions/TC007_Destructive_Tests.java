package gitlab.stepdefinitions;

import gitlab.api.GitlabIssuesAPI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.apache.http.HttpStatus;

import static gitlab.helpers.DataGenerator.getRandomMalformedIssueId;
import static gitlab.helpers.DataGenerator.getRandomMalformedTitle;
import static gitlab.utilities.AccessTokenProvider.getAccessToken;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.equalTo;

public class TC007_Destructive_Tests {

    private static String token;

    @Steps
    GitlabIssuesAPI gitlab;

    @Given("user is authorized to access for destructive tests")
    public void user_is_authorized_to_access_for_destructive_tests() {
        token = getAccessToken();
    }

    @When("user puts more than maximum characters to issue title of project {int}")
    public void user_puts_more_than_maximum_characters_to_issue_title_of_project(Integer projectId) {
        String invalidTitle = getRandomMalformedTitle();

        String request = "{\n" +
                "  \"project_id\": \"" + projectId + "\",\n" +
                "  \"title\": \"" + invalidTitle + "\" \n}";

        gitlab.addIssueByProject(projectId, token, request);
    }
    @Then("should see message {string}")
    public void should_see_message(String errorMessage) {
        restAssuredThat(response -> response.statusCode(HttpStatus.SC_BAD_REQUEST));
        restAssuredThat(response -> response.body("message.title[0]", equalTo(errorMessage)));
    }

    @When("user wants to get issue using invalid issue id of project {int}")
    public void user_wants_to_get_issue_using_invalid_issue_id_of_project(Integer projectId) {
        String invalidIssueId = getRandomMalformedIssueId();

        gitlab.getInvalidIssueRequestByProject(invalidIssueId, projectId, token);

    }
    @Then("should see {string}")
    public void should_see(String errorMessage) {
        restAssuredThat(response -> response.statusCode(HttpStatus.SC_BAD_REQUEST));
        restAssuredThat(response -> response.body("error", equalTo(errorMessage)));



    }
}
