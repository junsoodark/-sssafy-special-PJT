package ssafy.musicD.controller;
//package ssafy.musicD.controller;
//
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//import javax.validation.Valid;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import ssafy.musicD.Domain.ERole;
//import ssafy.musicD.Domain.Role;
//import ssafy.musicD.Domain.Member;
//import ssafy.musicD.jwt.JwtUtils;
//import ssafy.musicD.payload.request.LoginRequest;
//import ssafy.musicD.payload.request.SignupRequest;
//import ssafy.musicD.payload.response.JwtResponse;
//import ssafy.musicD.payload.response.MessageResponse;
//import ssafy.musicD.repository.RoleRepo;
//import ssafy.musicD.repository.UserRepo;
//import ssafy.musicD.service.UserDetailsImpl;
//
//
//@RestController
//@RequestMapping("/api/account")
//@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
//public class AuthController {
//	@Autowired
//	AuthenticationManager authenticationManager;
//
//	@Autowired
//	UserRepo userRepo;
//
//	@Autowired
//	RoleRepo roleRepo;
//
//	@Autowired
//	PasswordEncoder encoder;
//
//	@Autowired
//	JwtUtils jwtUtils;
//
//	@PostMapping("/login")
//	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
//
//		Authentication authentication = authenticationManager.authenticate(
//				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
//		SecurityContextHolder.getContext().setAuthentication(authentication);
//		String jwt = jwtUtils.generateJwtToken(authentication);
//		
//		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
//		List<String> roles = userDetails.getAuthorities().stream()
//				.map(item -> item.getAuthority())
//				.collect(Collectors.toList());
//
//		return ResponseEntity.ok(new JwtResponse(jwt, 
//												 userDetails.getId(), 
//												 userDetails.getName(),
//												 userDetails.getUsername(), 
//												 roles));
//	}
//	
//	@PostMapping("/signup")
//	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
//		if (userRepo.existsByEmail(signUpRequest.getEmail())) {
//			return ResponseEntity
//					.badRequest()
//					.body(new MessageResponse("Error: Email is already in use!"));
//		}
//
//		// Create new user's account
//		Member user = new Member(signUpRequest.getEmail(),
//							 signUpRequest.getName(),
//							 encoder.encode(signUpRequest.getPassword()));
//
//		Set<String> strRoles = signUpRequest.getRoles();
//		Set<Role> roles = new HashSet<>();
//
//		if (strRoles == null) {
//			Role userRole = roleRepo.findByName(ERole.ROLE_USER)
//					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//			roles.add(userRole);
//		} else {
//			strRoles.forEach(role -> {
//				switch (role) {
//				case "admin":
//					Role adminRole = roleRepo.findByName(ERole.ROLE_ADMIN)
//							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//					roles.add(adminRole);
//
//					break;
//				default:
//					Role userRole = roleRepo.findByName(ERole.ROLE_USER)
//							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//					roles.add(userRole);
//				}
//			});
//		}
//
//		user.setRoles(roles);
//		userRepo.save(user);
//
//		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
//	}
//
//	
//}