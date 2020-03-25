package de.weather.datatransferobject;

import java.time.ZonedDateTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
	
    private Long id;
    @NotNull
    private String emailAddress;
    @NotNull
    private String username;
    private ZonedDateTime createdAt;
    private String accessToken;

}
