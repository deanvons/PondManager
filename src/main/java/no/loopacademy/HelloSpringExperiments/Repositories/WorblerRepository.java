package no.loopacademy.HelloSpringExperiments.Repositories;

import no.loopacademy.HelloSpringExperiments.Models.Worbler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WorblerRepository extends JpaRepository<Worbler, Integer> {

    // exact match
    List<Worbler> findByName(String name);

    @Query("""
           SELECT w
           FROM Worbler w
           JOIN w.ponds p
           WHERE p.id = :pondId
           """)
    List<Worbler> getWorblersByPond(@Param("pondId") int pondId);
}
