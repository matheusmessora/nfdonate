package br.com.pandox.nfdonate.view.endpoint.fiscalNote;

import br.com.pandox.nfdonate.boot.json.LocalDateDeserializer;
import br.com.pandox.nfdonate.boot.json.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.time.LocalDate;

public class FiscalNoteDTO {

    public static final String URI = "fiscal_note";

    public FiscalNoteDTO() {
    }

    public FiscalNoteDTO(String coo, String cnpj, Double value, LocalDate purchaseDate) {
        this.coo = coo;
        this.cnpj = cnpj;
        this.value = value;
        this.purchaseDate = purchaseDate;
    }

    public String coo;
    public String cnpj;
    public Double value;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    public LocalDate purchaseDate;


}
