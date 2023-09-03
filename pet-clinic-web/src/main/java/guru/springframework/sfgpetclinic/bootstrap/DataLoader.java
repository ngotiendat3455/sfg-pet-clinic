package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.*;
import guru.springframework.sfgpetclinic.services.map.OwnerServiceMap;
import guru.springframework.sfgpetclinic.services.map.PetTypeMapService;
import guru.springframework.sfgpetclinic.services.map.SpecialityServiceMap;
import guru.springframework.sfgpetclinic.services.map.VetServiceMap;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {
    private final OwnerService ownerService;
    private final VetService vetService;
    private final PetTypeService petTypeService;
    private final SpecialityService specialityService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialityService specialityService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialityService = specialityService;
    }

    @Override
    public void run(String... args) throws Exception {
          // loadData();
    }

    private void loadData() {
        Specialty speciality1 = new Specialty();
        speciality1.setDescription("Speciality 1");

        Specialty speciality2 = new Specialty();
        speciality2.setDescription("Speciality 2");

        Specialty speciality3 = new Specialty();
        speciality3.setDescription("Speciality 3");

        Specialty saveSpeciality1 = specialityService.save(speciality1);
        Specialty saveSpeciality2 = specialityService.save(speciality2);
        Specialty saveSpeciality3 = specialityService.save(speciality3);

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
        vet1.getSpecialties().add(speciality1);
        vetService.save(vet1);
        System.out.println("Loaded vets");
    }
}
