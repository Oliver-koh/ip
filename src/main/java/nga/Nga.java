package nga;

import java.util.Scanner;

/** Runs Nga's introductory command echo interaction. */
public class Nga {
    private static final String DIVIDER = "____________________________________________________________";

    /** Starts Nga, echoes commands, and exits when the user enters {@code bye}. */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(DIVIDER);
        System.out.println("Hello! I'm Nga.");
        System.out.println("What can I do for you?");
        System.out.println(DIVIDER);

        while (scanner.hasNextLine()) {
            String command = scanner.nextLine();
            if (command.trim().equals("bye")) {
                System.out.println(DIVIDER);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(DIVIDER);
                return;
            }
            System.out.println(DIVIDER);
            System.out.println(command);
            System.out.println(DIVIDER);
        }
    }
}
