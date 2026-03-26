package com.github.codeine200.templateservice.service;

import com.github.codeine200.templateservice.repository.TemplateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@AllArgsConstructor
public class TemplateService {
    private final TemplateRepository templateRepository;

    public boolean hasSameVariables(Long templateId, Set<String> inputVariables) {
        Set<String> templateVariables = templateRepository.findSortedVariables(templateId);
        return templateVariables.equals(inputVariables);
    }
}
