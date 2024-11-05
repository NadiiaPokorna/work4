package dev.pokorna.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CriticalError extends Exception {
    private static final Logger logger = LoggerFactory.getLogger(CriticalError.class);
    private static final String EMAIL_SUBJECT = "Критична помилка";

    public CriticalError(String message) {
        super(message);
        sendEmail(message);
        logger.error(message);
    }

    private void sendEmail(String message) {
        String body = "Виникла критична помилка:\n\n" + "Повідомлення: " + message + "\n" +
                "\nОстанні запити логів:\n" +
                getLastLinesOfLog();

        EmailUtil.sendEmail(EMAIL_SUBJECT, body);
    }

    private String getLastLinesOfLog() {
        StringBuilder lastLines = new StringBuilder();
        String logFilePath = findLogFile();

        if (logFilePath == null) {
            return "Файл логу не знайдений.";
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(logFilePath))) {
            String line;
            int totalLines = 0;

            String[] buffer = new String[10];

            while ((line = reader.readLine()) != null) {
                buffer[totalLines % 10] = line;
                totalLines++;
            }

            int start = totalLines < 10 ? 0 : totalLines % 10;
            int count = Math.min(10, totalLines);

            for (int i = 0; i < count; i++) {
                lastLines.append(buffer[(start + i) % 10]).append("\n");
            }

        } catch (IOException e) {
            lastLines.append("Помилка читання логів: ").append(e.getMessage());
        }
        return lastLines.toString();
    }

    public String findLogFile() {
        String[] potentialPaths = {
                "logs/app.log",
                "logs/app-*.log",
                "app.log",
                "./logs/app.log"
        };

        for (String path : potentialPaths) {
            Path logPath = Paths.get(path);
            if (Files.exists(logPath)) {
                return logPath.toString();
            }
        }

        return null;
    }
}
