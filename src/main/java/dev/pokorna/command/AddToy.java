package dev.pokorna.command;

import dev.pokorna.Main;
import dev.pokorna.exception.CriticalError;
import dev.pokorna.io.WriteToyRoom;
import dev.pokorna.model.Toy;
import dev.pokorna.model.ToyRoom;
import dev.pokorna.model.ToySection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class AddToy implements Command {

    private static final Logger logger = LoggerFactory.getLogger(AddToy.class);

    private final ToyRoom toyRoom;

    public AddToy(ToyRoom toyRoom) {
        this.toyRoom = toyRoom;
    }

    @Override
    public void execute() throws CriticalError {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть назву іграшки: ");
        String name = scanner.nextLine();

        System.out.print("Введіть тип іграшки (машинка, лялька, м'яч тощо): ");
        String type = scanner.nextLine();

        System.out.print("Введіть розмір іграшки (маленький, середній, великий): ");
        String size = scanner.nextLine();

        System.out.print("Введіть вартість іграшки: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        Toy newToy = new Toy(name, type, size, price);
        System.out.print("Доступні секції: ");
        for (int i = 0; i < toyRoom.getSections().size(); i++) {
            System.out.println((i + 1) + ". " + toyRoom.getSections().get(i).getSectionName());
        }

        System.out.print("Виберіть номер секції для додавання іграшки: ");
        int sectionIndex = scanner.nextInt() - 1;

        if (sectionIndex >= 0 && sectionIndex < toyRoom.getSections().size()) {
            ToySection selectedSection = toyRoom.getSections().get(sectionIndex);

            toyRoom.addToyToSection(newToy, selectedSection);
            WriteToyRoom writeToyRoom = new WriteToyRoom(toyRoom);
            writeToyRoom.execute();
            System.out.println("Іграшка успішно додана до секції: " + selectedSection.getSectionName());
            logger.info("[Додавання іграшки] Іграшка {} успішно додана до секції: {}", newToy, selectedSection.getSectionName());
        } else {
            System.out.println("Помилка: невірний вибір секції.");
            logger.error("Помилка: невірний вибір секції.");
            throw new CriticalError("Невірний вибір секції");
        }
    }

    @Override
    public String name() {
        return "Додати нову іграшку";
    }

    @Override
    public String getDescription() {
        return "Функція дозволяє додати нові іграшки. " +
                "Щоб додати нову необхідно ввести назву, тип, розмір і секцію в яку бажаєте її додати.";
    }
}

