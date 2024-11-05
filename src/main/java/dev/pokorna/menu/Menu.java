package dev.pokorna.menu;

import dev.pokorna.command.Command;
import dev.pokorna.exception.CriticalError;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Menu {
    private final Map<Integer, Command> commands = new HashMap<>();

    public void setCommand(int option, Command command) {
        commands.put(option, command);
    }

    public void showMenu() {
        for (Map.Entry<Integer, Command> entry : commands.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue().name());
        }
    }

    public void executeCommand(int option) throws CriticalError {
        Command command = commands.get(option);
        if (command != null) {
            command.execute();
        } else {
            System.out.println("Невідома команда. Спробуйте ще раз.");
        }
    }

    public List<Command> toList() {
        return new ArrayList<>(commands.values());
    }
}
