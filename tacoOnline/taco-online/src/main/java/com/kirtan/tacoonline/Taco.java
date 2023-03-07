package com.kirtan.tacoonline;

import jakarta.annotation.Nonnull;
import lombok.Data;
import jakarta.validation.constraints.Size;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;


import java.util.Date;
import java.util.List;

@Data
public class Taco {

    private long id;

    @Nonnull
    @Size(min=5, message="Name must be at least 5 characters long")
    private String name;

    @Nonnull
    @Size(min=2, message="You must choose at least 1 ingredient")
    private List<Ingredient> ingredients;

    private Date createdAt = new Date();
}
