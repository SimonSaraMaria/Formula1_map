package Service;
import Model.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.io.PrintWriter;
import java.util.Map;
import java.util.LinkedHashMap;

public class EreignisService {
    public int calculateComputedPoints(RennenEreignis e) {
        int points = e.getBasePoints();
        int lap = e.getLap();

        return switch (e.gettyp()) {
            case "OVERTAKE" ->points + 1;
            case "FASTEST_LAP" ->points + 2 * (lap % 3);
            case "TRACK_LIMITS" ->points - 5;
            case "COLLISION" ->points - 10 - lap;
            case "PIT_STOP" -> (lap <= 10) ? points + 2 : points;
            default -> points;
        };
    }

    public void printTop5AndWinningTeam(List<Fahrer> fahrerListe, List<RennenEreignis> eventListe, List<Strafe> strafenListe) {
        Map<Integer, Integer> driverScores = new HashMap<>();

        // Initialize scores for all drivers
        for (Fahrer f : fahrerListe) {
            driverScores.put(f.getId(), 0);
        }

        // Add computed points from events
        for (RennenEreignis e : eventListe) {
            int current = driverScores.getOrDefault(e.getFahrerId(), 0);
            driverScores.put(e.getFahrerId(), current + calculateComputedPoints(e));
        }

        // Subtract penalty seconds
        for (Strafe s : strafenListe) {
            int current = driverScores.getOrDefault(s.getFahrerId(), 0);
            driverScores.put(s.getFahrerId(), current - s.getSeconds());
        }

        // Create a list for sorting
        List<Fahrer> sortedFahrer = new ArrayList<>(fahrerListe);
        sortedFahrer.sort((f1, f2) -> {
            int score1 = driverScores.get(f1.getId());
            int score2 = driverScores.get(f2.getId());
            if (score1 != score2) return Integer.compare(score2, score1); // Descending score
            return f1.getName().compareTo(f2.getName()); // Ascending name
        });

        // Output Top 5
        for (int i = 0; i < 5 && i < sortedFahrer.size(); i++) {
            Fahrer f = sortedFahrer.get(i);
            System.out.println(f.getName() + " (" + f.getTeam() + ") -> " + driverScores.get(f.getId()));
        }

        // Output Winning Team (Team of Rank 1)
        if (!sortedFahrer.isEmpty()) {
            System.out.println("Winning Team: " + sortedFahrer.get(0).getTeam());
        }
    }

    public void generateReport(List<RennenEreignis> ereignis, String fileName) throws IOException {
        Map<String, Integer> counts = new LinkedHashMap<>();

        counts.put("OVERTAKE", 0);
        counts.put("FASTEST_LAP", 0);
        counts.put("PIT_STOP", 0);
        counts.put("TRACK_LIMITS", 0);
        counts.put("COLLISION", 0);

        for (RennenEreignis e : ereignis) {
            String type = e.gettyp();
            counts.put(type, counts.getOrDefault(type, 0) + 1);
        }

        try (PrintWriter writer = new PrintWriter(fileName)) {
            for (Map.Entry<String, Integer> e : counts.entrySet()) {
                writer.println(e.getKey() + " -> " + e.getValue());
            }
        }
    }
}
