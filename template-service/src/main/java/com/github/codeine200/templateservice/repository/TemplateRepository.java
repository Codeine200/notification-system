package com.github.codeine200.templateservice.repository;

import com.github.codeine200.templateservice.entity.TemplateEntity;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.lang.NonNull;

@Registered
public interface TemplateRepository extends JpaRepository<TemplateEntity, Long> {
    @NonNull Optional<TemplateEntity> findById(@NonNull Long id);

    @Query(value = """
        SELECT distinct value
        FROM templates.templates t,
             jsonb_array_elements_text(t.variables) AS value
        WHERE t.id = :templateId
        ORDER BY value ASC
    """, nativeQuery = true)
    Set<String> findSortedVariables(Long templateId);
}