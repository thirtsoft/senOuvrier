package com.ouvriers.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ouvriers.models.Address;
import com.ouvriers.services.AddressService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

import static com.ouvriers.utils.Constants.APP_ROOT;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@RunWith(SpringRunner.class)
@WebMvcTest(AddressController.class)
public class AddressControllerTest {

    @MockBean
    private AddressService addressService;

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    private AddressController addressController;

    private Address address;

    private List<Address> addressList;

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @BeforeEach
    public void setup() {
        address.setCode("SN");
        address.setName("Dakar");
        address.setCountry("SENEGAL");
        mockMvc = MockMvcBuilders.standaloneSetup(addressController).build();
    }

    @AfterEach
    void tearDown() {
        address = null;
    }

    /*
    @Test
    public void PostMappingOfCategory() throws Exception {
        when(categoryService.saveCategory(any())).thenReturn(category);
        mockMvc.perform(post("/api/categories")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(category)))
                .andExpect(status().isOk());
        verify(categoryService, times(1)).saveCategory(any());
    }
    */

    @Test
    void save_one_address() throws Exception {
        /*
        Address address = new Address();
        address.setCode("SN");
        address.setName("Dakar");
        address.setCountry("SENEGAL");*/
        when(addressService.save(any(Address.class))).thenReturn(address);

        mockMvc.perform(post(APP_ROOT + "/**/addresses/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(address)))
                .andExpect(status().isCreated());
        verify(addressService, times(1)).save(any());

    }

}
