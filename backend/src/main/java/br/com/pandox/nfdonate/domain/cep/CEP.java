package br.com.pandox.nfdonate.domain.cep;

import org.springframework.util.StringUtils;

/**
 * Value Object de CÓDIGO DE ENDEREÇAMENTO POSTAL<br /><br />
 * <a href="http://martinfowler.com/bliki/ValueObject.html">Value Object - Martin Fowler</a>
 * <br /><br />
 * Esta classe tem como base de conhecimento <br />a descricao na <a href="https://pt.wikipedia.org/wiki/C%C3%B3digo_de_Endere%C3%A7amento_Postal">Wikipedia</a>.<br />
 *
 */
public final  class CEP {

    private String prefix;
    private String suffix;

    private CEP(String cep) {
        cep = checkIntegrity(cep);

        suffix = cep.substring(5);
        prefix = cep.substring(0, 5);
    }

    /**
     * Instancia um novo {@link CEP}. Para ser valido ele deve possuir 8 digitos. Apenas o caracter - (traço) eh aceito<br />
     * Este metodo pode lancar um {@link IllegalArgumentException} caso o parametro esteja incorreto
     * @param cep
     * @return
     */
    public static CEP from(String cep) {
        if(StringUtils.isEmpty(cep)){
            invalidCep(cep);
        }
        return new CEP(cep);
    }

    /**
     * Retorna apenas o sufixo do CEP.<br />
     Ex. retorna 001 caso o cep informado seja 01535001
     * @return 001
     */
    public String suffix() {
        return suffix;
    }

    public String prefix() {
        return prefix;
    }

    /**
     * Retorna o codigo do CEP sem o traço. <br />
     * Ex. 01535001
     * @return 01535001
     */
    public String fullCode() {
        return prefix() + suffix();
    }

    private static void invalidCep(String cep) {
        throw new InvalidCEP("CEP [" + cep + "] invalido");
    }

    private String checkIntegrity(String cep) {
        cep = cep.replace("-", "");
        if(cep.length() != 8) {
            invalidCep(cep);
        }
        if(!hasOnlyNumbers(cep)){
            invalidCep(cep);
        }
        return cep;
    }

    private boolean hasOnlyNumbers(String cep) {
        String regex = "^\\d+$";
        return cep.matches(regex);
    }

}
