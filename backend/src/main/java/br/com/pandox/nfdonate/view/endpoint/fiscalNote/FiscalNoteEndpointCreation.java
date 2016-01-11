package br.com.pandox.nfdonate.view.endpoint.fiscalNote;

import br.com.pandox.nfdonate.domain.fiscalNote.dto.FiscalNoteDTO;
import br.com.pandox.nfdonate.domain.fiscalNote.service.FiscalNoteService;
import br.com.pandox.nfdonate.domain.fiscalNote.service.LoggerFiscalNoteService;
import br.com.pandox.nfdonate.domain.userAddress.service.UserAddressServiceImpl;
import br.com.pandox.nfdonate.view.endpoint.ErroDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping
public class FiscalNoteEndpointCreation {

    @Autowired
    private FiscalNoteService service;

    @RequestMapping(value = FiscalNoteDTO.URI, method = RequestMethod.POST)
    public ResponseEntity create(@RequestBody FiscalNoteDTO dto) {
        service.create(dto);
        return parseToResponse();
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErroDTO postalAddressNotFoundHandler(IllegalArgumentException ex, HttpServletResponse response) {
        return new ErroDTO("invalid_fiscal_note", "Nota Fiscal invalida.");
    }

    private ResponseEntity parseToResponse() {
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
