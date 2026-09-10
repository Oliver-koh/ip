package nga;

import java.util.Scanner;

import nga.task.Task;

/** Runs Nga's introductory command echo interaction. */
public class Nga {
    private static final String DIVIDER = "____________________________________________________________";
    private static final int MAXIMUM_TASKS = 100;

    /** Starts Nga, manages task status, and exits when the user enters {@code bye}. */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Task[] tasks = new Task[MAXIMUM_TASKS];
        int taskCount = 0;

        System.out.println(DIVIDER);
        System.out.println("Hello! I'm Nga.");
        System.out.println("What can I do for you?");
        System.out.println(DIVIDER);

        while (scanner.hasNextLine()) {
            String command = scanner.nextLine().trim();
            if (command.equals("bye")) {
                System.out.println(DIVIDER);
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println(DIVIDER);
                return;
            }

            System.out.println(DIVIDER);
            if (command.equals("list")) {
                printTasks(tasks, taskCount);
            } else if (isStatusCommand(command, "mark")) {
                updateTaskStatus(command, "mark", tasks, taskCount, true);
            } else if (isStatusCommand(command, "unmark")) {
                updateTaskStatus(command, "unmark", tasks, taskCount, false);
            } else if (taskCount < MAXIMUM_TASKS) {
                tasks[taskCount] = new Task(command);
                taskCount++;
                System.out.println("added: " + command);
            } else {
                System.out.println("The task list is full.");
            }
            System.out.println(DIVIDER);
        }
    }

    /** Prints all tasks with their one-based numbers and completion icons. */
    private static void printTasks(Task[] tasks, int taskCount) {
        System.out.println("Here are the tasks in your list:");
        for (int taskNumber = 0; taskNumber < taskCount; taskNumber++) {
            Task task = tasks[taskNumber];
            System.out.println((taskNumber + 1) + ".[" + task.getStatusIcon() + "] " + task.getDescription());
        }
    }

    /** Returns whether a command is a mark or unmark command, including its task number. */
    private static boolean isStatusCommand(String command, String keyword) {
        return command.equals(keyword) || command.startsWith(keyword + " ");
    }

    /** Updates a task's completion status and prints the corresponding response. */
    private static void updateTaskStatus(String command, String keyword, Task[] tasks,
                                         int taskCount, boolean isDone) {
        String taskNumberText = command.substring(keyword.length()).trim();
        try {
            int taskNumber = Integer.parseInt(taskNumberText);
            if (taskNumber < 1 || taskNumber > taskCount) {
                System.out.println("Please provide a valid task number.");
                return;
            }

            Task task = tasks[taskNumber - 1];
            if (isDone) {
                task.markAsDone();
                System.out.println("Nice! I've marked this task as done:");
            } else {
                task.markAsUndone();
                System.out.println("OK, I've marked this task as not done yet:");
            }
            System.out.println("  [" + task.getStatusIcon() + "] " + task.getDescription());
        } catch (NumberFormatException exception) {
            System.out.println("Please provide a valid task number.");
        }
    }
}
