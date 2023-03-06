package com.kirtan.tacoonline.web;

import com.kirtan.tacoonline.Ingredient;
//import org.springframework.cglib.core.Converter;
import com.kirtan.tacoonline.data.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

import java.util.HashMap;
import java.util.Map;
import com.kirtan.tacoonline.Ingredient.Type;
import org.springframework.stereotype.Component;

@Component
public class IngredientByIdConverter implements Converter<String, Ingredient> {

    private final IngredientRepository ingredientRepository;


    private Map<String, Ingredient> ingredientMap = new HashMap<>();

    @Autowired
    public IngredientByIdConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }
    @Override
    public Ingredient convert(String id) {
        return ingredientRepository.findById(id).orElse(null);
    }

}
