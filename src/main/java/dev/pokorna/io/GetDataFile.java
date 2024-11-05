package dev.pokorna.io;

import dev.pokorna.command.Command;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

public class GetDataFile extends FileIO implements Command {

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the save path for the copied file (including filename): ");
        String inputPath = scanner.nextLine();
        scanner.close();
        Path sourcePath = Path.of(filePath);
        Path destinationPath = Path.of(inputPath);
        try {
            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File copied successfully to: " + destinationPath);
        } catch (IOException e) {
            System.err.println("Failed to copy the file: " + e.getMessage());
        }
    }

    @Override
    public String name() {
        return "Копіювати збережені дані у іншу директорію";
    }

    @Override
    public String getDescription() {
        return "Команда дозволяє скопіювати файл [" + filePath + "] у іншу директорію, яка буде вказана";
    }
}
