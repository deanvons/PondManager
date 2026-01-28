package no.loopacademy.HelloSpringExperiments.Services;

import jakarta.transaction.Transactional;
import no.loopacademy.HelloSpringExperiments.Models.Pond;
import no.loopacademy.HelloSpringExperiments.Models.Worbler;
import no.loopacademy.HelloSpringExperiments.Repositories.PondRepository;
import no.loopacademy.HelloSpringExperiments.Repositories.WorblerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorblerService {

    private final WorblerRepository worblerRepo;
    private final PondRepository pondRepo;

    public WorblerService(WorblerRepository worblerRepo,
                          PondRepository pondRepo) {
        this.worblerRepo = worblerRepo;
        this.pondRepo = pondRepo;
    }

    public List<Worbler> getAll() {
        return worblerRepo.findAll();
    }

    public Worbler getById(int id) {
        return worblerRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Worbler not found: " + id));
    }

    @Transactional
    public Worbler create(Worbler worbler) {
        worbler.setId(null); // explicit insert semantics
        return worblerRepo.save(worbler);
    }

    /**
     * Explicit M:M update on the OWNING side.
     * This is where the join table is actually written.
     */
    @Transactional
    public Worbler assignToPond(int worblerId, int pondId) {
        Worbler worbler = getById(worblerId);
        Pond pond = pondRepo.getById(pondId)
                .orElseThrow(() -> new RuntimeException("Pond not found: " + pondId));

        worbler.getPonds().add(pond);
        return worbler; // dirty-checked; join table updated on commit
    }

    @Transactional
    public Worbler removeFromPond(int worblerId, int pondId) {
        Worbler worbler = getById(worblerId);

        worbler.getPonds()
                .removeIf(p -> p.getId().equals(pondId));

        return worbler;
    }

    @Transactional
    public void delete(int id) {
        worblerRepo.deleteById(id);
    }
}
