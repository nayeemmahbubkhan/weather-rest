package de.creatision.weather.domainobject;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "user")
@Table(name = "user")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Where(clause = "deleted ='false'")
public class User {
	
    @Id
    @GeneratedValue
    private Long id;
    
    private String firstName;
    
    private String lastName;
    
    @Column(nullable = false)
    @NotNull(message = "email address can not be null!")
    private String emailAddress;

    @Column(nullable = false)
    @NotNull(message = "username can not be null!")
    private String username;
    
    @Column(nullable = false)
    @NotNull(message = "password can not be null!")
    private String password;
    
    private String city;
    
    private String country;
    
    @CreationTimestamp
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime createdAt;
    
    @UpdateTimestamp
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime updatedAt;
    
    @Column(nullable = false)
    @Builder.Default
    private Boolean deleted = false;
}
