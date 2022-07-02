package com.zealep.ventasbackend.controller;


import com.zealep.ventasbackend.config.JwtTokenUtil;
import com.zealep.ventasbackend.exception.BusinessException;
import com.zealep.ventasbackend.model.jwt.JwtRequest;
import com.zealep.ventasbackend.model.jwt.JwtResponse;
import com.zealep.ventasbackend.repository.EmpleadoRepository;
import com.zealep.ventasbackend.util.BusinessMsgError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@CrossOrigin
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private UserDetailsService jwtInMemoryUserDetailsService;

	@Autowired
	private EmpleadoRepository empleadoRepository;

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> generateAuthenticationToken(@RequestBody JwtRequest authenticationRequest)
			throws Exception {

		authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

		final UserDetails userDetails = jwtInMemoryUserDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());


		final String token = jwtTokenUtil.generateToken(userDetails);

		final String idUsuario = empleadoRepository.findIdUsuarioByUsername(authenticationRequest.getUsername());

		return ResponseEntity.ok(new JwtResponse(token,idUsuario));
	}

	private void authenticate(String username, String password) throws Exception {
		Objects.requireNonNull(username);
		Objects.requireNonNull(password);
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		}catch (BadCredentialsException e) {
			throw new BusinessException(BusinessMsgError.ERROR_USUARIO_INCORRECTO,e);
		}
	}
}
