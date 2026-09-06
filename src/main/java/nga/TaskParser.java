package nga;

/**
 * Creates task objects from the commands entered by the user.
 */
public final class TaskParser {
    private TaskParser() {
        // Utility class; do not instantiate.
    }

    /**
     * Creates a task from a typed command.
     *
     * @param command the complete command entered by the user
     * @return the corresponding task, or {@code null} for an invalid typed command
     */
    public static Task createTask(String command) {
        if (command.startsWith("todo ")) {
            return new Todo(command.substring(5).trim());
        }

        if (command.startsWith("deadline ")) {
            int byIndex = command.indexOf(" /by ");
            if (byIndex > 9 && byIndex + 5 < command.length()) {
                return new Deadline(command.substring(9, byIndex).trim(), command.substring(byIndex + 5).trim());
            }
            System.out.println(" Use: deadline task description /by date or time");
            return null;
        }

        if (command.startsWith("event ")) {
            int fromIndex = command.indexOf(" /from ");
            int toIndex = command.indexOf(" /to ");
            if (fromIndex > 6 && toIndex > fromIndex + 7 && toIndex + 4 < command.length()) {
                String description = command.substring(6, fromIndex).trim();
                String from = command.substring(fromIndex + 7, toIndex).trim();
                String to = command.substring(toIndex + 4).trim();
                return new Event(description, from, to);
            }
            System.out.println(" Use: event task description /from start /to end");
            return null;
        }

        return new Todo(command);
    }
}
