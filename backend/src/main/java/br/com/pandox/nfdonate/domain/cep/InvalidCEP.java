package br.com.pandox.nfdonate.domain.cep;

public class InvalidCEP extends RuntimeException {
    public InvalidCEP(String message) {
        super(message);
    }
}
