package gitlab.stepdefinitions;

import gitlab.api.GitlabIssuesAPI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.apache.http.HttpStatus;

import static gitlab.utilities.AccessTokenProvider.getAccessToken;
import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;

public class TC004_Delete_Issue {

    private static String token;
    private static Integer projectId = null;
    private static Integer issueId = null;

    @Steps
    GitlabIssuesAPI gitlab;

    @Given("user is authorized to access to delete")
    public void user_is_authorized_to_access_to_delete() {
        token = getAccessToken();
    }

    @Given("user retrieve an issue")
    public void user_retrieve_an_issue() {
        gitlab.getAllIssues(token);
        projectId = lastResponse().path("project_id[0]"); // first project
        issueId = lastResponse().path("iid[0]"); // first issue
    }

    @When("user wants to delete the issue")
    public void user_wants_to_delete_the_issue() {
        gitlab.deleteIssue(issueId, projectId, token);
        restAssuredThat(response -> response.statusCode(HttpStatus.SC_NO_CONTENT));
    }

    @Then("the issue should not exist")
    public void the_issue_should_not_exist() {
        gitlab.getSingleIssueByProject(issueId, projectId, token);
        restAssuredThat(response -> response.statusCode(HttpStatus.SC_NOT_FOUND));
    }
}


