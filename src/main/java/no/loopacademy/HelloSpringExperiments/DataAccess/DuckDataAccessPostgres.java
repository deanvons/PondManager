package no.loopacademy.HelloSpringExperiments.DataAccess;

import no.loopacademy.HelloSpringExperiments.Entities.Duck;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component // ℹ️ this is the only class that implements DuckDataAccessOption so Spring assumes you want this, if our app had more (DuckDataAccessFlatFile or DuckDataAccessXML) we would need to tell Spring which one to use with @Primary
public class DuckDataAccessPostgres implements DuckDataAccessOption{

    // where is my DB
    private String dburl= "jdbc:postgresql://localhost:5432/PondManager";
    private String username= "postgres";
    private String password= "postgres";
    // access??

    /* -------------------------
               READ ALL
               ------------------------- */
    public List<Duck> selectAll() {

        List<Duck> ducks = new ArrayList<>();

        // SQL
        String sql = "SELECT id, nickname, age, weight FROM duck";

        try (
                // Connect
                Connection connection = DriverManager.getConnection(dburl, username, password);
                // Execute
                PreparedStatement stmt = connection.prepareStatement(sql);
                // Handle Result
                ResultSet rs = stmt.executeQuery()
        ) {

            // Package result
            while (rs.next()) {
                Duck duck = new Duck();
                duck.setId(rs.getInt("id"));
                duck.setNickName(rs.getString("nickname"));
                duck.setAge(rs.getInt("age"));
                duck.setWeight(rs.getDouble("weight"));

                ducks.add(duck);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch ducks", e);
        }

        // Return
        return ducks;
    }

    /* -------------------------
       READ BY ID
       ------------------------- */
    public Optional<Duck> selectById(int id) {

        String sql = "SELECT id, nickname, age, weight FROM duck WHERE id = ?";
        Duck duck = null;

        try (
                Connection connection = DriverManager.getConnection(dburl, username, password);
                PreparedStatement stmt = connection.prepareStatement(sql)
        ) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    duck = mapRow(rs);
                }

            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to fetch duck with id " + id, e);
        }

        return Optional.ofNullable(duck);
    }

    /* -------------------------
       CREATE
       ------------------------- */
    public Duck insert(Duck duck) {

        String sql = """
            INSERT INTO duck (nickname, age, weight)
            VALUES (?, ?, ?)
            RETURNING id
        """;

        try (
                Connection connection = DriverManager.getConnection(dburl, username, password);
                PreparedStatement stmt = connection.prepareStatement(sql)
        ) {

            stmt.setString(1, duck.getNickName());
            stmt.setInt(2, duck.getAge());
            stmt.setDouble(3, duck.getWeight());

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    duck.setId(rs.getInt("id"));
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Failed to insert duck", e);
        }

        return duck;
    }

    /* -------------------------
       UPDATE
       ------------------------- */
    public boolean update(Duck duck) {

        String sql = """
            UPDATE duck
            SET nickname = ?, age = ?, weight = ?
            WHERE id = ?
        """;

        try (
                Connection connection = DriverManager.getConnection(dburl, username, password);
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

    /* -------------------------
       DELETE
       ------------------------- */
    public boolean deleteById(int id) {

        String sql = "DELETE FROM duck WHERE id = ?";

        try (
                Connection connection = DriverManager.getConnection(dburl, username, password);
                PreparedStatement stmt = connection.prepareStatement(sql)
        ) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() == 1;

        } catch (SQLException e) {
            throw new RuntimeException("Failed to delete duck with id " + id, e);
        }
    }

    /* -------------------------
       MAPPING (centralized!)
       ------------------------- */
    private Duck mapRow(ResultSet rs) throws SQLException {
        Duck duck = new Duck();
        duck.setId(rs.getInt("id"));
        duck.setNickName(rs.getString("nickname"));
        duck.setAge(rs.getInt("age"));
        duck.setWeight(rs.getDouble("weight"));
        return duck;
    }
}
