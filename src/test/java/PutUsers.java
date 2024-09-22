import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PutUsersTest {
    private static final String BASE_URL = "https://reqres.in/api";

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void updateUser() {
        String requestBody = "{\n" +
                "    \"name\": \"Maria do Teste\",\n" +
                "    \"job\": \"Developer\"\n" +
                "}";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put("/users/2")
                .then()
                .statusCode(200)// Verifica se o status foi 200
                .body("name", equalTo("Maria do Teste"))//Verifica se o nome do usuário foi atualizado
                .body("job", equalTo("Developer"));//Verifica se o cargo do usuário foi atualizado
    }
}