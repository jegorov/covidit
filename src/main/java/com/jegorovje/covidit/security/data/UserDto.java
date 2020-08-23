package com.jegorovje.covidit.security.data;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class UserDto {

    @NotBlank
    private String login;
    @NotBlank
    private String password;

    private String role;

}
