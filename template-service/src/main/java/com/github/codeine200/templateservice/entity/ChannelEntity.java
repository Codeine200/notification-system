package com.github.codeine200.templateservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "channels", schema = "templates")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChannelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "channels_seq")
    @SequenceGenerator(name = "channels_seq", sequenceName = "templates.channels_seq", allocationSize = 1)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String type;

    @ManyToMany(mappedBy = "channels")
    private Set<TemplateEntity> templates;
}