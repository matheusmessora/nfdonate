package br.com.pandox.nfdonate.domain.fiscalNote.service;

import br.com.pandox.nfdonate.domain.fiscalNote.FiscalNote;
import br.com.pandox.nfdonate.domain.fiscalNote.dto.FiscalNoteDTO;
import br.com.pandox.nfdonate.infrastructure.repository.fiscalNote.SimpleFiscalNote;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.omg.CORBA.Object;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoggerFiscalNoteService implements FiscalNoteService {

    private static final Logger logger = LogManager.getLogger("FileLogger");

    @Override
    public FiscalNote create(FiscalNoteDTO dto) {
        FiscalNote fiscalNote = new SimpleFiscalNote(dto.coo, dto.cnpj, dto.purchaseDate, dto.value);

        try {
            logger.info(fiscalNote.toKeyValue());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return fiscalNote;
    }
}
