package gitlab.helpers;

import com.github.javafaker.Faker;
import org.json.JSONObject;

public class DataGenerator {

    public static String getRandomEmail() {
        Faker faker = new Faker();
        String email = faker.name().firstName().toLowerCase() + faker.random().nextInt(0, 100) + "@test.com";
        return email;
    }

    public static String getRandomToken() {
        Faker faker = new Faker();
        String token = faker.gameOfThrones().quote();
        return token;
    }

    public static String getRandomUserName() {
        Faker faker = new Faker();
        String username = faker.name().username();
        return username;
    }

    public static Integer getRandomProjectId() {
        Faker faker = new Faker();
        Integer projectId = faker.number().numberBetween(10000, 100000);
        return projectId;
    }

    public static Integer getRandomIssueId() {
        Faker faker = new Faker();
        Integer issueId = faker.random().nextInt(0, 100);
        return issueId;
    }

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
