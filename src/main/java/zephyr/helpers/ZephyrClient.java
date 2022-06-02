package zephyr.helpers;

import zephyr.model.TestExecution;
import org.json.JSONArray;
import org.json.JSONObject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * ZephyrClient provides publishing the test results into Zephyr scale app
 * TODO: Should be refactored due to SOLID principles violations
 * @author  Ali Pala
 */

public class ZephyrClient {

    private final String baseURI = "https://api.zephyrscale.smartbear.com/v2/%s";
    private final String endpoint = "testexecutions";
    private final String url = String.format(baseURI, endpoint);

    public void publishResult(TestExecution testExecution) {
        JSONObject executionData = new JSONObject();
        executionData.put("projectKey", testExecution.getProjectKey());
        executionData.put("testCaseKey", testExecution.getTestCaseKey());
        executionData.put("testCycleKey", testExecution.getTestCycleKey());
        executionData.put("statusName", testExecution.getStatusName());
        executionData.put("executionTime", testExecution.getExecutionTime());
        executionData.put("comment", testExecution.getComment());

        JSONArray array = new JSONArray();
        JSONObject testScriptResultsItems = new JSONObject();
        testScriptResultsItems.put("statusName", testExecution.getScriptStatusName());
        testScriptResultsItems.put("actualEndDate", testExecution.getActualEndDate());
        testScriptResultsItems.put("actualResult", testExecution.getActualResult());
        array.put(testScriptResultsItems);
        executionData.put("testScriptResults", array);

        String message = executionData.toString();
        System.out.println("Test Execution: " + message);

        Entity<String> payload = Entity.json(executionData.toString());
        Client client = ClientBuilder.newClient();
        Response response = client.target(url)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .header("Authorization", "Bearer " + System.getenv("ZEPHYR_SCALE_API_KEY"))
                .post(payload);

        // Printing the important response items
        System.out.println("Status: " + response.getStatus());
        System.out.println("Headers: " + response.getHeaders());
        System.out.println("Entity: " + response.readEntity(String.class));

    }
}
