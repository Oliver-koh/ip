package nga.task;

/** Stores tasks and translates user-facing task numbers into array indexes. */
public class TaskList {
    private static final int MAXIMUM_TASKS = 100;
    private final Task[] tasks = new Task[MAXIMUM_TASKS];
    private int taskCount;

    /** Returns the number of stored tasks. */
    public int size() {
        return taskCount;
    }

    /** Returns whether the list cannot accept another task. */
    public boolean isFull() {
        return taskCount == MAXIMUM_TASKS;
    }

    /** Adds a task to the end of the list. */
    public void add(Task task) {
        if (isFull()) {
            throw new IllegalStateException("The task list is full.");
        }
        tasks[taskCount] = task;
        taskCount++;
    }

    /** Returns whether the one-based number identifies a stored task. */
    public boolean hasTaskNumber(int taskNumber) {
        return taskNumber >= 1 && taskNumber <= taskCount;
    }

    /** Returns the task identified by a valid one-based task number. */
    public Task getTask(int taskNumber) {
        if (!hasTaskNumber(taskNumber)) {
            throw new IllegalArgumentException("Invalid task number.");
        }
        return tasks[taskNumber - 1];
    }
}
