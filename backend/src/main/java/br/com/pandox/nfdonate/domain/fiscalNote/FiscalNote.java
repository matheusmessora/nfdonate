package br.com.pandox.nfdonate.domain.fiscalNote;

import br.com.pandox.nfdonate.domain.UUID;
import br.com.pandox.nfdonate.domain.behaviour.KeyValuePrint;
import br.com.pandox.nfdonate.domain.money.MonetaryValue;
import br.com.pandox.nfdonate.domain.socialEntity.SocialEntity;

import java.time.LocalDate;

public interface FiscalNote extends KeyValuePrint {

    UUID id();

    LocalDate purchaseDate();

    COO coo();

    MonetaryValue value();

    SocialEntity socialEntity();

}
