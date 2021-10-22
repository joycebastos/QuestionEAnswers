package Utils;

import Tests.setEndpoints;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;



public class CapturarToken {

    setEndpoints env = new setEndpoints();

    public String capturarToken() {

        Response response =

                given()
                        //Cabeçalho
                        .header("content-type", "application/json")
                        .body("{\n" +
                                "  \"usuarioEmail\": \"Bnserh@yopmail.com\",\n" +
                                "  \"usuarioSenha\": \"123456789\"\n" +
                                "}")
                        .log().all()

                        //URL do endpoint
                        .when()
                        .post(env.setAutenticacao())

                        //Validação do Response
                        .then()
                        .log().all()
                        .statusCode(200) //Status code que sinaliza que a requisição foi bem sucedida
                        .extract().response();

        String token = response.path("data.token");
        System.out.println("o numero de token é: " +token);
        return token;
    }
}