import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class DeleteUsersTest {
    private static final String BASE_URL = "https://reqres.in/api";

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void deleteAllUsers() {
        given()
                .when()
                .delete("/users/2")
                .then()
                .statusCode(204);// Verifica se o status foi 204
    }

}
