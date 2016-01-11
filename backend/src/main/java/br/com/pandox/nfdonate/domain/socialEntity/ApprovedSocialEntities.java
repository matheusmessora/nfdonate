package br.com.pandox.nfdonate.domain.socialEntity;

import br.com.pandox.nfdonate.domain.CNPJ;
import br.com.pandox.nfdonate.domain.socialEntity.approved.AACD;
import br.com.pandox.nfdonate.domain.socialEntity.approved.AMPARA;
import br.com.pandox.nfdonate.domain.socialEntity.approved.HospitalCancerBarretos;

import java.util.ArrayList;
import java.util.List;

public class ApprovedSocialEntities {

	List<SocialEntity> socialEntities;

	public ApprovedSocialEntities() {
		init();
	}

	void init() {
		HospitalCancerBarretos hospitalCancerBarretos = new HospitalCancerBarretos();
		AACD aacd = new AACD();
		AMPARA ampara = new AMPARA();

		socialEntities = new ArrayList<>();

		socialEntities.add(hospitalCancerBarretos);
		socialEntities.add(aacd);
		socialEntities.add(ampara);
	}


	public boolean isApproved(CNPJ cnpj) {
		for (SocialEntity socialEntity : socialEntities) {
			if (socialEntity.cnpj().equals(cnpj)) {
				return true;
			}
		}

		return false;
	}
}
