package br.com.pandox.nfdonate.domain.postalAddress.service;

import br.com.pandox.nfdonate.infrastructure.repository.address.AddressRepository;
import br.com.pandox.nfdonate.infrastructure.repository.district.City;
import br.com.pandox.nfdonate.domain.cep.CEP;
import br.com.pandox.nfdonate.domain.postalAddress.PostalAddress;
import br.com.pandox.nfdonate.domain.postalAddress.PostalAddressNotFound;
import br.com.pandox.nfdonate.infrastructure.repository.address.AddressEntity;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

public class PostalUserAddressServiceTest {

    @InjectMocks
    PostalAddressServiceImpl service;

    @Mock
    AddressRepository addressRepository;

    @BeforeMethod
    public void init(){
        service = new PostalAddressServiceImpl();
        MockitoAnnotations.initMocks(this);
    }

    @Test(expectedExceptions = PostalAddressNotFound.class)
    public void should_throw_exception_when_address_does_not_exist(){
        CEP cep = CEP.from("01535001");
        when(addressRepository.findByCep(anyString())).thenReturn(null);

        service.find(cep);
    }

    @Test
    public void should_return_postal_address_from_correct_CEP(){
        CEP cep = CEP.from("01535001");
        AddressEntity mock = new AddressEntity("01535001", "Rua Paulo Orozimbo", "Cambuci", new City("Sao Paulo", "SP"));
        mock.setId(1L);
        when(addressRepository.findByCep(eq(cep.fullCode()))).thenReturn(mock);

        PostalAddress postalAddress = service.find(cep);

        assertEquals(postalAddress.address(), "Rua Paulo Orozimbo");
    }

    @Test
    public void should_return_district_postal_address_from_correct_CEP(){
        CEP cep = CEP.from("01535001");
        AddressEntity mock = new AddressEntity("01535001", "Rua Paulo Orozimbo", "Cambuci", new City("Sao Paulo", "SP"));
        mock.setId(1L);
        when(addressRepository.findByCep(eq(cep.fullCode()))).thenReturn(mock);

        PostalAddress postalAddress = service.find(cep);

        assertEquals(postalAddress.city().getDescription(), "Sao Paulo");
    }

    @Test
    public void should_return_postal_address_from_CEP_with_last_zero(){
        CEP cep = CEP.from("01535010");
        AddressEntity mock = new AddressEntity("01535010", "Rua Paulo Orozimbo", "Cambuci", new City("Sao Paulo", "SP"));
        mock.setId(1L);
        when(addressRepository.findByCep(eq(cep.fullCode()))).thenReturn(null);
        when(addressRepository.findByCep(eq("01535010"))).thenReturn(mock);

        PostalAddress postalAddress = service.find(cep);

        assertEquals(postalAddress.address(), "Rua Paulo Orozimbo");
    }

    @Test
    public void should_return_postal_address_from_CEP_with_last_double_zero(){
        CEP cep = CEP.from("01535111");
        AddressEntity mock = new AddressEntity("01535000", "Rua Paulo Orozimbo", "Cambuci", new City("Sao Paulo", "SP"));
        mock.setId(1L);
        when(addressRepository.findByCep(eq(cep.fullCode()))).thenReturn(null);
        when(addressRepository.findByCep(eq("01535111"))).thenReturn(null);
        when(addressRepository.findByCep(eq("01535110"))).thenReturn(null);
        when(addressRepository.findByCep(eq("01535100"))).thenReturn(null);
        when(addressRepository.findByCep(eq("01535000"))).thenReturn(mock);

        PostalAddress postalAddress = service.find(cep);

        assertEquals(postalAddress.address(), "Rua Paulo Orozimbo");
    }

    @Test
    public void should_return_postal_address_from_CEP_with_full_double_zero(){
        CEP cep = CEP.from("01535111");
        AddressEntity mock = new AddressEntity("00000000", "Rua Paulo Orozimbo", "Cambuci", new City("Sao Paulo", "SP"));
        mock.setId(1L);
        when(addressRepository.findByCep(eq("00000000"))).thenReturn(mock);
        when(addressRepository.findByCep(Mockito.contains("1"))).thenReturn(null);

        PostalAddress postalAddress = service.find(cep);

        assertEquals(postalAddress.address(), "Rua Paulo Orozimbo");
    }
}
