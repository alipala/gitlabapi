package gitlab.stepdefinitions;

import gitlab.api.GitlabIssuesAPI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Steps;
import org.apache.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static gitlab.utilities.AccessTokenProvider.getAccessToken;
import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.Matchers.equalTo;

public class TC002_Edit_Issue {

    private static String token;

    @Steps
    GitlabIssuesAPI gitlab;

    private static final String TITLE = "TOOLSQA-Test_Update";
    private static final String DESCRIPTION = "TOOLSQA-Test_Update";
    private static final Integer ASSIGNEE_ID = 4464677;
    private static final String ISSUE_TYPE = "incident";
    private static final String DUE_DATE = "2022-11-31";
    private static final String STATE = "closed";

    @Given("issue {int} already exist in project {int}")
    public void issue_already_exist_in_project(Integer issue, Integer project) {
        // Look for an issue already exist to update
        token = getAccessToken();
        gitlab.getSingleIssueByProject(issue, project, token);
        restAssuredThat(response -> response.statusCode(HttpStatus.SC_OK));
    }

    @When("user wants to update issue")
    public void user_wants_to_update_issue() {
        // Update the existing issue that retrieved
        Integer issueId = SerenityRest.lastResponse().path("iid");
        Integer projectId = SerenityRest.lastResponse().path("project_id");

        List<String> labels = new ArrayList<>();
        labels.add("Updated_Outsystems");
        labels.add("Updated_Mulesoft");

        String validRequest = "{\n" +
                "  \"project_id\": \"" + projectId + "\",\n" +
                "  \"title\": \"" + TITLE + "\",\n" +
                "  \"description\": \"" + DESCRIPTION + "\",\n" +
                "  \"labels\": \"" + labels + "\",\n" +
                "  \"assignees.id\": \"" + ASSIGNEE_ID + "\", \n" +
                "  \"issue_type\": \"" + ISSUE_TYPE + "\", \n" +
                "  \"state\": \"" + STATE + "\", \n" +
                "  \"due_date\": \"" + DUE_DATE + "\" \n}";

        gitlab.editIssueByProject(issueId, projectId, token, validRequest);

    }

    @Then("see the updated issue content")
    public void see_the_updated_issue_content() {
        restAssuredThat(response -> response.statusCode(HttpStatus.SC_OK));
        restAssuredThat(response -> response.header("Content-Type", "application/json"));
        restAssuredThat(response -> response.body("title", equalTo(TITLE)));
        restAssuredThat(response -> response.body("description", equalTo(DESCRIPTION)));
        restAssuredThat(response -> response.body("assignees[0].id", equalTo(ASSIGNEE_ID)));
        restAssuredThat(response -> response.body("issue_type", equalTo(ISSUE_TYPE)));
        restAssuredThat(response -> response.body("state", equalTo(STATE)));
    }
}
