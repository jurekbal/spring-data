package pl.sdacademy.springdata.task06;

import lombok.Value;

import java.util.List;

@Value
public class AnimalListResponse {
    List<AnimalDTO> animals;
}
