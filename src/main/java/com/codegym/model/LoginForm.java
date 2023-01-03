package com.codegym.model;

import lombok.*;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoginForm {
    @NotNull
    @Size(min = 3, max = 28)
    private String username;

    @NotNull
    @Size(min = 6, max = 28)
    private String password;
}
