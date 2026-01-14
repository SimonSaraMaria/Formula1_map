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

        // Updated to use the EreignisTyp enum
        // No quotes are needed, and 'default' is optional if all Enum values are covered
        return switch (e.getTyp()) {
            case OVERTAKE -> points + 1;
            case FASTEST_LAP -> points + 2 * (lap % 3);
            case TRACK_LIMITS -> points - 5;
            case COLLISION -> points - 10 - lap;
            case PIT_STOP -> (lap <= 10) ? points + 2 : points;
        };
    }

    public void printTop5AndWinningTeam(List<Fahrer> fahrerListe, List<RennenEreignis> eventListe, List<Strafe> strafenListe) {
        Map<Integer, Integer> driverScores = new HashMap<>();

        for (Fahrer f : fahrerListe) {
            driverScores.put(f.getId(), 0);
        }

        for (RennenEreignis e : eventListe) {
            int current = driverScores.getOrDefault(e.getFahrerId(), 0);
            driverScores.put(e.getFahrerId(), current + calculateComputedPoints(e));
        }

        for (Strafe s : strafenListe) {
            int current = driverScores.getOrDefault(s.getFahrerId(), 0);
            driverScores.put(s.getFahrerId(), current - s.getSeconds());
        }

        List<Fahrer> sortedFahrer = new ArrayList<>(fahrerListe);
        sortedFahrer.sort((f1, f2) -> {
            int score1 = driverScores.get(f1.getId());
            int score2 = driverScores.get(f2.getId());
            if (score1 != score2) return Integer.compare(score2, score1);
            return f1.getName().compareTo(f2.getName());
        });

        System.out.println("Top 5 Ranking:");
        for (int i = 0; i < 5 && i < sortedFahrer.size(); i++) {
            Fahrer f = sortedFahrer.get(i);
            System.out.println(f.getName() + " (" + f.getTeam() + ") -> " + driverScores.get(f.getId()));
        }

        if (!sortedFahrer.isEmpty()) {
            System.out.println("Winning Team: " + sortedFahrer.get(0).getTeam());
        }
    }

    public void generateReport(List<RennenEreignis> ereignis, String fileName) throws IOException {
        // Map now uses EreignisTyp enum as the key
        Map<EreignisTyp, Integer> counts = new LinkedHashMap<>();

        // Automatically initialize the map with all values from the Enum
        for (EreignisTyp typ : EreignisTyp.values()) {
            counts.put(typ, 0);
        }

        for (RennenEreignis e : ereignis) {
            EreignisTyp type = e.getTyp();
            counts.put(type, counts.get(type) + 1);
        }

        try (PrintWriter writer = new PrintWriter(fileName)) {
            for (Map.Entry<EreignisTyp, Integer> entry : counts.entrySet()) {
                writer.println(entry.getKey() + " -> " + entry.getValue());
            }
        }
    }
}
