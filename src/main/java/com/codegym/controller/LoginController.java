package com.codegym.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@CrossOrigin("*")
public class LoginController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtService jwtService;
    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginForm loginForm) {
        try {
            // Tạo 1 đối tượng authentication
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            // Tạo token mới
            String token = jwtService.createToken(authentication);
            AppUser user1 = userService.getUserByUsername(loginForm.getUsername());
            JwtResponse jwtResponse = new JwtResponse(user1.getId(), user1.getUsername(), token, user1.getRoles());
            AppUser account = userService.findByUsername(loginForm.getUsername());

            if (account.getStatus() != null) {
                String status = account.getStatus();
                if ("0".equals(status)) {
                    return ResponseEntity.ok(new MessageResponse("lock"));
                }
            }

            return new ResponseEntity<>(jwtResponse, HttpStatus.ACCEPTED);

        } catch (Exception e) {
            System.out.println("Loi khi dang nhap");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
