package pl.sdacademy.springdata.task06;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animal, Long> {

    //zad 6.1
    public List<Animal> findByAgeGreaterThanAndNameIsLike(int age, String name);

    //zad 6.2
    List<Animal> findByName(String name);
    List<Animal> findByAge(int age);
    List<Animal> findByNameAndAge(String name, int age);
    List<Animal> findTop3ByOrderByNameDesc();
}
