package br.com.pandox.nfdonate.domain.postalAddress.service;

import br.com.pandox.nfdonate.domain.cep.CEP;
import br.com.pandox.nfdonate.domain.postalAddress.PostalAddress;
import br.com.pandox.nfdonate.domain.postalAddress.PostalAddressNotFound;
import br.com.pandox.nfdonate.infrastructure.repository.address.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostalAddressServiceImpl implements PostalAddressService {

    @Autowired
    private AddressRepository repository;

    @Override
    public PostalAddress find(CEP cep) {
        PostalAddress address = findPostallAddress(cep);

        if(address == null) {
            throw new PostalAddressNotFound();
        }

        return address;
    }

    private PostalAddress findPostallAddress(CEP cep) {
        PostalAddress address = null;
        for (int lastZerosCount = 0; lastZerosCount < cep.fullCode().length(); lastZerosCount++) {
            address = repository.findByCep(cep.fullCode());
            if (address == null) {
                cep = nextCEP(cep, lastZerosCount + 1);
            }
        }
        return address;
    }

    /**
     * Gera um novo CEP com o ultimo zero a direita adicionado a partir de um CEP especifico.<br />
     * Ex. Caso seja informado 01535999, o novo CEp retornado sera 01535990.<br />
     * Caso seja informado 01535990, o novo CEP retornado sera 01535900 e assim por diante
     */
    private CEP nextCEP(CEP cep, int lastZerosCount) {
        String fullCode = cep.fullCode();

        String zeros = "";
        while (zeros.length() < lastZerosCount) {
            zeros = zeros + "0";
        }
        String cepCode = fullCode.substring(0, fullCode.length() - lastZerosCount) + zeros;
        return CEP.from(cepCode);
    }
}


