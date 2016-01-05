package br.com.pandox.nfdonate.domain.socialEntity;

import br.com.pandox.nfdonate.domain.CNPJ;
import br.com.pandox.nfdonate.domain.UUID;

public interface SocialEntity {

    UUID id();

    CNPJ cnpj();
}
