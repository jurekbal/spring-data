package pl.sdacademy.springdata.task06;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class Task06Controller {

    private AnimalRepository animalRepository;
    private Task06Service animalService;
    // kontroler powinien komunikować się z serwisem a nie bezpo z repo
    // korzystamy z service od zadania 6.2

    public Task06Controller(AnimalRepository animalRepository, Task06Service animalService) {
        this.animalRepository = animalRepository;
        this.animalService = animalService;
    }


    @PostMapping(path="/task06/create")
    public void createAndSaveAnimal (@RequestBody Animal animal) {
        animalRepository.save(animal);
    }

    @GetMapping(path="/task06/getall")
    public List<Animal> getAllAnimals() {
        return animalRepository.findAll();
    };

    @GetMapping("/task06-1")
    public List<Animal> getByAgeAndName() {
        return animalRepository.findByAgeGreaterThanAndNameIsLike(5,"%Rex%");
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Animal> deleteAnimal(@RequestParam(name="id") Long id) {
        try {
            animalRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (EmptyResultDataAccessException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("task06-1bonus/{id}")
    public ResponseEntity<Animal> getByIdAndSetResponse(@PathVariable(value="id") long id) {
        Optional<Animal> animalOpt = animalRepository.findById(id);
        if(animalOpt.isPresent()) {
            return ResponseEntity.ok(animalOpt.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    //task 6.1 bonus pkt 4
    @PostMapping("task06/createall")
    public void createAnimlsFromList(@RequestBody List<Animal> animalList) {
        animalRepository.saveAll(animalList);
    }

    //zad 6.2 pkt 1 i 2.1
    // dodatkowo używamy DTO i Response
    @GetMapping("/animals")
    public AnimalListResponse getAnimals(@RequestParam(value="name", required = false) String name,
                                     @RequestParam(value="age", required = false) Integer age) {
        return new AnimalListResponse(animalService.getAnimals(name, age));
    }

    @GetMapping("/three")
    public AnimalListResponse getThree() {
        return new AnimalListResponse(animalService.getTopThreeDesc());
    }

    @GetMapping("/page")
    public AnimalListResponse getPage(@RequestParam(name="perpage", defaultValue = "3") Integer resultsPerPage,
                                      @RequestParam(name = "page", defaultValue = "1") Integer page,
                                      @RequestParam(name="order", defaultValue = "ASC") Sort.Direction order) {
        return new AnimalListResponse(animalService.getPages(resultsPerPage, page, order));
    }

}
