package dev.pokorna;

import java.util.Scanner;

import dev.pokorna.command.*;
import dev.pokorna.exception.CriticalError;
import dev.pokorna.io.GetDataFile;
import dev.pokorna.io.OutputSavedToyRooms;
import dev.pokorna.menu.Menu;
import dev.pokorna.model.ToyRoom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws CriticalError {
        ToyRoom toyRoom = new ToyRoom();
        logger.info("Створена нова іграшкова кімната");

        Menu menu = new Menu();
        menu.setCommand(1, new CreateToySection(toyRoom));
        menu.setCommand(2, new AddToy(toyRoom));
        menu.setCommand(3, new SortToys(toyRoom));
        menu.setCommand(4, new FindToys(toyRoom));
        menu.setCommand(5, new CheckBudget(toyRoom));
        menu.setCommand(6, new OutputSavedToyRooms());
        menu.setCommand(7, new GetDataFile());
        menu.setCommand(8, new Help(menu));
        menu.setCommand(9, new Exit());
        logger.info("Додано пункти меню");

        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n========= Іграшкова кімната =========");
            menu.showMenu();
            System.out.print("Виберіть пункт меню: ");
            logger.info("Виведено меню");
            choice = scanner.nextInt();
            logger.info("Вибрано {} пункт меню", choice);
            menu.executeCommand(choice);
        } while (choice != 9);
        scanner.close();
    }
}