package dev.pokorna.command;

import dev.pokorna.menu.Menu;

import java.util.List;

public class Help implements Command {

    private final Menu menu;

    public Help(Menu menu) {
        this.menu = menu;
    }

    @Override
    public void execute() {
        List<Command> commandList = menu.toList();
        if (!commandList.isEmpty()) {
            System.out.println("\nПерегляд довідки: ");
            int counter = 1;
            for (Command command : commandList) {
                System.out.println(counter + ". " + command.name() + ": " + command.getDescription());
                counter++;
            }
        }
    }

    @Override
    public String name() {
        return "Довідка";
    }

    @Override
    public String getDescription() {
        return "Дозволяє переглянути інформацію про команди програми.";
    }
}
