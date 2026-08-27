package nga;

/**
 * Represents one task in Nga's task list.
 */
public class Task {
    private final String description;
    private boolean isDone;

    /** Creates an unfinished task with the supplied description. */
    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    /** Marks this task as done. */
    public void markAsDone() {
        isDone = true;
    }

    /** Marks this task as not done. */
    public void markAsUndone() {
        isDone = false;
    }

    /** Returns {@code X} for a completed task or a space otherwise. */
    public String getStatusIcon() {
        return isDone ? "X" : " ";
    }

    /** Returns the task description. */
    public String getDescription() {
        return description;
    }

    /** Returns this task in the format used by the chatbot. */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
