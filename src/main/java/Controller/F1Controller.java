package Controller;
import Model.*;
import Repository.F1Repository;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class F1Controller {
    public void run() {
        try {
            F1Repository repo = new F1Repository();
            Scanner scanner = new Scanner(System.in);

            List<Fahrer> fahrer = repo.readFahrer("drivers.json");
            List<Strafe> strafe = repo.readStrafe("penalties.json");
            List<RennenEreignis> ereignis = repo.readRennenEreignis("events.json");

            boolean running = true;
            while (running) {
                System.out.println("\n--- Hunger Games Menu ---");
                System.out.println("1. Task 1: Show Data");
//                System.out.println("2. Task 2: Filter by District and Status");
//                System.out.println("3. Task 3: Sort tributes by skill level");
//                System.out.println("4. Task 4: Saving sorted tributes to file");
//                System.out.println("5. Task 5: Computed points for first 5 events");
//                System.out.println("6. Task 6: Top 5 tributes");
//                System.out.println("7. Task 7: Arena report");
                System.out.println("0. Exit");
                System.out.print("Choice: ");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1 -> {
                        System.out.println("Fahrer loaded: " + fahrer.size());
                        System.out.println("Ereignisse loaded: " + ereignis.size());
                        System.out.println("Strafen loaded: " + strafe.size());
                        for (Fahrer f : fahrer) {
                            System.out.println(f);
                        }
                    }
//                    case 2 -> {
//                        System.out.print("Input district: ");
//                        int districtInput = scanner.nextInt();
//                        List<Tribut> filtered = tributeService.filterAliveByDistrict(tributes, districtInput);
//                        for (Tribut t : filtered) {
//                            System.out.println(t);
//                        }
//                    }
//                    case 3 -> {
//                        System.out.print("Sorted tributes: ");
//                        List<Tribut> sorted = tributeService.getSortedTributes(tributes);
//                        for (Tribut t : sorted) {
//                            System.out.println(t);
//                        }
//                    }
//                    case 4 -> {
//                        System.out.print("Saving sorted tributes: ");
//                        try {
//                            List<Tribut> sorted = tributeService.getSortedTributes(tributes);
//                            tributeService.saveTributesToFile(sorted, "tributes_sorted.txt");
//                            System.out.println("Successfully saved sorted tributes!");
//                        } catch (IOException e) {
//                            System.err.println(e);
//                        }
//                    }
//                    case 5 -> {
//                        System.out.print("Computed points: ");
//                        EventService eventService = new EventService();
//                        for (int i = 0; i < 5 && i < events.size(); i++) {
//                            Ereignis e = events.get(i);
//                            int computedPoints = eventService.calculateComputedPoints(e);
//                            System.out.println("Event" + e.getId() + "-> rawPoints" + e.getPoints() +
//                                    "-> computedPoints = " + computedPoints);
//                        }
//                    }
//                    case 6 -> {
//                        System.out.println("Top 5 Tributes:");
//                        EventService eventService = new EventService();
//                        var top5 = eventService.calculateTopTributes(tributes, events, gifts);
//                        int rank = 1;
//                        for (var entry : top5) {
//                            System.out.println(rank + ". " + entry.getKey().getName() + " -> " + entry.getValue());
//                            rank++;
//                        }
//                    }
//                    case 7 -> {
//                        System.out.println("Arena report:");
//                        try {
//                            EventService eventService = new EventService();
//                            eventService.generateArenaReport(events, "arena_report.txt");
//                            System.out.println("Successfully saved arena report!");
//                        } catch (Exception e) {
//                            System.err.println(e);
//                        }
//                    }
                    case 0 -> {
                        running = false;
                        System.out.println("Exiting...");
                    }
                    default -> System.out.println("Invalid choice. Try again.");
                }
            }
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}