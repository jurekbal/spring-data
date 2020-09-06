package pl.sdacademy.springdata.task06;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Animal {

    private String name;
    private int age;

    @Id
    @GeneratedValue
    private long id;
}
