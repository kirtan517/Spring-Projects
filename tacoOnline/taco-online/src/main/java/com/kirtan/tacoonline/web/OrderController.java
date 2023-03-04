package com.kirtan.tacoonline.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import com.kirtan.tacoonline.TacoOrder;
import org.springframework.web.bind.support.SessionStatus;

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
        log.info(model.toString());
    }

    @PostMapping
    public String processOrder(TacoOrder order , SessionStatus sessionStatus){
        log.info("Order submitted : {}",order);
        sessionStatus.setComplete();
        return "redirect:/";
    }

}
