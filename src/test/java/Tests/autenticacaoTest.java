package Tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class autenticacaoTest {

    setEndpoints env = new setEndpoints();

    @Test
    @DisplayName("Autenticaçao com sucesso")
    public void autenticacao() {


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
                .body("message", equalTo ("Sucesso ao realizar o login"))
                .body("error", notNullValue());

    }

    @Test
    @DisplayName("Não realizar login com usuario e senha em branco")
    public void naoRealizarLoginComCamposEmBranco() {


        given()
                //Cabeçalho
                .header("content-type", "application/json")
                .body("{\n" +
                        "  \"usuarioEmail\": \"\",\n" +
                        "  \"usuarioSenha\": \"\"\n" +
                        "}")
                .log().all()

                //Ação
                .when()
                .post(env.setAutenticacao())//URL do endpoint que faz a autenticação

                //Validação do Response
                .then()
                .log().all()
                .statusCode(401); //Status code que sinaliza que o cliente nao se autenticou
    }
}
