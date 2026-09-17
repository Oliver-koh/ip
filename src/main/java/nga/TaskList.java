package nga;

import java.util.ArrayList;
import java.util.List;

/** Stores tasks and translates user-facing one-based task numbers into list indexes. */
public class TaskList {
    private static final int MAXIMUM_TASKS = 100;
    private final List<Task> tasks = new ArrayList<>();

    /** Returns the number of stored tasks. */
    public int size() {
        return tasks.size();
    }

    /** Returns whether the list cannot accept another task. */
    public boolean isFull() {
        return tasks.size() == MAXIMUM_TASKS;
    }

    /** Adds a task to the end of the list. */
    public void add(Task task) {
        if (isFull()) {
            throw new IllegalStateException("The task list is full.");
        }
        tasks.add(task);
    }

    /** Returns whether the one-based number identifies a stored task. */
    public boolean hasTaskNumber(int taskNumber) {
        return taskNumber >= 1 && taskNumber <= tasks.size();
    }

    /** Returns the task identified by a valid one-based task number. */
    public Task getTask(int taskNumber) {
        if (!hasTaskNumber(taskNumber)) {
            throw new IllegalArgumentException("Invalid task number.");
        }
        return tasks.get(taskNumber - 1);
    }

    /** Removes and returns the task identified by a valid one-based task number. */
    public Task removeTask(int taskNumber) {
        if (!hasTaskNumber(taskNumber)) {
            throw new IllegalArgumentException("Invalid task number.");
        }
        return tasks.remove(taskNumber - 1);
    }

    /** Removes all stored tasks from the list. */
    public void clear() {
        tasks.clear();
    }
}
