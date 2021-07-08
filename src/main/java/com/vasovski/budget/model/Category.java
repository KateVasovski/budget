package com.vasovski.budget.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "tree", nullable = false, columnDefinition = "ltree")
    @Type(type = "com.vasovski.budget.stuff.LTreeType")
    private String tree;

    @OneToMany(mappedBy = "category")
    @JsonIgnore
    private List<Expense> expenses;

    @Transient
    private Category parent;
}
