package com.kirtan.tacoonline;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class TacoOrder {
    private String diliveryName;
    private String diliveryStreet;
    private String diliveryCity;
    private String diliveryState;
    private String diliveryZip;

    private String ccnumber;
    private String ccExpiration;
    private String ccCVV;

    private List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco){
        this.tacos.add(taco);
    }

}
