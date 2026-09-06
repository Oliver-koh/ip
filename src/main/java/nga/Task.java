package nga;

/**
 * Represents a task in Nga's task list.
 *
 * <p>This class stores the behavior shared by all task types. Subclasses can
 * provide their own task type label and additional details while reusing the
 * completion state and description handling.</p>
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

    /**
     * Returns the one-letter label used for this task type.
     *
     * <p>The base task is treated as a ToDo for now. Specific task subclasses
     * can override this method when they are added.</p>
     *
     * @return the task type label
     */
    public String getTaskType() {
        return "T";
    }

    /** Returns this task in the format used by the chatbot. */
    @Override
    public String toString() {
        return "[" + getTaskType() + "][" + getStatusIcon() + "] " + description;
    }
}
