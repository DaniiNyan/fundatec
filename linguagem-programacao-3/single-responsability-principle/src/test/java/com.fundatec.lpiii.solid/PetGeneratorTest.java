package com.fundatec.lpiii.solid;

import org.junit.Test;

import static junit.framework.TestCase.*;

public class PetGeneratorTest {

    private PetGenerator petGenerator = new PetGenerator();

    @Test
    public void mustGenerateRandomPet() {
        Pet randomPet = petGenerator.getRandomPet();
        assertNotNull(randomPet.getPetName());
        assertNotNull(randomPet.getAge());
        assertNotNull(randomPet.getType());
    }

    @Test
    public void mustGenerateFemalePetNameWhenGivenFemaleAsParam() {
        Pet femalePet = petGenerator.getRandomPet("female");
        assertEquals("female", femalePet.getGenre());
    }

    @Test
    public void mustGenerateMalePetNameWhenGivenMaleAsParam() {
        Pet malePet = petGenerator.getRandomPet("male");
        assertEquals("male", malePet.getGenre());
    }
}
