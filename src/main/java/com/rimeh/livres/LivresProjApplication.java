package com.rimeh.livres;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.rimeh.livres.entities.Livre;
import com.rimeh.livres.entities.Role;
import com.rimeh.livres.entities.User;
import com.rimeh.livres.service.LivreService;
import com.rimeh.livres.service.UserService;

import jakarta.annotation.PostConstruct;

@SpringBootApplication
public class LivresProjApplication implements CommandLineRunner{
	@Autowired
	LivreService livreService;
	@Autowired
	PasswordEncoder passwordEncoder;
	@Autowired
	UserService userService;
	
	public static void main(String[] args) {
		SpringApplication.run(LivresProjApplication.class, args);
	}
	
	
	
	/*@PostConstruct
	void init_users() {
	//ajouter les rôles
	userService.addRole(new Role(null,"ADMIN"));
	userService.addRole(new Role(null,"AGENT"));
	userService.addRole(new Role(null,"USER"));
	//ajouter les users
	userService.saveUser(new User(null,"admin","123",true,null));
	userService.saveUser(new User(null,"nadhem","123",true,null));
	userService.saveUser(new User(null,"user1","123",true,null));
	//ajouter les rôles aux users
	userService.addRoleToUser("admin", "ADMIN");
	userService.addRoleToUser("nadhem", "USER");
	userService.addRoleToUser("nadhem", "AGENT");
	userService.addRoleToUser("user1", "USER");
	}*/
	
	
	

	@Override
	public void run(String... args) throws Exception {
		//System.out.println("Password Encoded BCRYPT :******************** ");
		//System.out.println(passwordEncoder.encode("123"));
		
		
		/*livreService.saveLivre(new Livre("L'Étranger", "Albert Camus", 22.0, new Date(), "letranger@gmail.com"));
		livreService.saveLivre(new Livre("Madame Bovary", "Gustave Flaubert", 26.0, new Date(), "madamebovary@gmail.com"));
		livreService.saveLivre(new Livre("Candide", "Voltaire", 18.0, new Date(), "candide@gmail.com"));*/
		
	}

}
