package nga;

/** Represents one user command that can be executed by the application. */
public abstract class Command {
    /** Executes this command using the application's task list and storage. */
    public abstract CommandResult execute(TaskList taskList, Storage storage) throws TaskParseException;

    /** Returns whether executing this command should end the application. */
    public boolean isExit() {
        return false;
    }
}
