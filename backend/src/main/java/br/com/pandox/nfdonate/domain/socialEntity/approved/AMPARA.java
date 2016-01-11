package br.com.pandox.nfdonate.domain.socialEntity.approved;

import br.com.pandox.nfdonate.domain.CNPJ;
import br.com.pandox.nfdonate.domain.SimpleUUID;
import br.com.pandox.nfdonate.domain.UUID;

public class AMPARA extends ApprovedSocialEntity {
	@Override
	public UUID id() {
		return new SimpleUUID(3L);
	}

	@Override
	public CNPJ cnpj() {
		return CNPJ.of("12791298000184");
	}
}
