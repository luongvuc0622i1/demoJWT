package com.codegym.model.dto;

import com.codegym.model.jwt.Role;
import lombok.*;

import java.util.Set;

@RequiredArgsConstructor
@Data
@Getter
@Setter
@AllArgsConstructor
public class JwtResponse {
    private Long id;
    private String username;
    private String token;
    private Set<Role> roleSet;
}
