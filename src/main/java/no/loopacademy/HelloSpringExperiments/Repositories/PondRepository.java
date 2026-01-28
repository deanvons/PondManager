package no.loopacademy.HelloSpringExperiments.Repositories;

import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import no.loopacademy.HelloSpringExperiments.Models.Pond;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PondRepository {

    @PersistenceContext
    private EntityManager em;


    public List<Pond> getAll() {
        return em.createQuery("SELECT p FROM Pond p", Pond.class)
                .getResultList();
    }


    public Optional<Pond> getById(int id) {
        return Optional.ofNullable(em.find(Pond.class, id));
    }


    @Transactional
    public Pond create(Pond pond) {
        pond.setId(null);     // explicit “insert”
        em.persist(pond);     // pond is now managed
        // no flush needed unless you want to force INSERT immediately
        return pond;
    }


    @Transactional
    public boolean deleteById(int id) {
        Pond managed = em.find(Pond.class, id);
        if (managed == null) return false;

        em.remove(managed);
        return true;
    }

    /**
     * Optional: keep merge as an explicit "detached update" path.
     * Not your default update style if you want EF-like clarity.
     */
    @Transactional
    public Pond mergeDetached(Pond detached) {
        return em.merge(detached); // returns managed instance
    }
}
