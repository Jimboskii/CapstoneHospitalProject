package com.example.service.rest;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mail.demo.model.MailMessage;
//import com.example.mail.demo.service.MailService;

import com.example.models.ERole;
import com.example.models.JwtResponse;
import com.example.models.Role;
import com.example.models.User;
import com.example.payload.request.LoginRequest;
import com.example.payload.request.SignupRequest;
import com.example.payload.response.MessageResponse;
import com.example.repositorys.RoleRepository;
import com.example.repositorys.UserRepository;
import com.example.security.jwt.JwtUtils;
import com.example.security.services.UserDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class JwtAuthenticationController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
//	@Autowired
//	MailService notificationService;
	
	@Autowired
	MailMessage message;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail(), 
												 roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), 
							 signUpRequest.getEmail(),
							 encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role pharmRole = roleRepository.findByName(
					ERole.PHARMACIST)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(pharmRole);
		} else {
			strRoles.forEach(role -> {
				switch (role.toLowerCase()) {
				case "pharmacist":
					Role pharmRole = roleRepository.findByName(
							ERole.PHARMACIST)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(pharmRole);

					break;
				case "nurse":
					Role nurseRole = roleRepository.findByName(
							ERole.NURSE)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(nurseRole);

					break;

				default:
					pharmRole = roleRepository.findByName(
							ERole.PHARMACIST)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(pharmRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);
		
		message.setEmailAddress("jimmyngpham@gmail.com"); // Receiver's email address
		message.setSubject("signup Mail request");
		message.setBodyText("You have signed up!");
		/*
		 * Here we will call sendEmail() for Sending mail to the sender.
		 */
//		try {
//			notificationService.sendEmail(message);
//		} catch (MailException mailException) {
//			System.out.println(mailException);
//		}
//	
		

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}
}
