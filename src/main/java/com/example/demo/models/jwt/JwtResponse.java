package com.example.demo.models.jwt;

public class JwtResponse {
	private String jwtToken;
	public JwtResponse(String jwtToken) {
		this.jwtToken = jwtToken;
	}
	public String getToken() {return this.jwtToken;}
}
