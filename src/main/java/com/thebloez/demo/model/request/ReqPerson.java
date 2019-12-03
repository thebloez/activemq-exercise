package com.thebloez.demo.model.request;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class ReqPerson {
    private String name;
    private BigDecimal salary;
}
