package de.creatision.weather.datatransferobject;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRegistrationRequest {
	
    private String firstName;
    private String lastName;
    @NotNull
    private String emailAddress;
    @NotNull
    private String username;
    @NotNull
    private String password;
    private String city;
    private String country;

}
