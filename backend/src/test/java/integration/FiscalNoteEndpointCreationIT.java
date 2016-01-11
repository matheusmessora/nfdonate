package integration;

import br.com.pandox.nfdonate.domain.CNPJ;
import br.com.pandox.nfdonate.domain.fiscalNote.dto.FiscalNoteDTO;
import br.com.pandox.nfdonate.domain.socialEntity.approved.HospitalCancerBarretos;
import com.jayway.restassured.http.ContentType;
import integration.json.JsonSerializer;
import integration.shared.IntegrationServer;
import org.apache.http.HttpStatus;
import org.testng.annotations.Test;

import java.time.LocalDate;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.nullValue;

public class FiscalNoteEndpointCreationIT extends IntegrationServer {

    @Test
    public void should_create_fiscal_note() {
        String json = createFiscalNoteWithMandatoryFields();

        given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post("http://127.0.0.1:" + jettyPort + "/" + FiscalNoteDTO.URI + "")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_CREATED);
    }

    @Test
    public void should_throw_error_when_mandatory_field_is_null() {
        String json = createFiscalNoteWithoutCOO();

        given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post("http://127.0.0.1:" + jettyPort + "/" + FiscalNoteDTO.URI + "")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    void should_throw_error_when_invalid_cnpj(){
        String json = createFiscalNoteWithInvalidCNPJ();

        given()
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post("http://127.0.0.1:" + jettyPort + "/" + FiscalNoteDTO.URI + "")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    private String createFiscalNoteWithoutCOO() {
        FiscalNoteDTO dto = new FiscalNoteDTO(null, "40791470822", 100.00, LocalDate.now());
        return toJson(dto);
    }

    private String createFiscalNoteWithInvalidCNPJ() {
        FiscalNoteDTO dto = new FiscalNoteDTO("100", "12791298000100", 100.00, LocalDate.now());
        return toJson(dto);
    }

    private String createFiscalNoteWithMandatoryFields() {
        CNPJ cnpj = new HospitalCancerBarretos().cnpj();
        FiscalNoteDTO dto = new FiscalNoteDTO("100", cnpj.valueAsString(), 100.00, LocalDate.now());
        return toJson(dto);
    }

    private String toJson(FiscalNoteDTO dto) {
        JsonSerializer serializer = new JsonSerializer();
        return serializer.toJson(dto);
    }

}
