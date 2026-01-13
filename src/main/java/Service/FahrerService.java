package Service;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Comparator;
import java.util.stream.Collectors;

import Model.Fahrer;

public class FahrerService {

    public List<Fahrer> filterTeamByStatus(List<Fahrer> fahrerList, String teamName) {
        return fahrerList.stream()
                .filter(fahrer -> fahrer.getTeam().equalsIgnoreCase(teamName))
                .filter(fahrer -> fahrer.getStatus().equals("ACTIVE"))
                .collect(Collectors.toList());
    }

    public List<Fahrer> getSortedFahrer(List<Fahrer> fahrerList) {
        List<Fahrer> sortedList = new ArrayList<>();

        sortedList.sort(Comparator
                .comparing(Fahrer::getSkillLevel).reversed()
                .thenComparing(Fahrer::getName));

        return fahrerList;
    }

    public void saveFahrerToFile(List<Fahrer> fahrerList, String fileName) throws IOException {
        try (PrintWriter writer = new PrintWriter(fileName)) {
            for (Fahrer fahrer : fahrerList) {
                writer.println(fahrer.toString());
            }
        }
    }
}
