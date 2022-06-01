package gitlab.api;

import io.restassured.http.ContentType;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

/**
 * This class mainly contains the steps that uses HTTP requests in order to interact with endpoints
 *
 * @author  Ali Pala
 * @version 1.0
 */

public class GitlabIssuesAPI {

    private static final String ISSUES_BY_PROJECT =  "/projects/{projects}/issues";
    private static final String SINGLE_ISSUE_BY_PROJECT =  "projects/{projects}/issues/{issue}";
    private static final String SINGLE_ISSUE_BY_QUERY_PARAMETER =  "projects/{projects}/issues";
    private static final String ALL_ISSUES =  "/issues";

    @Step("Get all issues")
    public void getAllIssues(String accessToken) {
        SerenityRest.given().auth().preemptive().oauth2(accessToken)
                .contentType(ContentType.JSON)
                .when()
                .log().all()
                .get(ALL_ISSUES);
    }

    @Step("Get single issue by id {0} and project {1}")
    public void getSingleIssueByProject(Integer issueId, Integer projectId, String accessToken) {
        SerenityRest.given().auth().preemptive().oauth2(accessToken)
                .contentType(ContentType.JSON)
                .when()
                .pathParam("issue", issueId)
                .pathParam("projects", projectId)
                .log().all()
                .get(SINGLE_ISSUE_BY_PROJECT);
    }

    @Step("Get single issue by query parameter {0} {1} {2}")
    public void getSingleIssueByQueryParameter(Integer projectId, String parameterName, String parameterValue, String accessToken) {
        SerenityRest.given().auth().preemptive().oauth2(accessToken)
                .contentType(ContentType.JSON)
                .when()
                .pathParam("projects", projectId)
                .queryParam(parameterName, parameterValue)
                .log().all()
                .get(SINGLE_ISSUE_BY_QUERY_PARAMETER);
    }

    @Step("Get invalid issue request by id {0} and project {1}")
    public void getInvalidIssueRequestByProject(String issueId, Integer projectId, String accessToken) {
        SerenityRest.given().auth().preemptive().oauth2(accessToken)
                .contentType(ContentType.JSON)
                .when()
                .pathParam("issue", issueId)
                .pathParam("projects", projectId)
                .log().all()
                .get(SINGLE_ISSUE_BY_PROJECT);
    }

    @Step("Create issue by project {0}")
    public void addIssueByProject(Integer projectId, String accessToken, String payload) {
        SerenityRest.given().auth().preemptive().oauth2(accessToken)
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .pathParam("projects", projectId)
                .log().all()
                .post(ISSUES_BY_PROJECT);
    }

    @Step("Edit issue by project {0}")
    public void editIssueByProject(Integer issueId, Integer projectId, String accessToken, String payload) {
        SerenityRest.given().auth().preemptive().oauth2(accessToken)
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .pathParam("issue", issueId)
                .pathParam("projects", projectId)
                .log().all()
                .put(SINGLE_ISSUE_BY_PROJECT);
    }

    @Step("Delete an existing issue")
    public void deleteIssue(Integer issueId, Integer projectId, String accessToken) {
        SerenityRest.given().auth().preemptive().oauth2(accessToken)
                .contentType(ContentType.JSON)
                .when()
                .pathParam("issue", issueId)
                .pathParam("projects", projectId)
                .log().all()
                .delete(SINGLE_ISSUE_BY_PROJECT);
    }

}
