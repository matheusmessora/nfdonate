package br.com.pandox.nfdonate.view.endpoint.userAddress;

import br.com.pandox.nfdonate.domain.userAddress.UserAddress;

public class UserAddressDTO {

    private Long id;
    private Long idUser;
    private String cep;

    private String address;
    private String city;
    private String uf;
    private String district;
    private int number;
    private String complement;

    public UserAddressDTO() {
    }

    public UserAddressDTO(UserAddress userAddress) {
        this.id = userAddress.id();
        this.idUser = userAddress.idUser();
        this.address = userAddress.address().address();
        this.district = userAddress.address().district();
        this.city = userAddress.address().city().getDescription();
        this.uf = userAddress.address().city().getUf();
        this.number = userAddress.number();
        this.complement = userAddress.complement();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }


    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }
}
