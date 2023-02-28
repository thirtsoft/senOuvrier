package com.ouvriers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class SenOuvrierApplication implements CommandLineRunner {

    private static final Logger LOG = LoggerFactory.getLogger(SenOuvrierApplication.class);
    //  @Autowired
    //  RoleRepository roleRepository;
    //  @Autowired
    //  PasswordEncoder encoder;
    /*
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private MetierRepository metierRepository;
    @Autowired
    private OuvrierRepository ouvrierRepository;
    @Autowired
    private VilleRepository villeRepository;
    @Autowired
    private ServiceOffertRepository serviceOffertRepository;
    @Autowired
    private UtilisateurRepository utilisateurRepository;
    */
    //  @Autowired
    //  private UtilisateurService utilisateurService;
    //   @Autowired
    //  private BCryptPasswordEncoder bCryptPasswordEncoder;


    public static void main(String[] args) {
        SpringApplication.run(SenOuvrierApplication.class, args);
        createPhotoDirectoryIfItDoesntExist();
        createCvDirectoryIfItDoesntExist();
    }

    private static void createPhotoDirectoryIfItDoesntExist() {
        Path path = Paths.get(System.getProperty("user.home") + "/senouvrier/ouvrier/photos/");

        if (Files.notExists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException ie) {
                LOG.error(String.format("Problem creating directory %s", path));
            }
        }
    }

    private static void createCvDirectoryIfItDoesntExist() {
        Path path = Paths.get(System.getProperty("user.home") + "/senouvrier/ouvrier/cvs/");

        if (Files.notExists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException ie) {
                LOG.error(String.format("Problem creating directory %s", path));
            }
        }
    }

    /*
    @Bean
    PasswordEncoder passwordEncoder() { // NEEDED TO ALLOW PASSWORD ENCODER INSIDE SECURITY
        return new BCryptPasswordEncoder();
    }
    */

    @Override
    public void run(String... args) throws Exception {
/*
		Metier m1 = metierRepository.save(new Metier(1L, "elec", "ELECTRICIEN", "photo2.jpg"));
		Metier m2 = metierRepository.save(new Metier(2L, "pl", "PLOMBIER", "photo3.jpg"));
		Metier m3 = metierRepository.save(new Metier(3L, "frig", "FRIGORISTE", "photo5.jpg"));
		Metier m4 = metierRepository.save(new Metier(4L, "mec", "MECANICIEN", "photo10.jpg"));
		Metier m5 = metierRepository.save(new Metier(5L, "car", "CARROLEUR", "photo7.jpg"));
		Metier m6 = metierRepository.save(new Metier(6L, "tech", "TECHNICIEN SURFACE", "photo12.jpg"));
		Metier m7 = metierRepository.save(new Metier(7L, "mac", "MACON", "photo8.jpg"));
		Metier m8 = metierRepository.save(new Metier(8L, "charp", "CHARPANTIER", "photo11.jpg"));
		Metier m9 = metierRepository.save(new Metier(9L, "peint", "PEINTRE", "photo13.jpg"));
		Metier m10 = metierRepository.save(new Metier(10L, "chauff", "CHAUFFEUR", "photo6.jpg"));

		Address a1 = addressRepository.save(new Address("DK", "Dakar"));
		Address a2 = addressRepository.save(new Address("ZG", "Ziguinchor"));
		Address a3 = addressRepository.save(new Address("KL", "Kaolack"));
		Address a4 = addressRepository.save(new Address("Th", "Thies"));

		Ouvrier o1 = ouvrierRepository.save(new Ouvrier(1L, "PLOMBIER", "tairou","diallo","Masculin","Hann-Mariste","thirdiallo@gmail.com","779440310","1 à 2ans",400000,"Full-Time", "Master 2 GL", true,"DK-TH-ZG","photo1.jpg","cv1.pdf",m1,a1));
		Ouvrier o2 = ouvrierRepository.save(new Ouvrier(2L, "ELECTRICIEN", "Saliou","diallo","Masculin","Hann-Mariste","thirdiallo@gmail.com","779440310","1 à 2ans",400000,"Full-Time", "Ingénierie", true,"DK-TH-ZG","photo2.jpg","cv1.pdf",m2,a1));
		Ouvrier o3 = ouvrierRepository.save(new Ouvrier(3L, "FRIGORISTE", "fatou","ndiaye","Feminin","Hann-Mariste","thirdiallo@gmail.com","779440310","1 à 2ans",400000,"Full-Time", "DESS", false,"DK-TH-ZG","photo3.jpg","cv1.pdf",m1,a1));
		Ouvrier o4 = ouvrierRepository.save(new Ouvrier(4L, "CARROLEUR", "adama","diallo","Masculin","Keur-Massar","thirdiallo@gmail.com","779440310","1 à 2ans",400000,"Full-Time", "CAP", true,"DK-TH-ZG","photo4.jpg","cv1.pdf",m2,a1));
		Ouvrier o5 = ouvrierRepository.save(new Ouvrier(5L, "PEINTRE", "ndeye","gueye","Feminin","Parcelle","thirdiallo@gmail.com","779440310","1 à 2ans",400000,"Full-Time", "DEUG", false,"DK-TH-ZG","photo5.jpg","cv1.pdf",m3,a1));
		Ouvrier o6 = ouvrierRepository.save(new Ouvrier(6L, "MACON", "fallou","tine","Masculin","Guédiawaye","thirdiallo@gmail.com","779440310","1 à 2ans",400000,"Full-Time", "BAC+7", true,"DK-TH-ZG","photo6.jpg","cv1.pdf",m3,a1));
		Ouvrier o7 = ouvrierRepository.save(new Ouvrier(7L, "MECANICIEN", "awa","diouf","Feminin","Almadies","thirdiallo@gmail.com","779440310","1 à 2ans",400000,"Full-Time", "CAP", true,"DK-TH-ZG","photo7.jpg","cv1.pdf",m1,a1));
		Ouvrier o8 = ouvrierRepository.save(new Ouvrier(8L, "PLOMBIER", "tairou","diallo","Masculin","Hann-Mariste","thirdiallo@gmail.com","779440310","1 à 2ans",400000,"Full-Time", "CAP", false,"DK-TH-ZG","photo8.jpg","cv1.pdf",m4,a1));
		Ouvrier o9 = ouvrierRepository.save(new Ouvrier(9L, "ELECTRICIEN", "Saliou","diallo","Masculin","Hann-Mariste","thirdiallo@gmail.com","779440310","1 à 2ans",400000,"Full-Time", "Technicien", true,"DK-TH-ZG","photo9.jpg","cv1.pdf",m4,a1));
		Ouvrier o10 = ouvrierRepository.save(new Ouvrier(10L, "CHARPANTIER", "fatou","ndiaye","Feminin","Hann-Mariste","thirdiallo@gmail.com","779440310","1 à 2ans",400000,"Full-Time", "Enseignant", true,"DK-TH-ZG","photo10.jpg","cv1.pdf",m5,a1));
		Ouvrier o11 = ouvrierRepository.save(new Ouvrier(11L, "CARROLEUR", "adama","diallo","Masculin","Keur-Massar","thirdiallo@gmail.com","779440310","1 à 2ans",400000,"Full-Time", "Professor", false,"DK-TH-ZG","photo11.jpg","cv1.pdf",m5,a1));
		Ouvrier o12 = ouvrierRepository.save(new Ouvrier(12L, "PEINTRE", "ndeye","gueye","Feminin","Parcelle","thirdiallo@gmail.com","779440310","1 à 2ans",400000,"Full-Time", "Elementaire", false,"DK-TH-ZG","photo12.jpg","cv1.pdf",m6,a1));
		Ouvrier o13 = ouvrierRepository.save(new Ouvrier(13L, "MACON", "fallou","tine","Masculin","Guédiawaye","thirdiallo@gmail.com","779440310","1 à 2ans",400000,"Full-Time", "Elementaire", true,"DK-TH-ZG","photo1.jpg","cv1.pdf",m7,a1));
		Ouvrier o14 = ouvrierRepository.save(new Ouvrier(14L, "MECANICIEN", "awa","diouf","Feminin","Almadies","thirdiallo@gmail.com","779440310","1 à 2ans",400000,"Full-Time", "Doctorant", false,"DK-TH-ZG","photo2.jpg","cv1.pdf",m8,a1));
		Ouvrier o15 = ouvrierRepository.save(new Ouvrier(15L, "PLOMBIER", "tairou","diallo","Masculin","Hann-Mariste","thirdiallo@gmail.com","779440310","1 à 2ans",400000,"Full-Time", "Doctorant", true,"DK-TH-ZG","photo3.jpg","cv1.pdf",m9,a1));
		Ouvrier o16 = ouvrierRepository.save(new Ouvrier(16L, "ELECTRICIEN", "Saliou","diallo","Masculin","Hann-Mariste","thirdiallo@gmail.com","779440310","1 à 2ans",400000,"Full-Time", "BAC", true,"DK-TH-ZG","photo4.jpg","cv1.pdf",m10,a1));
		Ouvrier o17 = ouvrierRepository.save(new Ouvrier(17L, "FRIGORISTE", "fatou","ndiaye","Feminin","Hann-Mariste","thirdiallo@gmail.com","779440310","1 à 2ans",400000,"Full-Time", "BAC", false,"DK-TH-ZG","photo5.jpg","cv1.pdf",m8,a1));
		Ouvrier o18 = ouvrierRepository.save(new Ouvrier(18L, "CARROLEUR", "adama","diallo","Masculin","Keur-Massar","thirdiallo@gmail.com","779440310","1 à 2ans",400000,"Full-Time", "Lycée", false,"DK-TH-ZG","photo6.jpg","cv1.pdf",m1,a1));
		Ouvrier o19 = ouvrierRepository.save(new Ouvrier(19L, "PEINTRE", "ndeye","gueye","Feminin","Parcelle","thirdiallo@gmail.com","779440310","1 à 2ans",400000,"Full-Time", "CEM", true,"DK-TH-ZG","photo7.jpg","cv1.pdf",m10,a1));
		Ouvrier o20 = ouvrierRepository.save(new Ouvrier(20L, "MACON", "fallou","tine","Masculin","Guédiawaye","thirdiallo@gmail.com","779440310","1 à 2ans",400000,"Full-Time", "BEP", true,"DK-TH-ZG","photo8.jpg","cv1.pdf",m4,a1));
		Ouvrier o21 = ouvrierRepository.save(new Ouvrier(21L, "MECANICIEN", "awa","diouf","Feminin","Almadies","thirdiallo@gmail.com","779440310","1 à 2ans",400000,"Full-Time", "BTS", true,"DK-TH-ZG","photo9.jpg","cv1.pdf",m2,a1));


		Recruteur r1 = new Recruteur();Recruteur r2 = new Recruteur(); Recruteur r3 = new Recruteur();
		Recruteur r4 = new Recruteur(); Recruteur r5 = new Recruteur();
		r1.setId(1L);r1.setFirstName("tairou");r1.setLastName("diallo");r1.setNomEntreprise("wokite");
		r1.setPhoneRecruteur("779440310"); r1.setEmail("thirdialllo@gmail.com");
		r2.setId(2L);r2.setFirstName("saliou");r2.setLastName("diallo");r2.setNomEntreprise("CREATIVE-Groupe");
		r2.setPhoneRecruteur("775440310"); r2.setEmail("thirdialllo@gmail.com");
		r3.setId(3L);r3.setFirstName("adama");r3.setLastName("diallo");r3.setNomEntreprise("seninnovation");
		r3.setPhoneRecruteur("779440310"); r3.setEmail("thirdialllo@gmail.com");
		r4.setId(4L);r4.setFirstName("ndeye");r4.setLastName("diop");r4.setNomEntreprise("expad");
		r4.setPhoneRecruteur("774640310"); r4.setEmail("diop@gmail.com");
		r5.setId(5L);r5.setFirstName("awa");r5.setLastName("gueye");r5.setNomEntreprise("shopmania");
		r5.setPhoneRecruteur("779440310"); r5.setEmail("awagueye@gmail.com");

		recruteurRepository.save(r1); recruteurRepository.save(r2); recruteurRepository.save(r3);
		recruteurRepository.save(r4); recruteurRepository.save(r5);

		Annonce an1 = new Annonce(); Annonce an2 = new Annonce(); Annonce an3 = new Annonce();
		Annonce an4 = new Annonce(); Annonce an5 = new Annonce();
		an1.setId(1L);an1.setReference("pl");an1.setLibelle("PLOMBIER");an1.setAnneeExperience("1 à 5ans");
		an1.setEmailPoste("an1@gmail.com");an1.setMetier(m1);
		an2.setId(2L);an2.setReference("el");an1.setLibelle("ELECTRICIEN");an2.setAnneeExperience("1 à 2ans");
		an2.setEmailPoste("an2@gmail.com");an2.setMetier(m2);;
		an3.setId(3L);an3.setReference("frig");an3.setLibelle("FRIGORISTE");an3.setAnneeExperience("15 à 25ans");
		an3.setEmailPoste("an3@gmail.com");an3.setMetier(m3);
		an4.setId(4L);an4.setReference("pt");an4.setLibelle("PEINTRE");an4.setAnneeExperience("10 à 15ans");
		an4.setEmailPoste("an4@gmail.com");an4.setMetier(m4);
		an5.setId(5L);an5.setReference("mec");an5.setLibelle("MECANICIEN");an5.setAnneeExperience("5 à 10ans");
		an5.setEmailPoste("an5@gmail.com");an5.setMetier(m5);

		annonceRepository.save(an1); annonceRepository.save(an2); annonceRepository.save(an3);
		annonceRepository.save(an4); annonceRepository.save(an5);

		Ville v1 = villeRepository.save(new Ville(1L, "ville1", "ville1","ville1"));
		Ville v2 = villeRepository.save(new Ville(2L, "ville2", "ville2","ville2"));
		Ville v3 = villeRepository.save(new Ville(3L, "ville3", "ville3","ville3"));
		Ville v4 = villeRepository.save(new Ville(4L, "ville4", "ville4","ville4"));

		ServiceOffert s1 = serviceOffertRepository.save(new ServiceOffert(1L, "Service01","Service01", o1));
		ServiceOffert s2 = serviceOffertRepository.save(new ServiceOffert(2L, "Service02","Service02", o2));
		ServiceOffert s3 = serviceOffertRepository.save(new ServiceOffert(3L, "Service03","Service03", o3));
		ServiceOffert s4 = serviceOffertRepository.save(new ServiceOffert(4L, "Service04","Service04", o4));
		ServiceOffert s5 = serviceOffertRepository.save(new ServiceOffert(5L, "Service05","Service05", o5));

		/*
        Role useRole = new Role(RoleName.ROLE_USER);
		Role moderatorRole = new Role(RoleName.ROLE_MODERATOR);
        Role managerRole = new Role(RoleName.ROLE_MANAGER);
        Role adminRole = new Role(RoleName.ROLE_ADMIN);
        roleRepository.save(useRole);
		roleRepository.save(moderatorRole);
		roleRepository.save(managerRole);
        roleRepository.save(adminRole);

		Utilisateur user = new Utilisateur();
		user.setId(1L);
		user.setUsername("User");
		user.setName("User");
		user.setPassword(encoder.encode("user1234"));
		utilisateurRepository.save(user);
		utilisateurService.addRoleToUser("User", RoleName.ROLE_USER);
		Utilisateur admin = new Utilisateur();
		admin.setId(3L);
		admin.setUsername("Admin");
		admin.setName("Admin");
		admin.setPassword(encoder.encode("admin1234"));
		utilisateurRepository.save(admin);
		utilisateurService.addRoleToUser("Admin", RoleName.ROLE_ADMIN);

		*/


    }

}
