package br.com.pandox.nfdonate.boot.database;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("staging")
public class DatabaseInitializer {

//    @Autowired
//    private AddressRepository repository;
//
//    @PostConstruct
//    public void init() {
//        AddressEntity entity = new AddressEntity("01535001", "Rua Paulo Orozimbo - de 629/630 ao fim", "Cambuci", new City("Sao Paulo", "SP"));
//        repository.save(entity);
//
//        entity = new AddressEntity("01504001", "Rua Vergueiro - até 1289 - lado ímpar", "Liberdade", new City("Sao Paulo", "SP"));
//        repository.save(entity);
//
//        entity = new AddressEntity("01415001", "Rua Bela Cintra", "Consolação", new City("Sao Paulo", "SP"));
//        repository.save(entity);
//
//        entity = new AddressEntity("77500000", null, null, new City("Porto Nacional", "TO"));
//        repository.save(entity);
//    }

}
