package no.loopacademy.HelloSpringExperiments.Repositories;

import no.loopacademy.HelloSpringExperiments.Models.Duck;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.*;

@Repository
public class DuckRepositoryImpl implements DuckRepository {

    private final DataSource dataSource;

    public DuckRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Duck> getAll() {
        List<Duck> ducks = new ArrayList<>();
        String sql = "SELECT id, nickname, age, weight FROM duck";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                ducks.add(mapRow(rs));
            }
            return ducks;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch ducks", e);
        }
    }

    @Override
    public Optional<Duck> getById(int id) {
        String sql = "SELECT id, nickname, age, weight FROM duck WHERE id = ?";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) return Optional.of(mapRow(rs));
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch duck with id " + id, e);
        }
    }

    @Override
    public Duck register(Duck duck) {
        // Portable across Postgres + H2: use generated keys instead of RETURNING
        String sql = "INSERT INTO duck (nickname, age, weight) VALUES (?, ?, ?)";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)
        ) {
            stmt.setString(1, duck.getNickName());
            stmt.setInt(2, duck.getAge());
            stmt.setDouble(3, duck.getWeight());

            int affected = stmt.executeUpdate();
            if (affected != 1) throw new RuntimeException("Insert failed, affected rows: " + affected);

            try (ResultSet keys = stmt.getGeneratedKeys()) {
                if (keys.next()) {
                    duck.setId(keys.getInt(1));
                } else {
                    throw new RuntimeException("Insert succeeded but no generated key returned");
                }
            }

            return duck;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to insert duck", e);
        }
    }

    @Override
    public boolean update(Duck duck) {
        String sql = "UPDATE duck SET nickname = ?, age = ?, weight = ? WHERE id = ?";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)
        ) {
            stmt.setString(1, duck.getNickName());
            stmt.setInt(2, duck.getAge());
            stmt.setDouble(3, duck.getWeight());
            stmt.setInt(4, duck.getId());

            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to update duck with id " + duck.getId(), e);
        }
    }

    @Override
    public boolean unregisterById(int id) {
        String sql = "DELETE FROM duck WHERE id = ?";

        try (
                Connection connection = dataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() == 1;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete duck with id " + id, e);
        }
    }

    private Duck mapRow(ResultSet rs) throws SQLException {
        Duck duck = new Duck();
        duck.setId(rs.getInt("id"));
        duck.setNickName(rs.getString("nickname"));
        duck.setAge(rs.getInt("age"));
        duck.setWeight(rs.getDouble("weight"));
        return duck;
    }
}
