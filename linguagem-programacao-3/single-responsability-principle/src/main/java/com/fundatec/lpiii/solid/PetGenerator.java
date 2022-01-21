package com.fundatec.lpiii.solid;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class PetGenerator {

    public List<PetName> randomNames;
    public List<String> randomTypes;
    private Random randomValue;

    public PetGenerator() {
        this.randomNames = Arrays.asList(
                new PetName("Alex", "male"),
                new PetName("Mia", "female"),
                new PetName("Bernard", "male"),
                new PetName("Bella", "female"),
                new PetName("Edgar", "male"),
                new PetName("Fleur", "female"),
                new PetName("Fred", "male"),
                new PetName("Fiora", "female"),
                new PetName("Toby", "male"),
                new PetName("Rex", "male"),
                new PetName("Diana", "female"),
                new PetName("Roberta", "female")
        );

        this.randomTypes = Arrays.asList(
                "Cat", "Dog", "Bird", "Horse", "Hamster"
        );

        this.randomValue = new Random();
    }

    public Pet getRandomPet() {
        Pet randomPet = new Pet();
        randomPet.setPetName(getRandomName());
        randomPet.setAge(getRandomAge());
        randomPet.setType(getRandomType());
        return randomPet;
    }

    public Pet getRandomPet(String genre) {
        Pet randomPet = new Pet();
        randomPet.setAge(getRandomAge());
        randomPet.setType(getRandomType());

        switch (genre) {
            case "female": randomPet.setPetName(getRandomFemaleName());
            break;
            case "male": randomPet.setPetName(getRandomMaleName());
            break;
            default: randomPet.setPetName(getRandomName());
        }

        return randomPet;
    }

    public List<PetName> getAllNames() {
        return randomNames;
    }

    public List<PetName> getAllFemaleNames() {
        return randomNames.stream()
                .filter(petName -> petName.getGenre().equals("female"))
                .collect(Collectors.toList());
    }

    public List<PetName> getAllMaleNames() {
        return randomNames.stream()
                .filter(petName -> petName.getGenre().equals("male"))
                .collect(Collectors.toList());
    }

    private PetName getRandomFemaleName() {
        return getAllFemaleNames().get(randomValue.nextInt(getAllFemaleNames().size()));
    }

    private PetName getRandomMaleName() {
        return getAllMaleNames().get(randomValue.nextInt(getAllMaleNames().size()));
    }

    private PetName getRandomName() {
        return randomNames.get(randomValue.nextInt(getAllNames().size()));
    }

    private String getRandomType() {
        return randomTypes.get(randomValue.nextInt(randomTypes.size()));
    }

    private Integer getRandomAge() {
        return randomValue.nextInt(10);
    }
}
