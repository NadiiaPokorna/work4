package dev.pokorna.io;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.pokorna.command.Command;
import dev.pokorna.model.ToyRoom;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class OutputSavedToyRooms extends FileIO implements Command {

    @Override
    public void execute() {
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(filePath);
        if (file.exists() && file.length() > 0) {
            try {
                List<ToyRoom> toyRooms = objectMapper.readValue(file, new TypeReference<List<ToyRoom>>() {});

                if (toyRooms.isEmpty()) {
                    System.out.println("No saved room in file: " + filePath);
                } else {
                    System.out.println("Saved ToyRooms:");
                    for (ToyRoom room : toyRooms) {
                        System.out.println(room);
                    }
                }
            } catch (IOException e) {
                System.err.println("Failed to read ToyRooms from file: " + e.getMessage());
            }
        } else {
            System.out.println("No saved room in file: " + filePath);
        }
    }

    @Override
    public String name() {
        return "Вивести всі збережені ігрові кімнати";
    }

    @Override
    public String getDescription() {
        return "Команда дозволяє переглянути всі збережені ігрові кімнати у файлі " + filePath;
    }
}
