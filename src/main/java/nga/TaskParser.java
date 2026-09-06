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
     * @param keyword the command word
     * @param arguments the text after the command word
     * @return the corresponding task
     * @throws TaskParseException if a required field is missing
     */
    public static Task createTask(String keyword, String arguments) throws TaskParseException {
        return switch (keyword) {
        case "todo" -> createTodo(arguments);
        case "deadline" -> createDeadline(arguments);
        case "event" -> createEvent(arguments);
        default -> throw new TaskParseException(" Unknown task command.");
        };
    }

    /** Creates a todo and rejects an empty description. */
    public static Task createTodo(String description) throws TaskParseException {
        if (description.isBlank()) {
            throw new TaskParseException(" Todo description cannot be empty. Please enter something.");
        }
        return new Todo(description.trim());
    }

    private static Task createDeadline(String arguments) throws TaskParseException {
        int byIndex = arguments.indexOf(" /by ");
        if (byIndex <= 0 || byIndex + 5 >= arguments.length()) {
            throw new TaskParseException(" Use: deadline task description /by date or time");
        }
        String description = arguments.substring(0, byIndex).trim();
        String by = arguments.substring(byIndex + 5).trim();
        if (description.isEmpty() || by.isEmpty()) {
            throw new TaskParseException(" Use: deadline task description /by date or time");
        }
        return new Deadline(description, by);
    }

    private static Task createEvent(String arguments) throws TaskParseException {
        int fromIndex = arguments.indexOf(" /from ");
        int toIndex = arguments.indexOf(" /to ");
        if (fromIndex <= 0 || toIndex <= fromIndex + 7 || toIndex + 4 >= arguments.length()) {
            throw new TaskParseException(" Use: event task description /from start /to end");
        }
        String description = arguments.substring(0, fromIndex).trim();
        String from = arguments.substring(fromIndex + 7, toIndex).trim();
        String to = arguments.substring(toIndex + 4).trim();
        if (description.isEmpty() || from.isEmpty() || to.isEmpty()) {
            throw new TaskParseException(" Use: event task description /from start /to end");
        }
        return new Event(description, from, to);
    }
}
