package br.com.pandox.nfdonate.domain.behaviour;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public interface KeyValuePrint {

    String toKeyValue() throws JsonProcessingException;
}
