package Tests;
import Utils.GeradorDeNome;
import org.junit.jupiter.api.*;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import Utils.GeradorDeEmail;



@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class adicionarUsuarioTest {
    GeradorDeEmail email = new GeradorDeEmail();
    GeradorDeNome nomeCompleto = new GeradorDeNome();

    setEndpoints env = new setEndpoints();

    @Test
    @DisplayName("Adicionar novo usuário")
    public void adicionarNovoUsuario() {

        //Cabeçalho da API
        //Contem os itens necessários que devem ser preenchidos antes de enviar a request
        given()
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "  \"usuarioNomeCompleto\":" + "\"" + nomeCompleto.geraNomeCompleto() + "\"" + "," + //Para melhorar criei um gerador de nome aleatorio
                        "  \"usuarioEmail\":" + "\"" + email.geraEmail() + "\"" + "," + //e-mail eh o unico campo que nao pode ser o mesmo sempre
                        "  \"usuarioSenha\": \"123456789\",\n" +
                        "  \"usuarioTelefone\": \"051988887733\"\n" +
                        "}")
                .log().all()

                .when()
                .post(env.setEnviromentAdicionaNovoUsuario())

                .then()
                .log().all()
                .statusCode(201);
    }

    @Test
    @DisplayName("Nao adicionar usuario com o mesmo email")
    public void naoAdicionarUsuario() {

        //Cabeçalho da API
        //Contem os itens necessários que devem ser preenchidos antes de enviar a request
        given()
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "  \"usuarioNomeCompleto\":" + "\"" + nomeCompleto.geraNomeCompleto() + "\"" + "," +
                        "  \"usuarioEmail\": \"Bnserh@yopmail.com\",\n" + //este email já foi cadastrado
                        "  \"usuarioSenha\": \"123456789\",\n" +
                        "  \"usuarioTelefone\": \"051988887733\"\n" +
                        "}")
                .log().all()

                .when()
                .post(env.setEnviromentAdicionaNovoUsuario())

                .then()
                .log().all()
                .statusCode(409)//status code que sinaliza conflito
                .body("error", equalTo ("O usuário Bnserh@yopmail.com já existe."));//Validei se ele mostra a mensagem de Error no response
    }

}
