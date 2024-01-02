package com.hsleiden.vdlelie.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequest {
    int employeenumber;
    String username;
    String password;
    String locationID;
    String email;

}
