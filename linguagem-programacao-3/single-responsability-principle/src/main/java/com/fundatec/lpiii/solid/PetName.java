package com.fundatec.lpiii.solid;

public class PetName {

    private String name;
    private String genre;

    public PetName(String name, String genre) {
        this.name = name;
        this.genre = genre;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }
}
