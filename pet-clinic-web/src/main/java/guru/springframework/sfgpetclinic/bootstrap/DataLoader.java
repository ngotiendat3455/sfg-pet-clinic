package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import guru.springframework.sfgpetclinic.services.map.OwnerServiceMap;
import guru.springframework.sfgpetclinic.services.map.PetTypeMapService;
import guru.springframework.sfgpetclinic.services.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;

    public DataLoader(OwnerServiceMap ownerService, VetServiceMap vetService, PetTypeMapService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {
        PetType dog = new PetType();
        dog.setName("Dog");
        PetType saveDog = petTypeService.save(dog);

        PetType cat = new PetType();
        cat.setName("Cat");
        PetType saveCat = petTypeService.save(cat);

        Owner owner1 = new Owner();
        owner1.setFirstName("First name 1");
        owner1.setLastName("Last name 1");

        Pet mikePet = new Pet();
        mikePet.setName("mike pet");
        mikePet.setBirthDate(LocalDate.now());
        mikePet.setPetType(saveDog);
        mikePet.setOwner(owner1);

        owner1.getPets().add(mikePet);
        ownerService.save(owner1);



        Owner owner2 = new Owner();
        // owner2.setId(2L);
        owner2.setFirstName("First name 2");
        owner2.setLastName("Last name 2");
        ownerService.save(owner2);

        System.out.println("Loaded owners");

        Vet vet1 = new Vet();
        // vet1.setId(1L);
        vet1.setFirstName("vet frist name 1");
        vet1.setLastName("vet frist name 2");
        vetService.save(vet1);
        System.out.println("Loaded vets");
    }
}
