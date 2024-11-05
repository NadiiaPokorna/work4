package dev.pokorna.command;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Exit implements Command {

    private static final Logger logger = LoggerFactory.getLogger(Exit.class);

    @Override
    public void execute() {
        System.out.println("Вихід із програми...");
        logger.info("Вихід із програми");
        System.exit(0);
    }

    @Override
    public String name() {
        return "Вийти з програми";
    }

    @Override
    public String getDescription() {
        return "Команда виходу із програми";
    }
}
