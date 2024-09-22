import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostUsers {
    private static final String BASE_URL = "https://reqres.in/api";

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void createUser() {
        String requestBody = "{\n" +
                "    \"name\": \"João do Teste\",\n" +
                "    \"job\": \"QA\"\n" +
                "}";

        String userId = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/users")
                .then()
                .statusCode(201)// Verifica se o status foi 201
                .body("name", equalTo("João do Teste"))//Verifica se o nome do usuário foi cadastrado
                .body("job", equalTo("QA"))//Verifica se o cargo do usuário foi cadastrado
                .extract()
                .path("id");
        System.out.println("Created User ID: " + userId);
        Config.setUserId(userId);
    }

    @Test
    public void createUserFail() {
        String requestBody = "{\n" +
                "    \"email\": \"sydney@fife\"\n" +
                "}";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/register")
                .then()
                .statusCode(400)//Verifica se o status foi 400
                .body("error", equalTo("Missing password"));//Verifica se a mensagem de erro está correta
    }


}