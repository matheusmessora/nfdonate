package br.com.pandox.nfdonate.domain.userAddress;

import br.com.pandox.nfdonate.domain.cep.CEP;
import br.com.pandox.nfdonate.domain.postalAddress.PostalAddress;

public interface UserAddress {

    Long id();

    Long idUser();

    CEP cep();

    PostalAddress address();

    Integer number();

    String complement();
}
