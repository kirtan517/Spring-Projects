package com.formexample.simpleform;

import jakarta.annotation.Nonnull;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class PersonForm {

    @NotNull
    @Size(min = 3 , max = 15)
    private String name;

    @NotNull
    @Min(18)
    private int age;


}
