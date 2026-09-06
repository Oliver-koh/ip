package nga;

import java.util.Scanner;

/**
 * A chatbot that stores entered tasks in memory and can display or complete them.
 * This version uses plain-English replies.
 */
public class NgaEnglish {
    private static final int MAXIMUM_TASKS = 100;
    private static final String DIVIDER = "____________________________________________________________";

    /**
     * Starts the chatbot and processes commands until the user enters {@code bye}.
     *
     * @param args command-line arguments, which are not used
     */
    public static void main(String[] args) {
        NgaEnglish nga = new NgaEnglish();
        nga.run();
    }

    /** Runs the command loop and owns the list of task objects. */
    private void run() {
        Task[] tasks = new Task[MAXIMUM_TASKS];
        int numberOfTasks = 0;

        System.out.println(greeting());
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String command = scanner.nextLine().trim();
            System.out.println(DIVIDER);

            if (command.equals("bye")) {
                System.out.println("Goodbye!");
                System.out.println(DIVIDER);
                return;
            } else if (command.equals("list")) {
                printTasks(tasks, numberOfTasks);
            } else if (command.equals("help")) {
                printHelp();
            } else if (command.startsWith("mark ")) {
                markTask(command.substring(5).trim(), tasks, numberOfTasks);
            } else if (command.startsWith("unmark ")) {
                unmarkTask(command.substring(7).trim(), tasks, numberOfTasks);
            } else if (!command.isEmpty()) {
                Task task = TaskParser.createTask(command);
                if (task == null) {
                    // TaskParser reports the expected format for malformed typed commands.
                } else if (numberOfTasks == MAXIMUM_TASKS) {
                    System.out.println(" The task list is full.");
                } else {
                    tasks[numberOfTasks] = task;
                    numberOfTasks++;
                    System.out.println(" Got it. I've added this task:");
                    System.out.println("   " + task);
                    System.out.println(" Now you have " + numberOfTasks + " tasks in the list.");
                }
            }

            if (command.equals("geeked")) {
                System.out.println("I understand.");
            } else if (command.equals("yogurt")) {
                System.out.println("Yogurt says hello.");
            }
            System.out.println(DIVIDER);
        }
    }

    /** Prints all tasks with their current completion markers. */
    private void printTasks(Task[] tasks, int numberOfTasks) {
        System.out.println(" Here are the tasks in your list:");
        for (int i = 0; i < numberOfTasks; i++) {
            System.out.println(" " + (i + 1) + "." + tasks[i]);
        }
    }

    /** Prints the available commands and their expected formats. */
    private void printHelp() {
        System.out.println(" Available commands:");
        System.out.println("   list");
        System.out.println("       Lists all tasks.");
        System.out.println("   todo <description>");
        System.out.println("       Adds a todo task.");
        System.out.println("   deadline <description> /by <date or time>");
        System.out.println("       Adds a deadline.");
        System.out.println("   event <description> /from <start> /to <end>");
        System.out.println("       Adds an event.");
        System.out.println("   mark <task number>");
        System.out.println("       Marks a task as done.");
        System.out.println("   unmark <task number>");
        System.out.println("       Marks a task as not done.");
        System.out.println("   help");
        System.out.println("       Shows this command guide.");
        System.out.println("   bye");
        System.out.println("       Exits Nga.");
    }

    /** Marks a one-based task number as done and reports the updated task. */
    private void markTask(String taskNumber, Task[] tasks, int numberOfTasks) {
        try {
            int index = Integer.parseInt(taskNumber) - 1;
            if (index < 0 || index >= numberOfTasks) {
                System.out.println(" That task number does not exist.");
                return;
            }

            Task task = tasks[index];
            task.markAsDone();
            System.out.println(" Nice! I've marked this task as done:");
            System.out.println("   " + task);
        } catch (NumberFormatException exception) {
            System.out.println(" Please provide a task number, such as: mark 2");
        }
    }

    /** Marks a one-based task number as not done and reports the updated task. */
    private void unmarkTask(String taskNumber, Task[] tasks, int numberOfTasks) {
        try {
            int index = Integer.parseInt(taskNumber) - 1;
            if (index < 0 || index >= numberOfTasks) {
                System.out.println(" That task number does not exist.");
                return;
            }

            Task task = tasks[index];
            task.markAsUndone();
            System.out.println(" OK, I've marked this task as not done yet:");
            System.out.println("   " + task);
        } catch (NumberFormatException exception) {
            System.out.println(" Please provide a task number, such as: unmark 2");
        }
    }

    /** Returns the chatbot greeting. */
    private String greeting() {
        return DIVIDER + "\n"
                + " _ __   __ _  __ _ \n"
                + "| '_ \\ / _` |/ _` |\n"
                + "| | | | (_| | (_| |\n"
                + "|_| |_|\\__, |\\__,_|\n"
                + "       |___/         \n"
                + "Hello! I'm Nga.\n"
                + DIVIDER;
    }
}
