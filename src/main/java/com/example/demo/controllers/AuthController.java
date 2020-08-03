package com.example.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.jwt.JwtRequest;
import com.example.demo.models.jwt.JwtResponse;
import com.example.demo.security.JwtToken;

@RequestMapping("/authenticate")
@RestController
public class AuthController {
	
	@Autowired
	private JwtToken jwtTokenUtil;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@GetMapping("/hello")
	public ResponseEntity<?> hello(){
		return ResponseEntity.ok("Hola Mundo");
	}
	
	@PostMapping()
	public ResponseEntity<?> autenticar(@RequestBody JwtRequest request) throws Exception{
		try {
			autenticar(request.getUsername(),request.getPassword());
			UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
			String token = jwtTokenUtil.generateToken(userDetails);
			return ResponseEntity.ok(new JwtResponse(token));
		}catch(Exception e){
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
	
	private void autenticar(String username, String password) throws Exception{
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));			
		}catch(DisabledException e) {
			throw new Exception("USUARIO DESHABILITADO");
		}catch(BadCredentialsException e) {
			throw new Exception ("CREDENCIALES INVALIDAS");
		}		
	}
}
