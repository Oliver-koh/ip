package nga;

/** Represents the command that ends the application. */
public class ExitCommand extends Command {
    /** Returns the exit response without changing the task list or storage. */
    @Override
    public CommandResult execute(TaskList taskList, Storage storage) {
        return new CommandResult(true, "Peace out");
    }

    /** Returns whether this command should end the application. */
    @Override
    public boolean isExit() {
        return true;
    }
}
