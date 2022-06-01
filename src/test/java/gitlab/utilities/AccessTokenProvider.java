package gitlab.utilities;

import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;
import org.json.JSONObject;

/**
 * This class provides Access Token by
 * resource owner Credentials grant type
 */
public class AccessTokenProvider {

    private static String clientId = "";
    public static String clientSecret = "2";
    private static String tokenUri = "https://gitlab.com/oauth/token";
    public static String scope = "api";
    public static String username = "";
    public static String password = "";

    /**
     * This method provides access token based on owner information
     * @return Access token
     */
    public static String getAccessToken() {
        Response response =
                SerenityRest.given().auth().preemptive().basic(clientId, clientSecret)
                        .formParam("grant_type", "password")
                        .formParam("username", username)
                        .formParam("password", password)
                        .formParam("scope", scope)
                        .when()
                        .post(tokenUri);

        JSONObject jsonObject = new JSONObject(response.getBody().asString());
        String accessToken = jsonObject.get("access_token").toString();

        return accessToken;
    }

}
