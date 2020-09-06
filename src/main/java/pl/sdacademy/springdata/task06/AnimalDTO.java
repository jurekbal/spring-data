package pl.sdacademy.springdata.task06;

import lombok.Value;

@Value
public class AnimalDTO {
    private String name;
    private int age;

    // konwerter z Entity na DTO
    public static AnimalDTO fromAnimal(Animal animal) {
        return new AnimalDTO(animal.getName(), animal.getAge());
    }

}
