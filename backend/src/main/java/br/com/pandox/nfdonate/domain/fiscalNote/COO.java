package br.com.pandox.nfdonate.domain.fiscalNote;

import org.springframework.util.StringUtils;

public class COO {

    private String value;

    private COO(String coo) {
        this.value = coo;
    }

    public String value(){
        return value;
    }

    public static COO from(String coo) {
        return new COO(coo);
    }

}
