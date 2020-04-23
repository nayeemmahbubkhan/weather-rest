package de.weather.domainobject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "blacklist")
@Table(name = "blacklist")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Where(clause = "deleted ='false'")
public class Blacklist {
    @Id
    @GeneratedValue
    private Long id;
    
    private String token;

}
