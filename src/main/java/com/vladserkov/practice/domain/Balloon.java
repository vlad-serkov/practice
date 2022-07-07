package com.vladserkov.practice.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@AllArgsConstructor
public class Balloon {
    private int serial;
    private double capacity;
    private String substance;
    private double pressure;
}
