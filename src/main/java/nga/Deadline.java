package nga;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * Represents a task that must be completed by a specified date or time.
 */
public class Deadline extends Task {
    private static final DateTimeFormatter DATE_ONLY_FORMATTER =
            DateTimeFormatter.ofPattern("MMM d yyyy", Locale.ENGLISH);
    private static final DateTimeFormatter DATE_TIME_FORMATTER =
            DateTimeFormatter.ofPattern("MMM d yyyy h:mm a", Locale.ENGLISH);
    private static final DateTimeFormatter[] INPUT_FORMATTERS = {
        DateTimeFormatter.ofPattern("d/M/uuuu HHmm"),
        DateTimeFormatter.ofPattern("uuuu-MM-dd HHmm"),
        DateTimeFormatter.ofPattern("uuuu-MM-dd HH:mm")
    };
    private final LocalDateTime by;
    private final boolean hasExplicitTime;

    /** Creates an unfinished deadline with its description and due date/time. */
    public Deadline(String description, LocalDateTime by, boolean hasExplicitTime) {
        super(description);
        this.by = by;
        this.hasExplicitTime = hasExplicitTime;
    }

    /** Creates a deadline from a user-entered date or date/time. */
    public static Deadline fromInput(String description, String input) throws TaskParseException {
        String value = input.trim();
        try {
            for (DateTimeFormatter formatter : INPUT_FORMATTERS) {
                try {
                    return new Deadline(description, LocalDateTime.parse(value, formatter), true);
                } catch (DateTimeParseException exception) {
                    // Try the next supported date/time format.
                }
            }
            return new Deadline(description, LocalDate.parse(value).atStartOfDay(), false);
        } catch (DateTimeParseException exception) {
            throw new TaskParseException(" Use: deadline task description /by yyyy-MM-dd [HHmm]");
        }
    }

    /** Returns the typed deadline date and time. */
    public LocalDateTime getBy() {
        return by;
    }

    /** Returns whether the user supplied an explicit time. */
    public boolean hasExplicitTime() {
        return hasExplicitTime;
    }

    /** Returns the deadline details used to identify duplicate tasks. */
    @Override
    protected String getDuplicateKey() {
        return super.getDuplicateKey() + "|" + by;
    }

    /** Returns the type label for a deadline. */
    @Override
    public String getTaskType() {
        return "D";
    }

    /** Returns this deadline in the format used by the chatbot. */
    @Override
    public String toString() {
        String formattedBy = hasExplicitTime
                ? by.format(DATE_TIME_FORMATTER)
                : by.format(DATE_ONLY_FORMATTER);
        return super.toString() + " (by: " + formattedBy + ")";
    }
}
