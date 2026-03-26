package com.github.codeine200.templateservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "templates", schema = "templates")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TemplateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "templates_seq")
    @SequenceGenerator(name = "templates_seq", sequenceName = "templates.templates_seq", allocationSize = 1)
    private Integer id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private List<String> variables;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToMany
    @JoinTable(
            name = "channel_templates",
            schema = "templates",
            joinColumns = @JoinColumn(name = "template_id"),
            inverseJoinColumns = @JoinColumn(name = "channel_id")
    )
    private Set<ChannelEntity> channels;
}