package no.loopacademy.HelloSpringExperiments.Controllers;

import no.loopacademy.HelloSpringExperiments.Entities.Duck;
import no.loopacademy.HelloSpringExperiments.Services.DuckService;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class DuckConsoleController {

    private final DuckService service;

    public DuckConsoleController(DuckService service) {
        this.service = service;
    }

    public void start() {
        System.out.println("Running......let's do some duck stuff");
        System.out.println("Duck (console) listening for commands. Type: help");

        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.print("> ");
                String line = sc.nextLine().trim();
                if (line.isBlank()) continue;

                String[] parts = line.split("\\s+");
                String cmd = parts[0].toLowerCase();

                try {
                    switch (cmd) {
                        case "help" -> help();
                        case "exit" -> { System.out.println("Bye."); return; }

                        case "list" -> list();                 // GET /ducks
                        case "get" -> get(parts);              // GET /ducks/{id}
                        case "create" -> create(parts);        // POST /ducks
                        case "delete" -> delete(parts);        // DELETE /ducks/{id}

                        default -> System.out.println("Unknown command. Type: help");
                    }
                } catch (IllegalArgumentException ex) {
                    // "400/404" style response
                    System.out.println("ERROR: " + ex.getMessage());
                }
            }
        }
    }

    private void help() {
        System.out.println("""
            Commands:
              list
              get <id>
              create <nickName> <age> <weight>
              delete <id>
              exit
            """);
    }

    private void list() {
        var ducks = service.listDucks();
        if (ducks.isEmpty()) {
            System.out.println("(empty)");
            return;
        }

        ducks.forEach(d ->
                System.out.println(d.getId() + " | " + d.getNickName() + " | age=" + d.getAge() + " | weight=" + d.getWeight())
        );
    }

    private void get(String[] parts) {
        if (parts.length < 2) throw new IllegalArgumentException("Usage: get <id>");
        int id = Integer.parseInt(parts[1]);

        //Duck d = service.getDuck(id);
        //System.out.println(d.getId() + " | " + d.getNickName() + " | age=" + d.getAge() + " | weight=" + d.getWeight());
    }

    private void create(String[] parts) {
        if (parts.length < 4) throw new IllegalArgumentException("Usage: create <nickName> <age> <weight>");

        String nick = parts[1];
        int age = Integer.parseInt(parts[2]);
        double weight = Double.parseDouble(parts[3]);

        Duck duck = new Duck();
        duck.setNickName(nick);
        duck.setAge(age);
        duck.setWeight(weight);

        Duck saved = service.registerDuck(duck);
        System.out.println("CREATED id=" + saved.getId());
    }

    private void delete(String[] parts) {
        if (parts.length < 2) throw new IllegalArgumentException("Usage: delete <id>");
        int id = Integer.parseInt(parts[1]);

        service.removeDuck(id);
        System.out.println("DELETED id=" + id);
    }
}
