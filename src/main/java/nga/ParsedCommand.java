package nga;

/** Holds the command word and the text following it. */
public record ParsedCommand(String keyword, String arguments, String original) {
}
