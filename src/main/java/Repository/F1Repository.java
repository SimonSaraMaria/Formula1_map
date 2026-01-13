package Repository;
import com.fasterxml.jackson.databind.ObjectMapper;
import Model.*;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class F1Repository {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Fahrer> readFahrer(String filepath) throws IOException {
        return Arrays.asList(objectMapper.readValue(new File(filepath), Fahrer[].class));
    }

    public List<RennenEreignis> readRennenEreignis(String filepath) throws IOException {
        return Arrays.asList(objectMapper.readValue(new File(filepath), RennenEreignis[].class));
    }

    public List<Strafe> readStrafe(String filepath) throws IOException {
        return Arrays.asList(objectMapper.readValue(new File(filepath), Strafe[].class));
    }
}
