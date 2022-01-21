package com.fundatec.lpiii.solid;

public class Pet {

    private PetName petName;
    private Integer age;
    private String type;
    private String genre;

    public String getPetName() {
        return petName.getName();
    }

    public void setPetName(PetName petName) {
        this.petName = petName;
        this.genre = petName.getGenre();
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getGenre() {
        return genre;
    }
}
