package com.kirtan.tacoonline;

import jakarta.annotation.Generated;
import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class TacoOrder {

    private long id;
    
    @NotBlank(message="Delivery name is required")
    @Size(max=50, message="Should not be longer than 50 characters")
    private String name;
    @NotBlank(message="Street is required")
    @Size(max=50, message="Should not be longer than 50 characters")
    private String street;
    @NotBlank(message="City is required")
    @Size(max=50, message="Should not be longer than 50 characters")
    private String city;
    @NotBlank(message="State is required")
    @Size(max=2, message="Should not be longer than 50 characters")
    private String state;
    @NotBlank(message="Zip code is required")
    @Size(max=10, message="Should not be longer than 50 characters")
    private String zip;

//    @CreditCardNumber(message="Not a valid credit card number")
    private String ccNumber;

    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([2-9][0-9])$", message="Must be formatted MM/YY")
    @Size(max=5, message="Should not be longer than 5 characters")
    private String ccExpiration;

    @Digits(integer=3, fraction=0, message="Invalid CVV")
    @Size(max=3, message="Should not be longer than 3 characters")
    private String ccCVV;

    private Date placedAt;

    private List<Taco> tacos = new ArrayList<>();

    public void addTaco(Taco taco){
        this.tacos.add(taco);
    }



}
