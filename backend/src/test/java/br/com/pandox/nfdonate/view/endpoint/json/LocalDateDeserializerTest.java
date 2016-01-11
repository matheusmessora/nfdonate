package br.com.pandox.nfdonate.view.endpoint.json;

import org.testng.annotations.Test;

import java.time.LocalDate;

import static org.testng.Assert.*;

public class LocalDateDeserializerTest {

	@Test
	public void deserializeStringToLocalDate(){
		String dateISO8601 = "2016-01-11T02:00:00.000Z";

		LocalDateDeserializer deserializer = new LocalDateDeserializer();
		LocalDate localDate = deserializer.getLocalDate(dateISO8601);
		assertNotNull(localDate);
	}

}