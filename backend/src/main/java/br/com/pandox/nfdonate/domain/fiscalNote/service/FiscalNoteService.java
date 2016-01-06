package br.com.pandox.nfdonate.domain.fiscalNote.service;

import br.com.pandox.nfdonate.domain.fiscalNote.FiscalNote;
import br.com.pandox.nfdonate.domain.fiscalNote.dto.FiscalNoteDTO;
import org.springframework.stereotype.Service;

public interface FiscalNoteService {

    FiscalNote create(FiscalNoteDTO dto);
}
