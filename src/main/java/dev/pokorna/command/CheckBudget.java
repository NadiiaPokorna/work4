package dev.pokorna.command;

import dev.pokorna.model.ToyRoom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CheckBudget implements Command {

    private static final Logger logger = LoggerFactory.getLogger(CheckBudget.class);

    private final ToyRoom toyRoom;

    public CheckBudget(ToyRoom toyRoom) {
        this.toyRoom = toyRoom;
    }

    @Override
    public void execute() {
        double remainingBudget = toyRoom.getBudgetManager().getRemainingBudget();
        System.out.println("Залишок бюджету: " + remainingBudget + " грн");
        logger.info("[Перевірка бюджету] Залишок бюджету: {} грн", remainingBudget);
    }

    @Override
    public String name() {
        return "Перевірити бюджет";
    }

    @Override
    public String getDescription() {
        return "Функція дозволяє побачити поточний баланс.";
    }
}
