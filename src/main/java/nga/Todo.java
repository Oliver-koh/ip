package nga;

/**
 * Represents a task without a date or time attached to it.
 */
public class Todo extends Task {
    /** Creates an unfinished ToDo with the supplied description. */
    public Todo(String description) {
        super(description);
    }

    /** Returns the type label for a ToDo. */
    @Override
    public String getTaskType() {
        return "T";
    }
}
