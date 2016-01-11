package br.com.pandox.nfdonate.domain;

public class CNPJ {

	private final String cnpj;

	private CNPJ(String cnpj) {
		this.cnpj = cnpj;
	}

	public static CNPJ of(String cnpj) {
		return new CNPJ(cnpj);
	}

	public String valueAsString() {
		return cnpj;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		CNPJ cnpj1 = (CNPJ) o;

		return cnpj != null ? cnpj.equals(cnpj1.cnpj) : cnpj1.cnpj == null;

	}

	@Override
	public int hashCode() {
		return cnpj != null ? cnpj.hashCode() : 0;
	}
}
