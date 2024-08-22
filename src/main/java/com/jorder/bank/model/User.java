package com.jorder.bank.model;


// import java.util.List;
// import java.util.Set;
// import jakarta.persistence.OneToMany;
// import jakarta.persistence.FetchType;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users", schema = "public")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;
    private int balance;

    // @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    // private Set<Talent> talents;

    // @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    // private Set<Schedule> schedules;
    
    @JsonIgnore
    private String salt;

}
