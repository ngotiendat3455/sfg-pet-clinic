package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerServiceMapTest {

    OwnerServiceMap ownerServiceMap;

    final Long ownerId = 1L;
    final String lastName = "Smith";
    @BeforeEach
    void setUp() {
        ownerServiceMap = new OwnerServiceMap(new PetTypeMapService(), new PetMapService());
        ownerServiceMap.save(Owner.builder().id(ownerId).lastName(lastName).build());
    }

    @Test
    void findAll() {
        Set<Owner> ownerSet = ownerServiceMap.findAll();
        assertEquals(1, ownerSet.size());
    }

    @Test
    void findByLastName() {
        Owner owner = ownerServiceMap.findByLastName(lastName);
        assertNotNull(owner);
        assertEquals(lastName, owner.getLastName());
    }

    @Test
    void findById() {
        Owner optionalOwner = ownerServiceMap.findById(ownerId);
        assertEquals(ownerId, optionalOwner.getId());
    }

    @Test
    void saveExistingId() {
        Long id = 2L;

        Owner owner = Owner.builder().id(id).build();
        Owner savedOwner = ownerServiceMap.save(owner);
        assertEquals(id, savedOwner.getId());
    }

    @Test
    void saveNodId() {
        Owner owner = Owner.builder().build();
        Owner savedOwner = ownerServiceMap.save(owner);
        assertNotNull(savedOwner);
        assertNotNull(savedOwner.getId());
    }
    @Test
    void delete() {
        ownerServiceMap.delete(ownerServiceMap.findById(ownerId));
        assertEquals(0, ownerServiceMap.findAll().size());
    }

    @Test
    void deleteById() {
        ownerServiceMap.deleteById(ownerId);
        assertEquals(0, ownerServiceMap.findAll().size());
    }
}
