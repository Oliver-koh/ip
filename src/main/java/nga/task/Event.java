package nga.task;

/**
 * Represents a task with a start date/time and an end date/time.
 */
public class Event extends Task {
    private final String from;
    private final String to;

    /** Creates an unfinished event with its description and date/time range. */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /** Returns the event start date or time. */
    public String getFrom() {
        return from;
    }

    /** Returns the event end date or time. */
    public String getTo() {
        return to;
    }

    /** Returns the type label for an event. */
    @Override
    public String getTaskType() {
        return "E";
    }

    /** Returns this event in the format used by the chatbot. */
    @Override
    public String toString() {
        return super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
