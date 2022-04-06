package com.real.estate.analyzer.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Audit {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private Long userId;

    private LocalDateTime createdOn;

    private String oldValue;

    private String newValue;

    @NotNull
    private String service;
}
