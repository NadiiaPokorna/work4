package dev.pokorna.command;

import dev.pokorna.io.WriteToyRoom;
import dev.pokorna.model.ToyRoom;
import dev.pokorna.model.ToySection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class CreateToySection implements Command {

    private static final Logger logger = LoggerFactory.getLogger(CreateToySection.class);

    private final ToyRoom toyRoom;

    public CreateToySection(ToyRoom toyRoom) {
        this.toyRoom = toyRoom;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введіть назву нової секції іграшок: ");
        String sectionName = scanner.nextLine();

        ToySection newSection = new ToySection(sectionName);
        toyRoom.addSection(newSection);
        logger.info("[Додавання нової секції іграшок] До кімнати {} було додано нову секцію, назва: {}", toyRoom.getId(), newSection.getSectionName());

        WriteToyRoom writeToyRoom = new WriteToyRoom(toyRoom);
        writeToyRoom.execute();
        logger.info("[Додавання нової секції іграшок] Успішно записано у файл");
        System.out.println("Секція '" + sectionName + "' успішно створена!");
    }

    @Override
    public String name() {
        return "Створити секцію у ляльковій кімнаті";
    }

    @Override
    public String getDescription() {
        return "Функція дозволяє створити нову секцію у ляльковій кімнаті." +
                "Щоб додати нову секцію необхідно ввести назву секції.";
    }
}

