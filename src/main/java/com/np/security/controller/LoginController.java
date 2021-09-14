package com.np.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.np.security.model.LoginRequestDTO;
import com.np.security.util.JWTUtil;

@Controller
public class LoginController {

	@Autowired
	private JWTUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("/")
	public ResponseEntity<String> getMessage() {
		return ResponseEntity.ok("Welcome to Web security");
	}

	@PostMapping("/authenticate")
	public String generateToken(@RequestBody LoginRequestDTO request) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUserName(), request.getPassword()));
		} catch (Exception e) {
			throw new Exception("Invalid username/password");
		}
		return jwtUtil.generateToken(request.getUserName());
	}
}
