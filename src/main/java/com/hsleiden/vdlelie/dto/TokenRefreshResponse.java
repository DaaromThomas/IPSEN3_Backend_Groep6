package com.hsleiden.vdlelie.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TokenRefreshResponse {
    private String accessToken;
    private String refreshToken;
}

