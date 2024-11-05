package dev.pokorna.io;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.pokorna.command.Command;
import dev.pokorna.model.ToyRoom;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WriteToyRoom extends FileIO implements Command {

    private final ToyRoom toyRoom;

    public WriteToyRoom(ToyRoom toyRoom) {
        this.toyRoom = toyRoom;
    }

    @Override
    public void execute() {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(filePath);
        List<ToyRoom> toyRooms = new ArrayList<>();

        toyRooms.add(this.toyRoom);

        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, toyRooms);
            System.out.println("ToyRoom saved to file: " + filePath);
        } catch (IOException e) {
            System.err.println("Failed to save ToyRoom to file: " + e.getMessage());
        }
    }

    @Override
    public String name() {
        return "Записати іграшкову кімнату у файл";
    }

    @Override
    public String getDescription() {
        return "Системна команда";
    }
}
