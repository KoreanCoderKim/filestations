package com.example.FileStateWeb2.dto;
public class HumanDto {
    private String name;

    public HumanDto(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "HumanDto [name=" + name + "]";
    }
}