package com.cancela.twitterclone.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse {
    private String userToken;
    private String username;
    private String fantasyName;
    private String email;
    private String profilePic;
    private String bio;
    private String location;
    private String site;
}
