package zephyr.model;

/**
 * This is a builder class consist of test execution details
 * in order to be used for updating a Zephyr scale test
 */
public class TestExecution {

    private final String projectKey;
    private final String testCaseKey;
    private final String testCycleKey;
    private final String statusName;
    private final String executionTime;
    private final String comment;
    private final String scriptStatusName;
    private final String actualEndDate;
    private final String actualResult;

    private TestExecution(ExecutionBuilder builder) {
        this.projectKey = builder.projectKey;
        this.testCaseKey = builder.testCaseKey;
        this.testCycleKey = builder.testCycleKey;
        this.statusName = builder.statusName;
        this.executionTime = builder.executionTime;
        this.comment = builder.comment;
        this.scriptStatusName = builder.scriptStatusName;
        this.actualEndDate = builder.actualEndDate;
        this.actualResult = builder.actualResult;
    }

    public String getProjectKey() {
        return projectKey;
    }

    public String getTestCaseKey() {
        return testCaseKey;
    }

    public String getTestCycleKey() {
        return testCycleKey;
    }

    public String getStatusName() {
        return statusName;
    }

    public String getExecutionTime() {
        return executionTime;
    }

    public String getComment() {
        return comment;
    }

    public String getScriptStatusName() {
        return scriptStatusName;
    }

    public String getActualEndDate() {
        return actualEndDate;
    }

    public String getActualResult() {
        return actualResult;
    }

    @Override
    public String toString() {
        return "TestExecution{" +
                "projectKey='" + projectKey + '\'' +
                ", testCaseKey='" + testCaseKey + '\'' +
                ", testCycleKey='" + testCycleKey + '\'' +
                ", statusName='" + statusName + '\'' +
                ", executionTime='" + executionTime + '\'' +
                ", comment='" + comment + '\'' +
                ", scriptStatusName='" + scriptStatusName + '\'' +
                ", actualEndDate='" + actualEndDate + '\'' +
                ", actualResult='" + actualResult + '\'' +
                '}';
    }

    public static class ExecutionBuilder {
        private String projectKey;
        private String testCaseKey;
        private String testCycleKey;
        private String statusName;
        private String executionTime;
        private String comment;
        private String scriptStatusName;
        private String actualEndDate;
        private String actualResult;

        public ExecutionBuilder projectKey (String projectKey) {
            this.projectKey = projectKey;
            return this;
        }

        public ExecutionBuilder testCaseKey(String testCaseKey) {
            this.testCaseKey = testCaseKey;
            return this;
        }

        public ExecutionBuilder testCycleKey(String testCycleKey) {
            this.testCycleKey = testCycleKey;
            return this;
        }

        public ExecutionBuilder statusName(String statusName) {
            this.statusName = statusName;
            return this;
        }

        public ExecutionBuilder executionTime(String executionTime) {
            this.executionTime = executionTime;
            return this;
        }

        public ExecutionBuilder comment(String comment) {
            this.comment = comment;
            return this;
        }

        public ExecutionBuilder scriptStatusName(String scriptStatusName) {
            this.scriptStatusName = scriptStatusName;
            return this;
        }

        public ExecutionBuilder actualEndDate(String actualEndDate) {
            this.actualEndDate = actualEndDate;
            return this;
        }

        public ExecutionBuilder actualResult(String actualResult) {
            this.actualResult = actualResult;
            return this;
        }

        public TestExecution build() {
            TestExecution testExecution = new TestExecution(this);
            return testExecution;
        }
    }

}
