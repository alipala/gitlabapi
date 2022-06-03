package gitlab.stepdefinitions;

import gitlab.api.GitlabIssuesAPI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import net.thucydides.core.annotations.Steps;
import org.apache.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static gitlab.utilities.AccessTokenProvider.getAccessToken;
import static net.serenitybdd.rest.SerenityRest.*;
import static org.hamcrest.Matchers.equalTo;

public class TC001_Create_Issue {

    private static String token;

    @Steps
    GitlabIssuesAPI gitlab;

    private static final String TITLE = "Sample Test Title";
    private static final String DESCRIPTION = "Sample Test Description";
    private static final Integer  ASSIGNEE_ID = 4464677;
    private static final String ISSUE_TYPE = "incident";
    private static final String DUE_DATE = "2022-11-30";


    @Given("user is authorized to create issue")
    public void user_is_authorized_to_create_issue() {
        token = getAccessToken();
    }

    @When("user wants to create new issue with project id {int}")
    public void user_wants_to_create_new_issue_with_project_id(Integer project) {

        List<String> labels = new ArrayList<>();
        labels.add("Outsystems");
        labels.add("Mulesoft");

        String validRequest = "{\n" +
                "  \"project_id\": \"" + project + "\",\n" +
                "  \"title\": \"" + TITLE + "\",\n" +
                "  \"description\": \"" + DESCRIPTION + "\",\n" +
                "  \"labels\": \"" + labels + "\",\n" +
                "  \"assignee_id\": \"" + ASSIGNEE_ID + "\", \n" +
                "  \"issue_type\": \"" + ISSUE_TYPE + "\", \n" +
                "  \"due_date\": \"" + DUE_DATE + "\" \n}";

        gitlab.addIssueByProject(project, token, validRequest);
        restAssuredThat(response -> response.statusCode(HttpStatus.SC_CREATED));
    }

    @Then("should see newly created issue")
    public void should_see_newly_created_issue() {
        Integer projectId = lastResponse().path("project_id");
        gitlab.getSingleIssueByQueryParameter(projectId, "sort", "desc", token); // get latest issue of project
        restAssuredThat(response -> response
                .header("Content-Type", "application/json")
                .body("title[0]", equalTo(TITLE))
                .body("description[0]", equalTo(DESCRIPTION))
                .body("issue_type[0]", equalTo(ISSUE_TYPE))
                .body("assignees[0][0].id", equalTo(ASSIGNEE_ID)));
    }
}
