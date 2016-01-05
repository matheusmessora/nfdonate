package br.com.pandox.nfdonate.boot.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LocalDateSerializer extends JsonSerializer<LocalDate> {

    @Override
    public void serialize(LocalDate localDate, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE;
        String dateTimeAsString = localDate.format(formatter);
        jsonGenerator.writeString(dateTimeAsString);
    }
}
