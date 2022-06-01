package gitlab.helpers;

import com.github.javafaker.Faker;
import org.json.JSONObject;

/**
 * This class provides random data to feed the request bodies
 *
 * @author Ali Pala
 */
public class DataGenerator {

    /**
     * This method is used for generating random token
     * @return random token
     */
    public static String getRandomToken() {
        Faker faker = new Faker();
        String token = faker.gameOfThrones().quote().replace(" ", "-");
        return token;
    }

    /**
     * This method is used for generating malformed title
     * @return title
     */
    public static String getRandomMalformedTitle() {
        Faker faker = new Faker();
        String title = faker.lorem().fixedString(256);
        return title;
    }

    /**
     * This method is used for generating malformed issue id
     * @return issue id
     */
    public static String getRandomMalformedIssueId() {
        Faker faker = new Faker();
        String issue = faker.lorem().characters(50);
        return issue;
    }

    /**
     * This method is used for generating random project id
     * @return project id
     */
    public static Integer getRandomProjectId() {
        Faker faker = new Faker();
        Integer projectId = faker.number().numberBetween(10000, 100000);
        return projectId;
    }

    /**
     * This method is used for generating random issue id
     * @return issue id
     */
    public static Integer getRandomIssueId() {
        Faker faker = new Faker();
        Integer issueId = faker.random().nextInt(0, 100);
        return issueId;
    }

    /**
     * This method is used for generating random json request body
     * @return json request body
     */
    public static JSONObject getRandomRequestBody() {
        Faker faker = new Faker();
        String title = faker.gameOfThrones().character();
        String description = faker.gameOfThrones().city();
        Integer assignee = faker.number().numberBetween(10000, 100000);
        String body = faker.gameOfThrones().quote();
        JSONObject json = new JSONObject();
        json.put("title", title);
        json.put("description", description);
        json.put("assignee_id", assignee);
        return json;
    }
}
