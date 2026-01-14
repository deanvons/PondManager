package no.loopacademy.HelloSpringExperiments.DataAccess;

import no.loopacademy.HelloSpringExperiments.Entities.Duck;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DuckDataAccess {

    // where is my DB
    private String dburl= "jdbc:postgresql://localhost:5432/PondManager";
    private String username= "postgres";
    private String password= "postgres";
    // access??


    public List<Duck> findAll() {

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


    }



















