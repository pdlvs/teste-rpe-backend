import io.restassured.RestAssured;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetUsers {
    private static final String BASE_URL = "https://reqres.in/api";

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = BASE_URL;
    }

    @Test
    public void getAllUsers() {
        given()
                .when()
                .get("/users")
                .then()
                .statusCode(200)// Verifica se o status foi 200
                .body("page", equalTo(1)); // Verifica se está na primera página
    }

    @Test
    public void getUserById() {
        given()
                .when()
                .get("/users/2")
                .then()
                .statusCode(200)// Verifica se o status foi 200
                .body("data.id", equalTo(2)); // Verifica se o id do usuário retornado é 2
    }

    @Test
    public void getUserFail() {
        given()
                .when()
                .get("/unknown/23")
                .then()
                .statusCode(404)//Verifica se o status foi 404
                .body(equalTo("{}")); // Verifica se o retorno foi vazio
    }
}
