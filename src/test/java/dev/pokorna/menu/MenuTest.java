package dev.pokorna.menu;

import dev.pokorna.command.Command;
import dev.pokorna.exception.CriticalError;
import junit.framework.TestCase;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

public class MenuTest extends TestCase {

    private Menu menu;
    private Command command1, command2;
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Override
    public void setUp() throws Exception {
        super.setUp();
        menu = new Menu();
        command1 = new MockCommand("Command 1");
        command2 = new MockCommand("Command 2");
        System.setOut(new PrintStream(outContent));
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        System.setOut(originalOut);
    }

    public void testSetCommand() {
        menu.setCommand(1, command1);
        menu.setCommand(2, command2);

        List<Command> commands = menu.toList();
        assertEquals(2, commands.size());
        assertTrue(commands.contains(command1));
        assertTrue(commands.contains(command2));
    }

    public void testShowMenu() {
        menu.setCommand(1, command1);
        menu.setCommand(2, command2);

        menu.showMenu();
        String expectedOutput = "1. Command 1" + System.lineSeparator() + "2. Command 2" + System.lineSeparator();
        assertEquals(expectedOutput, outContent.toString());
    }

    public void testExecuteCommand() throws CriticalError {
        menu.setCommand(1, command1);
        menu.setCommand(2, command2);

        menu.executeCommand(1);
        assertEquals("Command 1 executed." + System.lineSeparator(), outContent.toString());

        outContent.reset();
        menu.executeCommand(2);
        assertEquals("Command 2 executed." + System.lineSeparator(), outContent.toString());

        outContent.reset();
        menu.executeCommand(3);
        assertEquals("Невідома команда. Спробуйте ще раз." + System.lineSeparator(), outContent.toString());
    }

    public void testToList() {
        menu.setCommand(1, command1);
        menu.setCommand(2, command2);

        List<Command> commands = menu.toList();
        assertEquals(2, commands.size());
        assertTrue(commands.contains(command1));
        assertTrue(commands.contains(command2));
    }
}
