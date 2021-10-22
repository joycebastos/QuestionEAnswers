package Tests;

import Utils.CapturarToken;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;


public class gerarPerguntasTest {
    setEndpoints env = new setEndpoints();
    CapturarToken token = new CapturarToken();

    @Test
    @DisplayName("Gerar pergunta com sucesso")
    public void visualizarPerguntas() {


        given()
                //Cabeçalho
                .header("content-type", "application/json")
                .header("token", token.capturarToken())//pego o token que gerei na classe CapturarToken
                .log().all()

                //URL do endpoint
                .when()
                .get(env.setPerguntas())//Captura o endpoint de gerar perguntas

                //Validação do Response
                .then()
                .log().all()
                .statusCode(200); //Status code que sinaliza que a requisição foi bem sucedida
    }
}
