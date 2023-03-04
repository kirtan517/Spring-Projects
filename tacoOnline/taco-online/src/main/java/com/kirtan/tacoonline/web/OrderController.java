package com.kirtan.tacoonline.web;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import com.kirtan.tacoonline.TacoOrder;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController {


    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }


    @ModelAttribute
    public void addTacoOrderToModel(Model model){
        model.addAttribute("order", new TacoOrder());
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder order , BindingResult errors, Model model ){

        if(errors.hasErrors()){
            log.info("inside the error ");
            log.info("This is the current model " , model);
            return "orderForm";
        }
        return "redirect:/";
    }

}
