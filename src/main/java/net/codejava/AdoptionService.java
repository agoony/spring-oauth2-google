package net.codejava;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdoptionService {
    @Autowired
    private AdoptionRepository repo;

    @Autowired
    private PetRepository repoPet;

      @Autowired
    private UserRepository repoUser;
    
    public List<Adoption> listAll() {        
        return repo.findAll();
    }
    
    public void save(Adoption adoption,String adopterUsername) {
        Pet pet = repoPet.save(new Pet(adoption.getPet().getName(),adoption.getPet().getSpecies()));
        adoption.setPet(pet);
        User userAdopter = repoUser.getUserByUsername(adopterUsername);
        adoption.setAdopter(userAdopter);
        repo.save(adoption);
    }
    
    public Adoption get(Long id) {
        return repo.findById(id).get();
    }
    
    public void delete(Long id) {
        repo.deleteById(id);
    }
}
