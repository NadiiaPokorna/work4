package dev.pokorna.command;

import dev.pokorna.menu.Menu;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class HelpTest {

    private Menu mockMenu;
    private Help helpCommand;
    private ByteArrayOutputStream outputStream;

    @Before
    public void setUp() {
        mockMenu = new Menu();
        helpCommand = new Help(mockMenu);
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    public void testExecuteWithNoCommands() {
        helpCommand.execute();
        String output = outputStream.toString().trim();
        assertTrue("Expected no output when there are no commands.", output.isEmpty());
    }

    @Test
    public void testName() {
        assertEquals("Довідка", helpCommand.name());
    }

    @Test
    public void testGetDescription() {
        assertEquals("Дозволяє переглянути інформацію про команди програми.", helpCommand.getDescription());
    }
}
