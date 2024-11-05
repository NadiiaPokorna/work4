package dev.pokorna.menu;

import dev.pokorna.command.Command;

public record MockCommand(String name) implements Command {
    @Override
    public void execute() {
        System.out.println(name + " executed.");
    }
    @Override
    public String getDescription() {
        return name + ":description";
    }
}
