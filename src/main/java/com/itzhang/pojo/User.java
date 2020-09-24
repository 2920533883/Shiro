package com.itzhang.pojo;

import lombok.*;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    Integer id;
    String username;
    String password;
    String salt;
    String role;
    String role_id;
    Set<String> auth;
}
