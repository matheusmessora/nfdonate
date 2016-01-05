package br.com.pandox.nfdonate.infrastructure.repository.address;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

    AddressEntity findByCep(String cep);
}
