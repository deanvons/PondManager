package no.loopacademy.HelloSpringExperiments.Services;

import jakarta.transaction.Transactional;
import no.loopacademy.HelloSpringExperiments.Models.Pond;
import no.loopacademy.HelloSpringExperiments.Repositories.PondRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PondService {

    private final PondRepository pondRepo;

    public PondService(PondRepository pondRepo) {
        this.pondRepo = pondRepo;
    }

    public List<Pond> getAll() {
        return pondRepo.getAll();
    }

    public Pond getById(int id) {
        return pondRepo.getById(id)
                .orElseThrow(() -> new RuntimeException("Pond not found: " + id));
    }

    @Transactional
    public Pond create(Pond pond) {
        return pondRepo.create(pond);
    }

    @Transactional
    public Pond update(int id, Pond updated) {
        Pond managed = getById(id); // managed in this TX
        managed.setName(updated.getName());
        managed.setLocation(updated.getLocation());
        return managed; // no save/merge
    }

    @Transactional
    public void delete(int id) {
        boolean deleted = pondRepo.deleteById(id);
        if (!deleted) throw new RuntimeException("Pond not found: " + id);
    }
}
