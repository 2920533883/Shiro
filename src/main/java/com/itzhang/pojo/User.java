package com.itzhang.pojo;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    String id;
    String username;
    String password;
    String salt;
    String role_id;
}
