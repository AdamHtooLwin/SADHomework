package com.example.security.models;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
// @Data //from lomok, equivalent to getter/setter
// @Builder => lombok also
public class User {

    //Indicates what is primary key
    @Id
    //autoincremented value - 1,2,3
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    //cannot be null in db level
    //db level
    @Column(nullable = false)
    //in UI level
    //validation dependency
    @NotBlank(message = "This field is required")
    private String username;

    @Column(nullable = false)
    @NotBlank(message = "This field is required")
    private String password;

    @NotBlank(message = "This field is required")
    //dont want to put this field into the table
    @Transient
    private String passwordConfirmation;

    @Column(nullable = false)
    @NotBlank(message = "This field is required")
    //ui level - valid email
    @Email(message = "Invalid email")
    private String email;

    private boolean active;

    //one user has many roles
    //one role has many users
    //m2m relationship
    //below decorator creates an intermediate table
    //User_id, Role_id - @JoinTable => if we want tochange field name
    //fetch - if I have users , do you want all users already have roles
    //if i get one user, should jpa also get the roles of that user (eager initilaization) -> good if children are small
    //or if i get one user, maybe roles are retrieved only when asked (lazy initialization) -> good if big
    //but Spring security - user should always be eager -> want to know authority of users right away
    // Spring secuirty expectss some fields by default like email, roles, etc.
    @ManyToMany(fetch = FetchType.EAGER)
    //in a m2m relationship, who is the parent and child?
    //if parent is saved, usually child is saved
    //but if child is saved, maybe parent is not saved
    @JsonBackReference //take this out - infinite recursion error - EXPLORE!
    private Set<Role> roles;
}
