package br.com.pandox.nfdonate.domain.fiscalNote;

import br.com.pandox.nfdonate.domain.UUID;
import br.com.pandox.nfdonate.domain.socialEntity.SocialEntity;

import java.time.LocalDate;
import java.util.Currency;

public interface FiscalNote {

    UUID id();

    LocalDate purchaseDate();

    COO coo();

    Currency value();

    SocialEntity socialEntity();

}
