package gitlab.stepdefinitions;

import gitlab.api.GitlabIssuesAPI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.apache.http.HttpStatus;

import java.util.Arrays;

import static gitlab.utilities.AccessTokenProvider.getAccessToken;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.equalTo;

public class TC003_List_Issues {

    private static String token;

    @Steps
    GitlabIssuesAPI gitlab;

    @Given("user is authorized to access")
    public void user_is_authorized_to_access() {
        token = getAccessToken();
    }

    @When("user wants to get all issues")
    public void user_wants_to_get_all_issues() {
        gitlab.getAllIssues(token);
    }

    @Then("issues belong to user are returned {int}")
    public void issues_belong_to_user_are_returned(Integer noIssue) {
        restAssuredThat(response -> response.statusCode(HttpStatus.SC_OK));
        restAssuredThat(response -> response.body("iid.size()", equalTo(noIssue))); // number of issue belongs to admin
    }

    @When("user wants to get issue in project {int} by {string} equals {string}")
    public void user_wants_to_get_issue_in_project_by_equals(Integer project, String name, String value) {
        gitlab.getSingleIssueByQueryParameter(project, name, value, token);
    }

    @Then("the requested issue is returned")
    public void the_requested_issue_is_returned() {
        restAssuredThat(response -> response.statusCode(HttpStatus.SC_OK));
        restAssuredThat(response -> response.body("state", equalTo(Arrays.asList("closed"))));
    }
}
