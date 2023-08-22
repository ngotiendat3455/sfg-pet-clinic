package guru.springframework.sfgpetclinic.repositories;

import guru.springframework.sfgpetclinic.model.Person;
import guru.springframework.sfgpetclinic.services.CrudService;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//@Repository
public interface PersonRepository extends CrudRepository<Person,Long> {
}
