package com.kirtan.tacoonline.web;

import com.kirtan.tacoonline.data.OrderRepository;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    private OrderRepository orderRepository;
    @Autowired
    public OrderController(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }


    @GetMapping("/current")
    public String orderForm() {
        return "orderForm";
    }


    @ModelAttribute
    public void addTacoOrderToModel(Model model){
        model.addAttribute("order", new TacoOrder());
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder order , BindingResult errors, SessionStatus sessionStatus ){

        if(errors.hasErrors()){
            return "orderForm";
        }
        orderRepository.save(order);
        sessionStatus.setComplete();
        return "redirect:/";
    }

}
