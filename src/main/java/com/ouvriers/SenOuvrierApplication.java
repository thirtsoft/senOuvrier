package com.ouvriers;

import com.ouvriers.models.*;
import com.ouvriers.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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

	@Autowired
	private AddresseRepository addresseRepository;
	@Autowired
	private MetierRepository metierRepository;
	@Autowired
	private OuvrierRepository ouvrierRepository;
	@Autowired
	private RecruteurRepository recruteurRepository;
	@Autowired
	private TarifRepository tarifRepository;
	@Autowired
	private VilleRepository villeRepository;
	@Autowired
	private WhistListRepository whistListRepository;
	@Autowired
	private AnnonceRepository annonceRepository;


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


	// @Bean
  /*  public BCryptPasswordEncoder getBCPE() {
        return new BCryptPasswordEncoder();
    }
*/
	@Override
	public void run(String... args) throws Exception {

		Metier m1 = metierRepository.save(new Metier(1L, "elec", "ELECTRICIEN", "Electricité"));
		Metier m2 = metierRepository.save(new Metier(2L, "pl", "PLOMBIER", "plomberie"));
		Metier m3 = metierRepository.save(new Metier(3L, "frig", "FRIGORISTE", "frigo"));
		Metier m4 = metierRepository.save(new Metier(4L, "mec", "MECANICIEN", "mécanique"));
		Metier m5 = metierRepository.save(new Metier(5L, "car", "CARROLEUR", "carrolerie"));
		Metier m6 = metierRepository.save(new Metier(6L, "tech", "TECHNICIEN SURFACE", "technicien"));
		Metier m7 = metierRepository.save(new Metier(7L, "mac", "MACON", "maçonnerie"));
		Metier m8 = metierRepository.save(new Metier(8L, "charp", "CHARPANTIER", "charp"));
		Metier m9 = metierRepository.save(new Metier(9L, "peint", "PEINTRE", "Peint"));
		Metier m10 = metierRepository.save(new Metier(10L, "chauff", "CHAUFFEUR", "chauff"));

		Addresse a1 = addresseRepository.save(new Addresse(1L, "man", "Hann-Mariste", "rue 250", "233", "Dakar", "Senegal"));
		Addresse a2 = addresseRepository.save(new Addresse(2L, "sac", "Sacré Coeur", "rue 250", "233", "Dakar", "Senegal"));
		Addresse a3 = addresseRepository.save(new Addresse(3L, "al", "Almadies", "rue 250", "233", "Dakar", "Senegal"));
		Addresse a4 = addresseRepository.save(new Addresse(4L, "pa", "Parcelle", "rue 250", "233", "Dakar", "Senegal"));

		Ouvrier o1 = ouvrierRepository.save(new Ouvrier(1L, "PLOMBIER", "tairou","diallo","Masculin","Hann-Mariste","thirdiallo@gmail.com","779440310","1 à 2ans",400000,"Full-Time","DK-TH-ZG","photo1.jpg","cv1.pdf",m1,a1));
		Ouvrier o2 = ouvrierRepository.save(new Ouvrier(2L, "ELECTRICIEN", "Saliou","diallo","Masculin","Hann-Mariste","thirdiallo@gmail.com","779440310","1 à 2ans",400000,"Full-Time","DK-TH-ZG","photo1.jpg","cv1.pdf",m1,a1));
		Ouvrier o3 = ouvrierRepository.save(new Ouvrier(3L, "FRIGORISTE", "fatou","ndiaye","Feminin","Hann-Mariste","thirdiallo@gmail.com","779440310","1 à 2ans",400000,"Full-Time","DK-TH-ZG","photo1.jpg","cv1.pdf",m1,a1));
		Ouvrier o4 = ouvrierRepository.save(new Ouvrier(4L, "CARROLEUR", "adama","diallo","Masculin","Keur-Massar","thirdiallo@gmail.com","779440310","1 à 2ans",400000,"Full-Time","DK-TH-ZG","photo1.jpg","cv1.pdf",m1,a1));
		Ouvrier o5 = ouvrierRepository.save(new Ouvrier(5L, "PEINTRE", "ndeye","gueye","Feminin","Parcelle","thirdiallo@gmail.com","779440310","1 à 2ans",400000,"Full-Time","DK-TH-ZG","photo1.jpg","cv1.pdf",m1,a1));
		Ouvrier o6 = ouvrierRepository.save(new Ouvrier(6L, "MACON", "fallou","tine","Masculin","Guédiawaye","thirdiallo@gmail.com","779440310","1 à 2ans",400000,"Full-Time","DK-TH-ZG","photo1.jpg","cv1.pdf",m1,a1));
		Ouvrier o7 = ouvrierRepository.save(new Ouvrier(7L, "MECANICIEN", "awa","diouf","Feminin","Almadies","thirdiallo@gmail.com","779440310","1 à 2ans",400000,"Full-Time","DK-TH-ZG","photo1.jpg","cv1.pdf",m1,a1));

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
		an1.setEmailPoste("an1@gmail.com");an1.setMetier(m1);an1.setRecruteur(r1);
		an2.setId(2L);an2.setReference("el");an1.setLibelle("ELECTRICIEN");an2.setAnneeExperience("1 à 2ans");
		an2.setEmailPoste("an2@gmail.com");an2.setMetier(m2);an1.setRecruteur(r2);
		an3.setId(3L);an3.setReference("frig");an3.setLibelle("FRIGORISTE");an3.setAnneeExperience("15 à 25ans");
		an3.setEmailPoste("an3@gmail.com");an3.setMetier(m3);an3.setRecruteur(r3);
		an4.setId(4L);an4.setReference("pt");an4.setLibelle("PEINTRE");an4.setAnneeExperience("10 à 15ans");
		an4.setEmailPoste("an4@gmail.com");an4.setMetier(m4);an4.setRecruteur(r4);
		an5.setId(5L);an5.setReference("mec");an5.setLibelle("MECANICIEN");an5.setAnneeExperience("5 à 10ans");
		an5.setEmailPoste("an5@gmail.com");an5.setMetier(m5);an5.setRecruteur(r5);

		annonceRepository.save(an1); annonceRepository.save(an2); annonceRepository.save(an3);
		annonceRepository.save(an4); annonceRepository.save(an5);

		Tarif t1 = tarifRepository.save(new Tarif(1L,"tarif1",30L, "tarif1",an1));
		Tarif t2 = tarifRepository.save(new Tarif(2L,"tarif2",40L, "tarif1",an2));
		Tarif t3 = tarifRepository.save(new Tarif(3L,"tarif3",50L, "tarif1",an3));
		Tarif t4 = tarifRepository.save(new Tarif(4L,"tarif4",70L, "tarif1",an4));

		Ville v1 = villeRepository.save(new Ville(1L, "ville1", "ville1","ville1"));
		Ville v2 = villeRepository.save(new Ville(2L, "ville2", "ville2","ville2"));
		Ville v3 = villeRepository.save(new Ville(3L, "ville3", "ville3","ville3"));
		Ville v4 = villeRepository.save(new Ville(4L, "ville4", "ville4","ville4"));



/*
        accountService.saveUtilisateur(new Utilisateur(null, "admin", "1234", true, null));
        accountService.saveUtilisateur(new Utilisateur(null, "user", "1234", true, null));


        accountService.saveRole(new Role(null, "ADMIN"));
        accountService.saveRole(new Role(null, "USER"));

        accountService.addRoleToUtilisateur("admin", "ADMIN");
        accountService.addRoleToUtilisateur("admin", "USER");
        accountService.addRoleToUtilisateur("user", "USER");
*/


       /* Role useRole = new Role(RoleName.ROLE_USER);
        Role vendorRole = new Role(RoleName.ROLE_VENDEUR);
        Role adminRole = new Role(RoleName.ROLE_ADMIN);
        roleRepository.save(useRole);
        roleRepository.save(vendorRole);
        roleRepository.save(adminRole);*/

	}

}
