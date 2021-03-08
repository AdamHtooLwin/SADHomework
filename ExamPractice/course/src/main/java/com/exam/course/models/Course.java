package com.exam.course.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Course {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @Enumerated
    private Discipline discipline;

    @Enumerated
    private PaidModel type;

    private LocalDate dateOffered;

    @Column(precision=10, scale=2)
	private BigDecimal revenueGenerated;

    @ManyToMany(mappedBy = "courses")
    private Set<User> users;
}
