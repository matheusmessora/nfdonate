package br.com.pandox.nfdonate.domain.postalAddress.service;

import br.com.pandox.nfdonate.domain.cep.CEP;
import br.com.pandox.nfdonate.domain.postalAddress.PostalAddressNotFound;
import br.com.pandox.nfdonate.domain.postalAddress.PostalAddress;

public interface PostalAddressService {

    /**
     * Busca um Endereco Postal dado o CEP informado.<br />
     * @param cep CEP para realizar a pesquisa
     * @return null caso nenhum endereco tenha sido encontrado
     * @throws PostalAddressNotFound caso o CEP nao seja encontrado no repositorio
     */
    PostalAddress find(CEP cep) throws PostalAddressNotFound;
}
