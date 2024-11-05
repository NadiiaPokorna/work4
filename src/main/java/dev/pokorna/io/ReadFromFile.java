package dev.pokorna.io;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import dev.pokorna.command.Command;
import dev.pokorna.model.ToyRoom;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class ReadFromFile extends FileIO implements Command {

    private final File inputFile;

    public ReadFromFile() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введіть шлях до файлу: ");
        String inputFilePath = scanner.nextLine();
        this.inputFile = new File(inputFilePath);
    }

    @Override
    public void execute() {
        ObjectMapper objectMapper = new ObjectMapper();
        if (inputFile.exists() && inputFile.length() > 0) {
            try {
                List<ToyRoom> toyRooms = objectMapper.readValue(inputFile, new TypeReference<List<ToyRoom>>() {});
                if (toyRooms.isEmpty()) {
                    System.out.println("Немає збережених кімнат у файлі: " + inputFile.getPath());
                } else {
                    System.out.println("Збережені ToyRooms:");
                    for (ToyRoom room : toyRooms) {
                        System.out.println(room);
                    }
                    objectMapper.writeValue(new File(filePath), toyRooms);
                    System.out.println("ToyRooms успішно збережені у: " + filePath);
                }
            } catch (IOException e) {
                System.out.println("Неможливо прочитати файл...");
            }
        } else {
            System.out.println("Неможливо прочитати файл...");
        }
    }

    @Override
    public String name() {
        return "Прочитати з файлу і записати у збережні";
    }

    @Override
    public String getDescription() {
        return "Системна команда";
    }
}

