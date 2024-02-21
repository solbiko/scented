package com.dysb.scented.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.Set;

@Entity
@Table(name = "perfume")
@Comment("향수")
@Getter
@Setter
public class Perfume extends BaseEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("향수명")
    private String name;

    @Comment("브랜드명")
    @Column(name="brand_name")
    private String brandName;

    @BatchSize(size = 1000)
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "perfume")
    @Fetch(value = FetchMode.JOIN)
    Set<com.dysb.scented.entity.Comment> comments;


}