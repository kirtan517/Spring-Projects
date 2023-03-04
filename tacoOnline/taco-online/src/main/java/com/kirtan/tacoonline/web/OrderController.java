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
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Controller
@RequestMapping("/orders")
@SessionAttributes("tacoOrder")
public class OrderController implements WebMvcConfigurer {

    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }


    @ModelAttribute
    public void addTacoOrderToModel(Model model){
        model.addAttribute("order", new TacoOrder());
        log.info(model.toString());
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder order , BindingResult errors , SessionStatus sessionStatus, Model model){

        if(errors.hasErrors()){
            log.info("inside the error " , model.toString());
            return "orderForm";
        }
        sessionStatus.setComplete();
        return "redirect:/";
    }

}
