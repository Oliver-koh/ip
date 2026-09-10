package nga.ui;

import java.util.Scanner;

import nga.command.CommandResult;
import nga.command.NgaCommandHandler;
import nga.parser.TaskParseException;

/** Reads console input and displays responses from the command handler. */
public class NgaUi {
    private static final String DIVIDER = "____________________________________________________________";
    private final NgaCommandHandler commandHandler;
    private final Scanner scanner;

    /** Creates a UI connected to standard input. */
    public NgaUi(NgaCommandHandler commandHandler) {
        this(commandHandler, new Scanner(System.in));
    }

    /** Creates a UI with an injectable scanner for tests. */
    public NgaUi(NgaCommandHandler commandHandler, Scanner scanner) {
        this.commandHandler = commandHandler;
        this.scanner = scanner;
    }

    /** Runs the console interaction until the user exits or input ends. */
    public void run() {
        System.out.println(DIVIDER + "\nHello! I'm Nga.\n" + DIVIDER);
        while (scanner.hasNextLine()) {
            System.out.println(DIVIDER);
            try {
                CommandResult result = commandHandler.handle(scanner.nextLine());
                System.out.println(result.message());
                if (result.shouldExit()) {
                    return;
                }
            } catch (TaskParseException exception) {
                System.out.println(exception.getMessage());
            } catch (RuntimeException exception) {
                System.out.println(" Something went wrong while processing that command. Please try again.");
            }
            System.out.println(DIVIDER);
        }
    }
}
