package br.com.pandox.nfdonate.infrastructure.repository.fiscalNote;

import br.com.pandox.nfdonate.domain.UUID;
import br.com.pandox.nfdonate.domain.fiscalNote.COO;
import br.com.pandox.nfdonate.domain.fiscalNote.FiscalNote;
import br.com.pandox.nfdonate.domain.money.MonetaryValue;
import br.com.pandox.nfdonate.domain.socialEntity.SocialEntity;
import com.google.common.base.Strings;

import java.time.LocalDate;

public class SimpleFiscalNote implements FiscalNote {

    private final COO coo;
    private final String cnpj;
    private final LocalDate purchaseDate;
    private final MonetaryValue monetaryValue;

    public SimpleFiscalNote(String coo, String cnpj, LocalDate purchaseDate, Double value) {
        if (Strings.isNullOrEmpty(coo) || Strings.isNullOrEmpty(cnpj) || purchaseDate ==  null || value == null) {
            throw new IllegalArgumentException();
        }

        this.coo = COO.from(coo);
        this.cnpj = cnpj;
        this.purchaseDate = purchaseDate;
        this.monetaryValue =  MonetaryValue.of(value);
    }

    @Override
    public UUID id() {
        return null;
    }

    @Override
    public LocalDate purchaseDate() {
        return purchaseDate;
    }

    @Override
    public COO coo() {
        return coo;
    }

    @Override
    public MonetaryValue value() {
        return monetaryValue;
    }

    @Override
    public SocialEntity socialEntity() {
        return null;
    }

    @Override
    public String toKeyValue() {
        StringBuilder sb = new StringBuilder();
        sb
                .append("coo=")
                .append(coo.value())
                .append(", cnpj=")
                .append(cnpj)
                .append(", purchaseDate=")
                .append(purchaseDate)
                .append(", value=")
                .append(monetaryValue.number());
        return sb.toString();
    }
}
