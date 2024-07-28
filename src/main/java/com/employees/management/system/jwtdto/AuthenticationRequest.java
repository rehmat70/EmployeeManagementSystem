package com.employees.management.system.jwtdto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthenticationRequest {

    private String email;
    private String password;
}
