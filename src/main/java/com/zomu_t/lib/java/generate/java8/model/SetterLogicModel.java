package com.zomu_t.lib.java.generate.java8.model;

import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class SetterLogicModel implements Serializable {

    private static final long serialVersionUID = 1L;

    @NonNull
    private String name;
}
