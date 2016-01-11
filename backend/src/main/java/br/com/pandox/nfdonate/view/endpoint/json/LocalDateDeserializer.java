package br.com.pandox.nfdonate.view.endpoint.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateDeserializer extends JsonDeserializer<LocalDate> {

    @Override
    public LocalDate deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String value = jsonParser.getText();

	    return getLocalDate(value);
    }

	LocalDate getLocalDate(String value) {
		DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
		return LocalDate.parse(value, formatter);
	}
}
