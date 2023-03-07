package com.kirtan.tacoonline.web;

import com.kirtan.tacoonline.Ingredient;
import com.kirtan.tacoonline.Taco;
import com.kirtan.tacoonline.TacoOrder;
import com.kirtan.tacoonline.data.IngredientRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.kirtan.tacoonline.Ingredient.Type;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/design")
@SessionAttributes("tacoOrder")
@Slf4j
public class DesignTacoController {

    private final IngredientRepository ingredientRepository;
//    private TacoRepository designRepo

    @Autowired
    public  DesignTacoController(IngredientRepository ingredientRepository
//                                 TacoRepository designRepo
    ) {
        this.ingredientRepository = ingredientRepository;
//        this.designRepo = designRepo;
    }


    @ModelAttribute
    public void addIngredientsToModel(Model model){
        List<Ingredient> ingredients = (List<Ingredient>) ingredientRepository.findAll();
        Type [] types = Ingredient.Type.values();
        for(Type type : types){
            model.addAttribute(type.toString().toLowerCase(),filterbyType(ingredients,type));
        }
    }

    @ModelAttribute(name = "tacoOrder")
    public TacoOrder order(){
    return new TacoOrder();
    }

    @ModelAttribute(name = "taco")
    public Taco taco(){
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(Model model){
        log.info("inside the showDesignForm");
        log.info(model.toString());
        return "design";
    }

    private Iterable<Ingredient> filterbyType(List<Ingredient> ingredients,Type type){
        return ingredients.stream().filter(x->x.getType().equals(type)).collect(Collectors.toList());
    }


    @PostMapping
    public String processTaco(@Valid Taco taco, BindingResult errors, @ModelAttribute TacoOrder tacoOrder, Model model){
        if (errors.hasErrors()) {
            return "design";
        }

        tacoOrder.addTaco(taco);
        log.info("Processing taco : {}",taco);
        return "redirect:/orders/current";
    }

}
