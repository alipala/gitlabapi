package zephyr.listeners;

import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.*;
import zephyr.helpers.ZephyrClient;
import zephyr.model.TestExecution;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * ZephyrClient provides publishing the test results into Zephyr scale app
 * TODO: Should be refactored due to SOLID principles violations
 * @author  Ali Pala
 */

public class ZephyrListener implements ConcurrentEventListener {
    InputStream input;
    private static String projectKey = null;
    private static String testCycleKey = null;

    @Override
    public void setEventPublisher(EventPublisher eventPublisher) {
        eventPublisher.registerHandlerFor(TestCaseFinished.class, this::handleTestCaseFinished);
    }

    private void handleTestCaseFinished(TestCaseFinished event) {

        try {
            Properties prop = new Properties();
            String propFileName = "zephyr.properties";
            input = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (input == null) {
                System.out.println("Unable to find zephyr.properties. Check directory");
                return;
            }

            prop.load(input);
            projectKey = prop.getProperty("projectKey");
            testCycleKey = prop.getProperty("testCycleKey");

        }catch (IOException ex) {
            ex.printStackTrace();
        }

        String testStatus = null;
        String testResultMessage = null;
        TestCase testCase = event.getTestCase();
        Result result = event.getResult();
        Status status = result.getStatus();
        Throwable error = result.getError();
        String featureTag = testCase.getTags().get(0); // Get test tag name in feature file
        String testcaseTag = featureTag.replace("@", ""); //Remove @ sign from tag
        Long durationOfTest = result.getDuration().getSeconds() * 1000; // Update duration from seconds to miliseconds


        if(status.is(Status.FAILED)) {
            testStatus = "Fail";
            testResultMessage = error.getMessage();
        }

        if (status.is(Status.PASSED)) {
            testStatus = "Pass";
            testResultMessage = "Test Passed";
        }

        TestExecution testExecution = new TestExecution.ExecutionBuilder()
                .projectKey(projectKey)
                .testCaseKey(testcaseTag)
                .testCycleKey(testCycleKey)
                .statusName(testStatus)
                .executionTime(durationOfTest.toString())
                .comment(testResultMessage)
                .scriptStatusName("Approved") // Default value need to be put
                .actualResult(testStatus)
                .build();
        ZephyrClient zephyrClient = new ZephyrClient();
        zephyrClient.publishResult(testExecution);
    }
}
