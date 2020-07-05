package com.example.demo.auth;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.DAO.ProgrammeurRepository;
import com.example.demo.model.Programmeur;

@Service
public class MyUserDetailsService implements UserDetailsService {

	@Autowired
	ProgrammeurRepository programmeurRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Programmeur programmeur = programmeurRepository.findByNom(username);

		if (programmeur == null) {
			throw new UsernameNotFoundException("Utilisateur Non Existant :" + username);
		}

		return new MyUserDetails(programmeur);

	}

}
