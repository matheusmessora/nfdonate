package br.com.pandox.nfdonate.domain.userAddress.builder;

import br.com.pandox.nfdonate.domain.postalAddress.PostalAddress;
import br.com.pandox.nfdonate.domain.userAddress.UserAddress;
import br.com.pandox.nfdonate.infrastructure.repository.userAddress.UserAddressEntity;

public class UserAddressBuilder {

    public static AddressBuilderUserStep newBuilder() {
        return new Steps();
    }

    public interface AddressBuilderUserStep {

        AddressBuilderCepStep withUser(Long idUser);

        AddressBuilderUserStep withId(Long userAddressID);
    }

    public interface AddressBuilderCepStep {

        /**
         * Constroi um endereco dado um CEP
         * @param cep
         * @return
         */
        AddressBuilderNumberStep withPostalAddress(PostalAddress cep);
    }

    public interface AddressBuilderNumberStep {
        /**
         * Metodo <b>Opcional</b>.<br />
         * Insere o numero do endereco
         * @param number
         * @return
         */
        AddressBuilderFinalStep withNumber(int number);

    }

    public interface AddressBuilderFinalStep {


        /**
         * Metodo <b>Opcional</b>.<br />
         * Insere o complemento do endereco
         * @param complement
         * @return
         */
        AddressBuilderFinalStep withComplement(String complement);


        UserAddress build();
    }


    private static class Steps implements AddressBuilderFinalStep, AddressBuilderCepStep, AddressBuilderUserStep, AddressBuilderNumberStep {

        private int number;
        private String complement;
        private PostalAddress postalAddress;
        private Long iduser;
        private Long userAddressID;

        @Override
        public AddressBuilderNumberStep withPostalAddress(PostalAddress postalAddress) {
            this.postalAddress = postalAddress;
            return this;
        }

        @Override
        public AddressBuilderFinalStep withNumber(int number) {
            this.number = number;
            return this;
        }

        @Override
        public AddressBuilderFinalStep withComplement(String complement) {
            this.complement = complement;
            return this;
        }

        @Override
        public UserAddress build() {
            return new UserAddressEntity(userAddressID, iduser, postalAddress, number, complement);
        }

        @Override
        public AddressBuilderCepStep withUser(Long idUser) {
            this.iduser = idUser;
            return this;
        }

        @Override
        public AddressBuilderUserStep withId(Long userAddressID) {
            this.userAddressID = userAddressID;
            return this;
        }
    }
}
