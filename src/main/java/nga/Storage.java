package nga;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/** Writes and loads Nga's task list in the project data directory. */
public class Storage {
    private static final Path FILE_PATH = Path.of("data/nga.txt");

    /** Saves the current tasks, replacing the previous contents of the file. */
    public boolean save(TaskList taskList) {
        if (taskList == null) {
            return false;
        }
        List<String> lines = new ArrayList<>();
        for (int taskNumber = 1; taskNumber <= taskList.size(); taskNumber++) {
            Task task = taskList.getTask(taskNumber);
            if (task != null) {
                lines.add(formatTask(task));
            }
        }
        try {
            Files.createDirectories(FILE_PATH.getParent());
            Files.write(FILE_PATH, lines, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
            return true;
        } catch (IOException | SecurityException exception) {
            return false;
        }
    }

    /** Loads saved tasks, returning an empty list when no data file exists. */
    public List<Task> load() {
        try {
            if (!Files.exists(FILE_PATH)) {
                return new ArrayList<>();
            }
            List<Task> tasks = new ArrayList<>();
            for (String line : Files.readAllLines(FILE_PATH)) {
                Task task = parseTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
            return tasks;
        } catch (IOException | SecurityException exception) {
            return new ArrayList<>();
        }
    }

    private String formatTask(Task task) {
        String status = task.isDone() ? "1" : "0";
        if (task instanceof Deadline deadline) {
            String storedDate = deadline.hasExplicitTime()
                    ? deadline.getBy().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)
                    : deadline.getBy().toLocalDate().toString();
            return String.join(" | ", "D", status, deadline.getDescription(), storedDate);
        }
        if (task instanceof Event event) {
            return String.join(" | ", "E", status, event.getDescription(), event.getFrom(), event.getTo());
        }
        return String.join(" | ", "T", status, task.getDescription());
    }

    private Task parseTask(String line) {
        if (line == null || line.isBlank()) {
            return null;
        }
        String[] fields = line.split("\\s*\\|\\s*", -1);
        for (int index = 0; index < fields.length; index++) {
            fields[index] = fields[index].trim();
        }
        if (fields.length < 3 || !isValidStatus(fields[1])) {
            return null;
        }
        Task task = switch (fields[0]) {
        case "T" -> fields.length == 3 && hasContent(fields[2]) ? new Todo(fields[2]) : null;
        case "D" -> parseDeadline(fields);
        case "E" -> fields.length == 5 && hasContent(fields[2]) && hasContent(fields[3]) && hasContent(fields[4])
                ? new Event(fields[2], fields[3], fields[4]) : null;
        default -> null;
        };
        if (task != null && fields[1].equals("1")) {
            task.markAsDone();
        }
        return task;
    }

    private Task parseDeadline(String[] fields) {
        if (fields.length != 4 || !hasContent(fields[2]) || !hasContent(fields[3])) {
            return null;
        }
        try {
            if (fields[3].contains("T")) {
                return new Deadline(fields[2], LocalDateTime.parse(fields[3]), true);
            }
            return new Deadline(fields[2], LocalDate.parse(fields[3]).atStartOfDay(), false);
        } catch (DateTimeParseException exception) {
            return null;
        }
    }

    private boolean isValidStatus(String status) {
        return "0".equals(status) || "1".equals(status);
    }

    private boolean hasContent(String value) {
        return value != null && !value.isBlank();
    }
}
