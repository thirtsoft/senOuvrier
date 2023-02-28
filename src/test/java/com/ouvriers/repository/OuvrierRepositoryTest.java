package com.ouvriers.repository;

import com.ouvriers.models.Locality;
import com.ouvriers.models.Metier;
import com.ouvriers.models.Ouvrier;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.annotation.Rollback;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyLong;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class OuvrierRepositoryTest {

    @Autowired
    private OuvrierRepository ouvrierRepository;

    @Autowired
    private LocalityRepository localityRepository;

    @Autowired
    private MetierRepository metierRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveOuvrierTest() {
        Locality locality = new Locality();
        locality.setRue("233");
        locality.setName("Hann-Mariste");
        locality.setDataCreate(new Date());
        localityRepository.save(locality);
        Metier metier = metierRepository.save(new Metier(1L, "Metier01", "Metier01", "Metier01", "Metier01"));
        Ouvrier ouvrier = new Ouvrier();
        ouvrier.setReference("Plombier");
        ouvrier.setFirstName("Tairou");
        ouvrier.setLastName("Diallo");
        ouvrier.setEmail("thirdiallo@gmail.com");
        ouvrier.setDescription("Plombier genial");
        ouvrier.setDisponibity("Immediate");
        ouvrier.setMobilite("779440310");
        ouvrier.setLocality(locality);
        ouvrier.setMetier(metier);
        ouvrierRepository.save(ouvrier);

        assertThat(ouvrier.getId()).isGreaterThan(0);

        String firstName = "Tairou";

        assertThat(firstName)
                .isEqualTo(ouvrier.getFirstName());

    }

    @Test
    @Order(2)
    @Rollback(value = false)
    public void updateOuvrierTest() {
        Optional<Ouvrier> optionalOuvrier = ouvrierRepository.findById(1L);
        if (optionalOuvrier.isPresent()) {
            Ouvrier ouvrier = optionalOuvrier.get();
            ouvrier.setReference("Electricite");

            Ouvrier ouvrierUpdated = ouvrierRepository.save(ouvrier);

            Assertions.assertThat(ouvrierUpdated.getReference()).isEqualTo("Electricite");
        }

    }

    /*
    @Test
    @Order(2)
    @Rollback(value = false)
    public void saveOuvrierWithFilesTest(String ouvrier,
                                     MultipartFile photoOuvrier,
                                     MultipartFile cvOuvrier) throws IOException {

        Ouvrier ouvrierMapper = new ObjectMapper().readValue(ouvrier, Ouvrier.class);

        ouvrierMapper.setPhotoOuvrier(photoOuvrier.getOriginalFilename());
        ouvrierMapper.setCvOuvrier(cvOuvrier.getOriginalFilename());
        ouvrierRepository.save(ouvrierMapper);
        assertThat(ouvrierMapper.getId()).isGreaterThan(0);

    }
    */

    @Test
    @Order(3)
    public void getOuvrierByIdTest() {
        Ouvrier ouvrier = new Ouvrier();
        ouvrier.setId(1L);
        ouvrier.setFirstName("Tairou");
        ouvrier.setLastName("Diallo");
        ouvrierRepository.save(ouvrier);

        Ouvrier optionalOuvrier = ouvrierRepository.findById(ouvrier.getId()).get();

        assertThat(optionalOuvrier.getId()).isEqualTo(ouvrier.getId());

    }

    @Test
    @Order(4)
    public void getListOfOuvriersTest() {
        List<Ouvrier> ouvrierList = ouvrierRepository.findAll();
        assertThat(ouvrierList.size()).isNotNull();
    }

    @Test
    @Order(5)
    public void getListOfOuvriersByIdDescTest() {
        List<Ouvrier> ouvrierList = ouvrierRepository.findOuvrierByOrderByIdDesc();
        assertThat(ouvrierList.size()).isNotNull();
    }

    @Test
    @Order(6)
    public void getListOuvrierBySelectedTest() {
        List<Ouvrier> ouvrierList = ouvrierRepository.findOuvrierBySelected();
        assertThat(ouvrierList.size()).isNotNull();
    }

    @Test
    @Order(7)
    public void getListOfOuvriersByMetierTest() {
        Metier metier = metierRepository.save(new Metier(1L, "Met01", "Met01", "Met01", "Met01"));
        List<Ouvrier> ouvrierList = ouvrierRepository.findListOuvriersByMetier(metier.getId());
        assertThat(ouvrierList.size()).isNotNull();
    }

    @Test
    @Order(8)
    public void getListOfOuvriersByKeywordTest() {
        String keyword = "Macon";
        List<Ouvrier> ouvrierList = ouvrierRepository.findListOfOuvriersByKeyword(keyword);
        assertThat(ouvrierList.size()).isNotNull();
    }

    @Test
    @Order(9)
    public void getListOfOuvriersByDisponibilityTest() {
        String disponibility = "Plein temps";
        List<Ouvrier> ouvrierList = ouvrierRepository.findListOfOuvrierByDisponibility(disponibility);
        assertThat(ouvrierList.size()).isLessThan(4);
    }

    @Test
    @Order(10)
    public void countNumbersOfOuvriersTest() {
        BigDecimal nbre = ouvrierRepository.countNumberOfOuvriers();
        assertThat(nbre).isNotNull();
    }

    @Test
    @Order(11)
    public void countNumberOfOuvrierByMonthTest() {
        List<?> ouvrierList = ouvrierRepository.countNumberOfOuvrierPeerMonth();
        assertThat(ouvrierList.size()).isLessThan(4);
    }

    @Test
    @Order(12)
    public void countNumberOfOuvrierByYearTest() {
        List<?> ouvrierList = ouvrierRepository.countNumberOfOuvriersPeerYear();
        assertThat(ouvrierList.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(13)
    public void getOuvriersByPageableTest() {
        Pageable pageable = null;
        Page<Ouvrier> ouvrierPage = ouvrierRepository.findOuvriersByPageable(pageable);
        assertThat(ouvrierPage.getContent().size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(14)
    public void getOuvriersByKeywordByPageableTest() {
        Pageable pageable = null;
        String keyword = "mc";
        Page<Ouvrier> ouvrierPage = ouvrierRepository.findOuvriersByKeywordByPageable(keyword, pageable);
        assertThat(ouvrierPage.getContent().size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(15)
    public void getOuvriersByLocalityPageablesTest() {
        Locality locality = new Locality();
        locality.setRue("233");
        locality.setName("Hann-Mariste");
        locality.setDataCreate(new Date());
        localityRepository.save(locality);

        Pageable pageable = null;
        Page<Ouvrier> ouvrierPage = ouvrierRepository.findAllOuvriersByAddressId(locality.getId(), pageable);

        assertThat(ouvrierPage.getContent().size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(16)
    public void getOuvriersByMetierPageablesTest() {
        Metier metier = new Metier();
        metier.setReference("233");
        metier.setDesignation("Hann-Mariste");
        metier.setDescription("Desc");
        metierRepository.save(metier);

        Pageable pageable = null;
        Page<Ouvrier> ouvrierPage = ouvrierRepository.findOuvriersByMetierPageables(metier.getId(), pageable);

        assertThat(ouvrierPage.getContent().size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(17)
    public void getAllOuvrierDtosTest() {
        int page = 1;
        int size = 1;
        Pageable pageable = PageRequest.of(page, size);

        List<Ouvrier> ouvrierList = ouvrierRepository.findAll(pageable).getContent();

        assertThat(ouvrierList.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(18)
    public void getAllOuvrierDtosByIdAddressTest() {
        int page = 1;
        int size = 1;
        Long id = 1L;
        Pageable pageable = PageRequest.of(page, size);
        List<Ouvrier> ouvrierList = ouvrierRepository.findAllOuvriersByAddressId(id, pageable).getContent();

        assertThat(ouvrierList.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(19)
    public void getAllOuvriersByLocalityIdTest() {
        int page = 1;
        int size = 1;
        Long locId = 1L;
        Pageable pageable = PageRequest.of(page, size);
        List<Ouvrier> ouvrierList = ouvrierRepository.findAllOuvriersByLocalityId(locId, pageable).getContent();

        assertThat(ouvrierList.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(20)
    public void getAllOuvriersByMetierIdTest() {
        int page = 1;
        int size = 1;
        Long metId = 1L;
        Pageable pageable = PageRequest.of(page, size);
        List<Ouvrier> ouvrierList = ouvrierRepository.findAllOuvriersByMetierId(metId, pageable).getContent();

        assertThat(ouvrierList.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(21)
    public void getAllOuvrierDtosByKeyTest() {
        int page = 1;
        int size = 1;
        String disponibility = "Immediate";
        Pageable pageable = PageRequest.of(page, size);
        List<Ouvrier> ouvrierList = ouvrierRepository.findByDisponibityContaining(disponibility, pageable).getContent();

        assertThat(ouvrierList.size()).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(22)
    public void getAllOuvrierDtosSizeTest() {
        long nbre = ouvrierRepository.count();
        assertThat(nbre).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(23)
    public void getOuvriersDtosByAddressIdLengthTest() {
        Long id = 1L;
        long nbre = ouvrierRepository.getOuvrierLengthByAddressId(id);
        assertThat(nbre).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(24)
    public void getOuvriersByLocalityIdLengthTest() {
        Long id = 1L;
        long nbre = ouvrierRepository.getOuvrierLengthByLocalityId(id);
        assertThat(nbre).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(25)
    public void getOuvriersDtosByMetierIdLengthTest() {
        Long id = 1L;
        long nbre = ouvrierRepository.getOuvrierLengthByMetierId(id);
        assertThat(nbre).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(26)
    public void getOuvrierDtosSizeByKeyTest() {
        String keyword = "mc";
        long nbre = ouvrierRepository.getOuvrierSizeByKey(keyword);
        assertThat(nbre).isGreaterThanOrEqualTo(0);
    }

    @Test
    @Order(27)
    @Rollback(value = false)
    public void deleteOuvrierTest() {
        Optional<Ouvrier> optionalOuvrier = ouvrierRepository.findById(1L);
        if (optionalOuvrier.isPresent()) {
            ouvrierRepository.delete(optionalOuvrier.get());
        }

        Ouvrier ouvrier1 = null;

        Optional<Ouvrier> optionalOuvrier1 = ouvrierRepository.findById(1L);

        if (optionalOuvrier1.isPresent()) {
            ouvrier1 = optionalOuvrier1.get();
        }

        Assertions.assertThat(ouvrier1).isNull();
    }


}
