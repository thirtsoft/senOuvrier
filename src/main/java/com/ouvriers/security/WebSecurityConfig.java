package com.ouvriers.security;

import com.ouvriers.security.jwt.JwtAuthEntryPoint;
import com.ouvriers.security.jwt.JwtAuthTokenFilter;
import com.ouvriers.security.service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        securedEnabled = true,
        jsr250Enabled = true,
        prePostEnabled = true)

public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtAuthEntryPoint unauthorizedHandler;

    @Bean
    public JwtAuthTokenFilter authenticationJwtTokenFilter() {
        return new JwtAuthTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }


    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost:4200");

            }
        };
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeRequests()
                .antMatchers("/**/auth/signUp").permitAll()
                .antMatchers("/**/auth/registerUser").permitAll()
                .antMatchers("/**/auth/authenticated").permitAll()

                .antMatchers("/**/ouvriers/create").permitAll()
                .antMatchers("/**/ouvriers/createWithFiles").permitAll()
                .antMatchers("/**/ouvriers/createWithFilesInFolder").permitAll()
                .antMatchers("/**/ouvriers/update/{idOuvrier}").permitAll()
                .antMatchers("/**/ouvriers/findById/{idOuvrier}").permitAll()
                .antMatchers("/**/ouvriers/searchOuvrierByReference").permitAll()
                .antMatchers("/**/ouvriers/all").permitAll()
                .antMatchers("/**/ouvriers/searchOuvrierOrderByIdDesc").permitAll()
                .antMatchers("/**/ouvriers/searchListOfOuvrierByMetier/{metierId}").permitAll()
                .antMatchers("/**/ouvriers/searchListOfOuvrierByKeyword/**").permitAll()
                .antMatchers("/**/ouvriers/searchOuvriersByDisponibilite").permitAll()
                .antMatchers("/**/ouvriers/numberOfOuvriersPeerMonth").permitAll()
                .antMatchers("/**/ouvriers/numberOfOuvriersPeerYear").permitAll()
                .antMatchers("/**/ouvriers/searchOuvrierBySelectedIsTrue").permitAll()
                .antMatchers("/**/ouvriers/NumbersOfOuvriers").permitAll()
                .antMatchers("/**/ouvriers/delete/{idProduct}").permitAll()
                .antMatchers("/**/ouvriers/photoOuvrier/{idOuvrier}").permitAll()
                .antMatchers("/**/ouvriers/photoOuvrierInFolder/{idOuvrier}").permitAll()
                .antMatchers("/**/ouvriers/uploadOuvrierPhoto/{idOuvrier}").permitAll()
                .antMatchers("/**/ouvriers/uploadPhotoOfOuvrierInFolder/{id}").permitAll()
                .antMatchers("/**/ouvriers/cvOuvrier/{idOuvrier}").permitAll()
                .antMatchers("/**/ouvriers/cvOuvrierInFolder/{idOuvrier}").permitAll()
                .antMatchers("/**/ouvriers/uploadOuvrierCv/{idOuvrier}").permitAll()
                .antMatchers("/**/ouvriers/uploadCvOfOuvrierInFolder/{id}").permitAll()
                .antMatchers("/**/ouvriers/downloadCvFileOfOuvrier/{fileName:.+}").permitAll()
                .antMatchers("/**/ouvriers/searchOuvriersByPageables/**").permitAll()
                .antMatchers("/**/ouvriers/searchOuvrierByDisponibityByPageables/**").permitAll()
                .antMatchers("/**/ouvriers/searchOuvrierByLocalityPageables/**").permitAll()
                .antMatchers("/**/ouvriers/searchOuvrierByMetierPageables/**").permitAll()
                .antMatchers("/**/ouvriers/allOuvriers").permitAll()
                .antMatchers("/**/ouvriers/address").permitAll()
                .antMatchers("/**/ouvriers/searchAllOuvriersByMetiersByPageable").permitAll()
                .antMatchers("/**/ouvriers/ouvrierKey").permitAll()
                .antMatchers("/**/ouvriers/ouvrierDtoSize").permitAll()
                .antMatchers("/**/ouvriers/ctaddressIdSize").permitAll()
                .antMatchers("/**/ouvriers/ctmetierIdSize").permitAll()
                .antMatchers("/**/ouvriers/keySize").permitAll()
                .antMatchers("/**/ouvriers/delete/{idOuvrier}").permitAll()

                .antMatchers("/**/metiers/create").permitAll()
                .antMatchers("/**/metiers/update/{idMetier}").permitAll()
                .antMatchers("/**/metiers/findById/{subCatId}").permitAll()
                .antMatchers("/**/metiers/NumbersOfmetiers").permitAll()
                .antMatchers("/**/metiers/all").permitAll()
                .antMatchers("/**/metiers/searchMetiersOrderByIdDesc").permitAll()
                .antMatchers("/**/metiers/photoMetier/{idMetier}").permitAll()
                .antMatchers("/**/metiers/photoMetierInFolder/{idMetier}").permitAll()
                .antMatchers("/**/metiers/delete/{idMetier}").permitAll()

                .antMatchers("/**/addresses/create").permitAll()
                .antMatchers("/**/addresses/update/{idAddress}").permitAll()
                .antMatchers("/**/addresses/findById/{catId}").permitAll()
                .antMatchers("/**/addresses/NumbersOfaddresses/*").permitAll()
                .antMatchers("/**/addresses/searchListOfAddressByKeyword/*").permitAll()
                .antMatchers("/**/addresses/all").permitAll()
                .antMatchers("/**/addresses/searchAlladdressesOrderByIdDesc").permitAll()
                .antMatchers("/**/addresses/searchaddressesByPageables/*").permitAll()
                .antMatchers("/**/addresses/delete/{idAdd}").permitAll()

                .antMatchers("/**/annonces/create").permitAll()
                .antMatchers("/**/annonces/update/{idAnn}").permitAll()
                .antMatchers("/**/annonces/findById/{idAnn}").permitAll()
                .antMatchers("/**/annonces/all").permitAll()
                .antMatchers("/**/annonces/searchAllAnnoncesOrderByIdDesc").permitAll()
                .antMatchers("/**/annonces/searchListOfAnnonceByMetier").permitAll()
                .antMatchers("/**/annonces/searchListOfAnnonceByKeyword/*").permitAll()
                .antMatchers("/**/annonces/searchAnnoncesByPageables/*").permitAll()
                .antMatchers("/**/annonces/NumbersOfannonces").permitAll()
                .antMatchers("/**/annonces/searchAnnonceByLocalityPageables/***").permitAll()

                .antMatchers("/**/historiqueAnnonces/create").permitAll()
                .antMatchers("/**/historiqueAnnonces/findById/{idOrderItem}").permitAll()
                .antMatchers("/**/historiqueAnnonces/all").permitAll()
                .antMatchers("/**/historiqueAnnonces/searchHistoriqueAnnonceByIdDesc").permitAll()
                .antMatchers("/**/historiqueAnnonces/NumbersOfhistoriqueAnnonces").permitAll()
                .antMatchers("/**/historiqueAnnonces/delete/{idOrderItem}").permitAll()


                .antMatchers("/**/checkout/placeToOrder").permitAll()
                .antMatchers("/**/checkout/placeToOrderWithUser/**").permitAll()
                .antMatchers("/**/checkout/placeToOrderWithLoginUser/**").permitAll()

                .antMatchers("/**/jetons/create").permitAll()
                .antMatchers("/**/jetons/update/{idJet}").permitAll()
                .antMatchers("/**/jetons/updateEtatOfJeton/{idJet}").permitAll()
                .antMatchers("/**/jetons/findById/*").permitAll()
                .antMatchers("/**/jetons/all").permitAll()
                .antMatchers("/**/jetons/searchJetonsByIdDesc").permitAll()
                .antMatchers("/**/jetons/searchJetonsByCustomerId/*").permitAll()
                .antMatchers("/**/jetons/NumbersOfjetons").permitAll()
                .antMatchers("/**/jetons/sumTotalOfJetonInYear").permitAll()
                .antMatchers("/**/jetons/sumTotalOfJetonPeerMonth").permitAll()
                .antMatchers("/**/jetons/sumTotalOfJetonPeerYear").permitAll()
                .antMatchers("/**/jetons/delete/{idJet}").permitAll()

                .antMatchers("/**/localities/create").permitAll()
                .antMatchers("/**/localities/findById/*").permitAll()
                .antMatchers("/**/localities/update/{idLoc}").permitAll()
                .antMatchers("/**/localities/all").permitAll()
                .antMatchers("/**/localities/searchAlllocalitiesOrderByIdDesc").permitAll()
                .antMatchers("/**/localities/searchLocalityByAddressCode/*").permitAll()
                .antMatchers("/**/localities/delete/*").permitAll()

                .antMatchers("/**/blogs/findById/{blogId}").permitAll()
                .antMatchers("/**/blogs/all").permitAll()
                .antMatchers("/**/blogs/searchAllBlogsOrderByIdDesc").permitAll()

                .antMatchers("/**/addresses/create").permitAll()
                .antMatchers("/**/addresses/findById/{addId}").permitAll()
                .antMatchers("/**/addresses/update/{addId}").permitAll()
                .antMatchers("/**/addresses/all").permitAll()
                .antMatchers("/**/addresses/searchAllAddressOrderByIdDesc").permitAll()
                .antMatchers("/**/addresses/delete/{addId}").permitAll()

                .antMatchers("/**/tarifs/create").permitAll()
                .antMatchers("/**/tarifs/update/{idCountry}").permitAll()
                .antMatchers("/**/tarifs/all").permitAll()
                .antMatchers("/**/tarifs/findById/{idCountry}").permitAll()
                .antMatchers("/**/tarifs/searchAllTarifsOrderByIdDesc").permitAll()
                .antMatchers("/**/tarifs/delete/{idCountry}").permitAll()

                .antMatchers("/**/states/create").permitAll()
                .antMatchers("/**/states/update/{stateId}").permitAll()
                .antMatchers("/**/states/findById/{stateId}").permitAll()
                .antMatchers("/**/states/all").permitAll()
                .antMatchers("/**/states/searchAllStatesOrderByIdDesc").permitAll()
                .antMatchers("/**/states/searchStateByCountryCode/**").permitAll()
                .antMatchers("/**/states/delete/{stateId}").permitAll()

                .antMatchers("/**/wishlists/create").permitAll()
                .antMatchers("/**/wishlists/update/{idWishlist}").permitAll()
                .antMatchers("/**/wishlists/findById/{idWishlist}").permitAll()
                .antMatchers("/**/wishlists/all").permitAll()
                .antMatchers("/**/wishlists/delete/{idWishlist}").permitAll()

                .antMatchers("/**/ratings/create").permitAll()
                .antMatchers("/**/ratings/update/{idRat}").permitAll()
                .antMatchers("/**/ratings/createWithOuvrier").permitAll()
                .antMatchers("/**/ratings/createRatingToOuvrier").permitAll()
                .antMatchers("/**/ratings/findById/{idRating}").permitAll()
                .antMatchers("/**/ratings/all").permitAll()
                .antMatchers("/**/ratings/searchAllRatingsOrderByIdDesc").permitAll()
                .antMatchers("/**/ratings/countNumberOfRatings").permitAll()
                .antMatchers("/**/ratings/countNumberOfRatingByOuvrierId/{idOuv}").permitAll()
                .antMatchers("/**/ratings/searchTop3RatingOrderByCreatedDateDesc").permitAll()
                .antMatchers("/**/ratings/searchTop4RatingOrderByCreatedDateDescByOuvrierId/{idProd}").permitAll()
                .antMatchers("/**/ratings/searchListRatingByCustomerId/{idCustomer}").permitAll()
                .antMatchers("/**/ratings/delete/{idRating}").permitAll()

                .antMatchers("/**/appointments/create").permitAll()
                .antMatchers("/**/appointments/createAppointmentToOuvrier").permitAll()
                .antMatchers("/**/appointments/update/{id}").permitAll()
                .antMatchers("/**//appointments/updateStatusOfAppointment/{id}").permitAll()
                .antMatchers("/**/appointments/findById/{idAppointment}").permitAll()
                .antMatchers("/**/appointments/numbersOfAppointments").permitAll()
                .antMatchers("/**/appointments/numbersOfAppointmentsInMonth").permitAll()
                .antMatchers("/**/appointments/numbersOfAppointmentsByStatusPending").permitAll()
                .antMatchers("/**/appointments/numbersOfAppointmentsByStatusAccepted").permitAll()
                .antMatchers("/**/appointments/numbersOfAcceptedAppointmentsInYear").permitAll()
                .antMatchers("/**/appointments/countNumberOfAppointmentByOuvrierId/{idOuv}").permitAll()
                .antMatchers("/**/appointments/countNumberOfAppointmentByCustomerId/{userId}").permitAll()
                .antMatchers("/**/appointments/countNumberOfAppointmentByCustomerIdAndStatusAccepted/{userId}").permitAll()
                .antMatchers("/**/appointments/all").permitAll()
                .antMatchers("/**/appointments/searchAllAppointmentOrderByIdDesc").permitAll()
                .antMatchers("/**/appointments/searchAllAppointmentsByStatusPending").permitAll()
                .antMatchers("/**/appointments/searchAllAppointmentsByStatusAccepted").permitAll()
                .antMatchers("/**/appointments/searchAllAppointmentsByStatusRefused").permitAll()
                .antMatchers("/**/appointments/searchAllAppointmentsByCustomerId/{userId}").permitAll()
                .antMatchers("/**/appointments/searchAllAppointmentsByOuvrierId/{ouvId}").permitAll()
                .antMatchers("/**/appointments/searchTop4AppointmentsByOuvrierId/{ouvId}").permitAll()
                .antMatchers("/**/appointments/searchTop10PendingAppointmentsOrderByIdDesc").permitAll()
                .antMatchers("/**/appointments/countNumberTotalOfAppointmentsPeerMonth").permitAll()
                .antMatchers("/**/appointments/countNumberTotalOfAppointmentsPeerYear").permitAll()
                .antMatchers("/**/appointments/delete/{idAppointment}").permitAll()


                .antMatchers("/**/prestations/create").permitAll()
                .antMatchers("/**/prestations/update/{idPrestation}").permitAll()
                .antMatchers("/**/prestations/findById/{idAppointment}").permitAll()
                .antMatchers("/**/prestations/countNumberOfPrestationByOuvrierId/{idOuv}").permitAll()
                .antMatchers("/**/prestations/all").permitAll()
                .antMatchers("/**/prestations/searchAllPrestationOrderByIdDesc").permitAll()
                .antMatchers("/**/prestations/searchAllPrestationsByOuvrierId/{ouvId}").permitAll()
                .antMatchers("/**/prestations/searchTop4PrestationsByOuvrierId/{ouvId}").permitAll()
                .antMatchers("/**/prestations/searchTop8PrestationsByOuvrierId/{ouvId}").permitAll()
                .antMatchers("/**/prestations/delete/{idPrestation}").permitAll()

                .antMatchers("/**/serviceOfferts/create").permitAll()
                .antMatchers("/**/serviceOfferts/update/{idServiceOffert}").permitAll()
                .antMatchers("/**/serviceOfferts/findById/{idServiceOffert}").permitAll()
                .antMatchers("/**/serviceOfferts/all").permitAll()
                .antMatchers("/**/serviceOfferts/searchAllServiceOffertsOrderByIdDesc").permitAll()
                .antMatchers("/**/serviceOfferts/searchAllServiceOffertsByOuvrierId/{ouvId}").permitAll()
                .antMatchers("/**/serviceOfferts/delete/{idServiceOffert}").permitAll()

                .antMatchers("/**/historiqueAppointment/create").permitAll()
                .antMatchers("/**/historiqueAppointment/update/{idHistoriqueAppointment}").permitAll()
                .antMatchers("/**/historiqueAppointment/findById/{idHistoriqueAppointment}").permitAll()
                .antMatchers("/**/historiqueAppointment/all").permitAll()
                .antMatchers("/**/historiqueAppointment/searchAllHistoriqueAppointmentByIdDesc").permitAll()
                .antMatchers("/**/historiqueAppointment/delete/{idHistoriqueAppointment}").permitAll()

                .antMatchers("/**/newsletters/create").permitAll()
                .antMatchers("/**/newsletters/update/{id}").permitAll()
                .antMatchers("/**/newsletters/findById/{idNewsletter}").permitAll()
                .antMatchers("/**/newsletters/NumbersOfNewsletters").permitAll()
                .antMatchers("/**/newsletters/searchAllNewsletersOrderByIdDesc").permitAll()
                .antMatchers("/**/newsletters/all").permitAll()
                .antMatchers("/**/newsletters/delete/{idNewsletter}").permitAll()

                .antMatchers("/**/emails/all").permitAll()
                .antMatchers("/**/emails/findById/*").permitAll()
                .antMatchers("/**/emails/searchAllEmailssOrderByIdDesc").permitAll()

                .antMatchers("/**/emails/sendEmail").permitAll()
                .antMatchers("/**/emails/sendToFournisseur").permitAll()
                .antMatchers("/**/emails/sendToNewsletter").permitAll()
                .antMatchers("/**/emails/sendMailToAllCustomers").permitAll()
                .antMatchers("/**/emails/sendMailToManager").permitAll()
                .antMatchers("/**/emails/findById/{idEmail}").permitAll()
                .antMatchers("/**/emails/searchAllEmailsOrderByIdDesc").permitAll()
                .antMatchers("/**/emails/countNumberOfEmail").permitAll()
                .antMatchers("/**/emails/delete/{idEmail}").permitAll()

                .antMatchers("/**/utilisateurs/all").permitAll()
                .antMatchers("/**/utilisateurs/findById/{idUtilisateur}").permitAll()
                .antMatchers("/**/utilisateurs/update/{idUser}").permitAll()
                .antMatchers("/**/utilisateurs/activatedUser/{id}").permitAll()
                .antMatchers("/**/utilisateurs/avatar/{id}").permitAll()
                .antMatchers("/**/utilisateurs/uploadUserPhoto/{id}").permitAll()
                .antMatchers("/**/utilisateurs/*").permitAll()
                .antMatchers("/**/utilisateurs/updateCustomerProfileByUsername").permitAll()
                .antMatchers("/**/utilisateurs/NumbersOfRecruteurs").permitAll()
                .antMatchers("/**/utilisateurs/countNumberTotalOfRegisterPeerMonth").permitAll()
                .antMatchers("/**/utilisateurs/countNumberTotalOfRegisterPeerYear").permitAll()

                .antMatchers("/**/historiqueLogins/create").permitAll()
                .antMatchers("/**/historiqueLogins/update/{idhistLog}").permitAll()
                .antMatchers("/**/historiqueLogins/findById/{idhistLog}").permitAll()
                .antMatchers("/**/historiqueLogins/searchHistoriqueLoginByIdDesc").permitAll()
                .antMatchers("/**/historiqueLogins/searchAllHistoriqueLoginsOrderByIdDesc").permitAll()
                .antMatchers("/**/historiqueLogins/delete/{idHistLog}").permitAll()

                .anyRequest().authenticated();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);

    }

}

