package pl.sdacademy.springdata.task06;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Task06Service {
    private AnimalRepository animalRepository;

    public Task06Service(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<AnimalDTO> getAnimals(String name, Integer age) {
        if (name == null && age == null) {
            return animalRepository.findAll().stream()
                    .map(AnimalDTO::fromAnimal)
                    .collect(Collectors.toList());
        } else if (name != null && age == null) {
            return animalRepository.findByName(name).stream()
                    .map(AnimalDTO::fromAnimal)
                    .collect(Collectors.toList());
        } else if (name == null && age != null) {
            return animalRepository.findByAge(age).stream()
                    .map(AnimalDTO::fromAnimal)
                    .collect(Collectors.toList());
        } else {
            return animalRepository.findByNameAndAge(name, age).stream()
                    .map(AnimalDTO::fromAnimal)
                    .collect(Collectors.toList());
        }
        //UWAGA! Można usunąć wszystkie if-y i zostawić tylko ostatni return...
        // domyślnie find ignoruje nulle!
    }

    public List<AnimalDTO> getTopThreeDesc() {
        return animalRepository.findTop3ByOrderByNameDesc().stream()
                .map(AnimalDTO::fromAnimal)
                .collect(Collectors.toList());
    }


    public List<AnimalDTO> getPages(Integer resultsPerPage, Integer page, Sort.Direction sort) {

        Pageable pageable = PageRequest.of(page, resultsPerPage, Sort.by(sort, "name"));
        Page<Animal> animalPage = animalRepository.findAll(pageable);

        return animalPage.get()
                .map(AnimalDTO::fromAnimal)
                .collect(Collectors.toList());
    }
}
