package com.zealep.ventasbackend.service;



import com.zealep.ventasbackend.model.entity.Empleado;
import com.zealep.ventasbackend.repository.EmpleadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private EmpleadoRepository empleadoRepository;

	@Override
	public UserDetails loadUserByUsername(final String username) {
		Empleado user = empleadoRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("usuario no encontrado. " + username));
		return this.userBuilder(user.getUsuario(), user.getClave(), new String[]{"ADMIN"}, true);

	}

	private User userBuilder(String username, String password, String[] roles,
							 boolean active) {
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}
		return new User(username, password, active, true,
				true, true, authorities);
	}
}