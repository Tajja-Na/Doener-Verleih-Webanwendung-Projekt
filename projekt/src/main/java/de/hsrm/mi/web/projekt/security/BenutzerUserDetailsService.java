package de.hsrm.mi.web.projekt.security;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import de.hsrm.mi.web.projekt.benutzer.services.BenutzerService;
import de.hsrm.mi.web.projekt.entities.benutzer.Benutzer;

@Service
public class BenutzerUserDetailsService implements UserDetailsService{

    private PasswordEncoder passwordEncoder;
    private BenutzerService bs;

    public BenutzerUserDetailsService(PasswordEncoder passwordEncoder, BenutzerService bs){
        this.passwordEncoder = passwordEncoder;
        this.bs = bs;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username.equals("admin")){
            return User.builder().username("admin").password(passwordEncoder.encode("admin")).roles("ADMIN").build();
        }else{
            Optional<Benutzer> benutzer = bs.findBenutzerById(username);
            if(benutzer.isPresent()){
                Benutzer vohandenerBenutzer = benutzer.get();
                return User.builder().username(username).password(vohandenerBenutzer.getLosung()).roles("USER").build();
            }else{
                throw new UsernameNotFoundException("Benutzer: " +username+ " konnte nicht gefunden werden");
            }
        }
    }
}
