package br.com.pandox.nfdonate.domain.fiscalNote.dto;

import br.com.pandox.nfdonate.view.endpoint.json.LocalDateDeserializer;
import br.com.pandox.nfdonate.view.endpoint.json.LocalDateSerializer;
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

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    public LocalDate purchaseDate;
    public String coo;
    public String cnpj;
    public Double value;




}
