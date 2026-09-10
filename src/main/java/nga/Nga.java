package nga;

import java.util.Scanner;

/** Runs Nga's introductory command echo interaction. */
public class Nga {
    private static final String DIVIDER = "____________________________________________________________";
    private static final int MAXIMUM_TASKS = 100;

    /** Starts Nga, stores tasks, lists them, and exits when the user enters {@code bye}. */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] tasks = new String[MAXIMUM_TASKS];
        int taskCount = 0;

        System.out.println(DIVIDER);
        System.out.println("Hello! I'm Nga.");
        System.out.println("What can I do for you?");
        System.out.println(DIVIDER);

        while (scanner.hasNextLine()) {
            String command = scanner.nextLine().trim();
            if (command.trim().equals("bye")) {
                System.out.println(DIVIDER);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(DIVIDER);
                return;
            }

            System.out.println(DIVIDER);
            if (command.equals("list")) {
                for (int taskNumber = 0; taskNumber < taskCount; taskNumber++) {
                    System.out.println((taskNumber + 1) + ". " + tasks[taskNumber]);
                }
            } else if (taskCount < MAXIMUM_TASKS) {
                tasks[taskCount] = command;
                taskCount++;
                System.out.println("added: " + command);
            } else {
                System.out.println("The task list is full.");
            }
            System.out.println(DIVIDER);
        }
    }
}
