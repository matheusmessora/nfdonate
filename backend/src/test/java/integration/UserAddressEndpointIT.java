package integration;

import com.jayway.restassured.http.ContentType;
import integration.shared.IntegrationServer;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.nullValue;

public class UserAddressEndpointIT extends IntegrationServer {

    @Test
    public void should_create_address() {
        createUserAddressWithoutComplement();
    }

    private void createUserAddressWithoutComplement() {
        given()
            .contentType(ContentType.JSON)
            .body("{ \"cep\": \"01535001\",\"idUser\": 1, \"number\": 110 }")
            .when()
                .post("http://127.0.0.1:" + jettyPort + "/user_address")
            .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
            .and()
                .body("id", equalTo(1))
            .and()
            .body("address", equalTo("Rua Paulo Orozimbo - de 629/630 ao fim"))
            .and()
            .body("number", equalTo(110))
            .and()
            .body("complement", nullValue())
            .and()
            .body("city", equalTo("Sao Paulo"))
            .and()
            .body("uf", equalTo("SP"))
            .and()
            .body("district", equalTo("Cambuci"));
    }

    private void createUserAddressWithComplement() {
        given()
                .contentType(ContentType.JSON)
                .body("{ \"cep\": \"01535001\",\"idUser\": 1, \"number\": 110, \"complement\": \"casa\" }")
                .when()
                .post("http://127.0.0.1:" + jettyPort + "/user_address")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED)
                .and()
                .body("id", equalTo(1))
                .and()
                .body("address", equalTo("Rua Paulo Orozimbo - de 629/630 ao fim"))
                .and()
                .body("number", equalTo(110))
                .and()
                .body("complement", equalTo("casa"))
                .and()
                .body("city", equalTo("Sao Paulo"))
                .and()
                .body("uf", equalTo("SP"))
                .and()
                .body("district", equalTo("Cambuci"));
    }

    @Test
    public void should_create_address_with_complement() {
        createUserAddressWithComplement();
    }

    @Test
    public void should_return_BAD_REQUEST_when_CEP_invalid() {
        given()
            .contentType(ContentType.JSON)
            .body("{ \"cep\": \"12345\",\"idUser\": 1, \"number\": 110 }")
            .when()
                .post("http://127.0.0.1:" + jettyPort + "/user_address")
            .then()
            .assertThat()
            .statusCode(HttpStatus.SC_BAD_REQUEST)
            .and()
            .body("code", equalTo("cep_invalid"));
    }

    @Test
    public void should_return_BAD_REQUEST_when_number_is_empty() {
        given()
            .contentType(ContentType.JSON)
                .body("{ \"cep\": \"01535001\",\"idUser\": 1 }")
            .when()
                .post("http://127.0.0.1:" + jettyPort + "/user_address")
            .then()
            .assertThat()
            .statusCode(HttpStatus.SC_BAD_REQUEST)
            .and()
            .body("code", equalTo("number_mandatory"))
                .and()
            .body("message", equalTo("Numero eh obrigatorio"));
    }

    @Test
    public void should_return_BAD_REQUEST_when_CEP_not_found() {
        given()
            .contentType(ContentType.JSON)
            .body("{ \"cep\": \"01535444\",\"idUser\": 1, \"number\": 110 }")
            .when()
                .post("http://127.0.0.1:" + jettyPort + "/user_address")
            .then()
            .assertThat()
            .statusCode(HttpStatus.SC_BAD_REQUEST)
            .and()
            .body("code", equalTo("postal_address_not_found"));
    }

    @Test
    public void should_return_postall_address_by_id() {
        createUserAddressWithoutComplement();

        given()
            .when()
                .get("http://127.0.0.1:" + jettyPort + "/user_address/1")
            .then()
            .assertThat()
            .statusCode(HttpStatus.SC_OK)
                .and()
                .body("id", equalTo(1))
                .and()
                .body("address", equalTo("Rua Paulo Orozimbo - de 629/630 ao fim"))
                .and()
                .body("number", equalTo(110))
                .and()
                .body("complement", nullValue())
                .and()
                .body("city", equalTo("Sao Paulo"))
                .and()
                .body("uf", equalTo("SP"))
                .and()
                .body("district", equalTo("Cambuci"));
    }

    @Test
    public void should_return_no_content_if_id_does_not_exist() {
        given()
            .when()
                .get("http://127.0.0.1:" + jettyPort + "/user_address/999")
            .then()
            .assertThat()
            .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void should_return_OK_when_delete_by_id() {
        createUserAddressWithoutComplement();

        given()
            .when()
                .delete("http://127.0.0.1:" + jettyPort + "/user_address/1")
            .then()
            .assertThat()
            .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void should_return_no_content_when_delete_by_id_does_not_exist() {
        given()
            .when()
                .delete("http://127.0.0.1:" + jettyPort + "/user_address/9999")
            .then()
            .assertThat()
            .statusCode(HttpStatus.SC_NO_CONTENT);
    }


    @Test(dependsOnMethods = "should_create_address_with_complement")
    public void should_return_postall_address_updated_with_complement() {
        createUserAddressWithoutComplement();

        given()
                .contentType(ContentType.JSON)
                .body("{ \"cep\": \"01504001\",\"idUser\": 1, \"number\": 999, \"complement\": \"casa\" }")
                .when()
                .put("http://127.0.0.1:" + jettyPort + "/user_address/1")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK)
                .and()
                .body("id", equalTo(1))
                .and()
                .body("address", equalTo("Rua Vergueiro - até 1289 - lado ímpar"))
                .and()
                .body("number", equalTo(999))
                .and()
                .body("complement", equalTo("casa"))
                .and()
                .body("city", equalTo("Sao Paulo"))
                .and()
                .body("uf", equalTo("SP"))
                .and()
                .body("district", equalTo("Liberdade"));
    }

}
