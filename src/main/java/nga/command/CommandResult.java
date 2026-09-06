package nga.command;

/** Represents the result that the command handler asks the UI to display. */
public record CommandResult(boolean shouldExit, String message) {
}
