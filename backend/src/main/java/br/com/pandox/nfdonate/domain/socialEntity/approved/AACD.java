package br.com.pandox.nfdonate.domain.socialEntity.approved;

import br.com.pandox.nfdonate.domain.CNPJ;
import br.com.pandox.nfdonate.domain.SimpleUUID;
import br.com.pandox.nfdonate.domain.UUID;

public class AACD extends ApprovedSocialEntity {
	@Override
	public UUID id() {
		return new SimpleUUID(2L);
	}

	@Override
	public CNPJ cnpj() {
		return CNPJ.of("60979457000111");
	}
}
