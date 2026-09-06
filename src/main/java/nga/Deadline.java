package nga;

/**
 * Represents a task that must be completed by a specified date or time.
 */
public class Deadline extends Task {
    private final String by;

    /** Creates an unfinished deadline with its description and due date/time. */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /** Returns the deadline date or time. */
    public String getBy() {
        return by;
    }

    /** Returns the type label for a deadline. */
    @Override
    public String getTaskType() {
        return "D";
    }

    /** Returns this deadline in the format used by the chatbot. */
    @Override
    public String toString() {
        return super.toString() + " (by: " + by + ")";
    }
}
