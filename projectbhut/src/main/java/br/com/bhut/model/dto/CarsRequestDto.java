package br.com.bhut.model.dto;

import lombok.Data;

@Data
public class CarsRequestDto {

    private String title;
    private String brand;
    private String price;
    private int age;
}
