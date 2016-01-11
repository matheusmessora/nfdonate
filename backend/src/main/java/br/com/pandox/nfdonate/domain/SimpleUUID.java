package br.com.pandox.nfdonate.domain;

public class SimpleUUID implements UUID {

	private final Long value;

	public SimpleUUID(Long value) {
		this.value = value;
	}

	@Override
	public Long value() {
		return value;
	}
}
