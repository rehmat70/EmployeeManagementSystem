package com.employees.management.system.jwtimplementation;

import com.employees.management.system.jwtdto.AuthenticationRequest;
import com.employees.management.system.security.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/Jwt/api")
public class JwtAuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDao;
    private final JwtUtils jwtUtils;
    @PostMapping("/login")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest request) {
//    authenticationManager.authenticate(
//            new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword()));
//
//        UserDetails user= userDao.findUserBYEmail(request.getEmail());
//        if (user != null){
//            return ResponseEntity.ok(jwtUtils.generateToken(user));
//        }
//        return ResponseEntity.status(400).body("Some error has occur");
//    }

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            UserDetails user = userDao.loadUserByUsername(request.getEmail());
            if (user != null) {
                String jwtToken = jwtUtils.generateToken(user);
                return ResponseEntity.ok(jwtToken);
            } else {
                return ResponseEntity.status(400).body("User not found");
            }
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(401).body("Invalid credentials");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("An error occurred: " + e.getMessage());
        }
    }
}
