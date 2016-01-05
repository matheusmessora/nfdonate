package br.com.pandox.nfdonate.view.endpoint.fiscalNote;

import br.com.pandox.nfdonate.domain.postalAddress.PostalAddress;
import br.com.pandox.nfdonate.domain.userAddress.UserAddress;
import br.com.pandox.nfdonate.view.endpoint.postalAddress.PostalAddressDTO;
import br.com.pandox.nfdonate.view.endpoint.userAddress.UserAddressDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class FiscalNoteEndpointCreation {


    @RequestMapping(value = FiscalNoteDTO.URI, method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody FiscalNoteDTO dto) {
        return parseToResponse(null);
    }

    private ResponseEntity parseToResponse(PostalAddress postalAddress) {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
