package dev.pokorna.command;

import dev.pokorna.exception.CriticalError;

public interface Command {
    void execute() throws CriticalError;
    String name();
    String getDescription();
}
