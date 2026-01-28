package no.loopacademy.HelloSpringExperiments;

import no.loopacademy.HelloSpringExperiments.Models.Duck;
import no.loopacademy.HelloSpringExperiments.Repositories.DuckRepository;
import no.loopacademy.HelloSpringExperiments.Repositories.DuckRepositoryImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlConfig;

@JdbcTest
@ActiveProfiles("test")

@SqlConfig(transactionMode = SqlConfig.TransactionMode.ISOLATED)
@Sql(scripts="/testschema.sql", executionPhase = ExecutionPhase.BEFORE_TEST_CLASS)
@Sql(scripts="/testdata.sql", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@Import(DuckRepositoryImpl.class) // bring in your repo bean
class DuckRepositoryImplTest {

    @Autowired
    DuckRepository duckRepo;

    @Test
    void getAll_returnsSeededDucks() {
        List<Duck> ducks = duckRepo.getAll();
        assertThat(ducks).hasSize(2);
        assertThat(ducks).extracting(Duck::getNickName)
                .containsExactlyInAnyOrder("Daffy", "Donald");
    }

    @Test
    void getById_returnsDuck_whenExists() {
        Duck duck = duckRepo.getById(1).orElseThrow();
        assertThat(duck.getNickName()).isEqualTo("Daffy");
    }

    @Test
    void register_insertsAndReturnsId() {
        Duck d = new Duck();
        d.setNickName("Howard");
        d.setAge(3);
        d.setWeight(1.9);

        Duck saved = duckRepo.register(d);

        assertThat(saved.getId()).isNotNull();
        assertThat(duckRepo.getById(saved.getId())).isPresent();
    }

    @Test
    void update_returnsTrue_whenRowUpdated() {
        Duck duck = duckRepo.getById(1).orElseThrow();
        duck.setNickName("DaffyUpdated");

        boolean ok = duckRepo.update(duck);

        assertThat(ok).isTrue();
        assertThat(duckRepo.getById(1).orElseThrow().getNickName()).isEqualTo("DaffyUpdated");
    }

    @Test
    void unregisterById_deletesRow() {
        boolean ok = duckRepo.unregisterById(2);

        assertThat(ok).isTrue();
        assertThat(duckRepo.getById(2)).isEmpty();
        assertThat(duckRepo.getAll()).hasSize(1);
    }
}
