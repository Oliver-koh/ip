package nga;

/** Executes commands against the task list without reading console input. */
public class NgaCommandHandler {
    private static final String HELP = " Available commands:\n"
            + "   list\n       Lists all tasks.\n"
            + "   todo <description>\n       Adds a todo task.\n"
            + "   deadline <description> /by <date or time>\n       Adds a deadline.\n"
            + "   event <description> /from <start> /to <end>\n       Adds an event.\n"
            + "   mark <task number>\n       Marks a task as done.\n"
            + "   unmark <task number>\n       Marks a task as not done.\n"
            + "   delete <task number>\n       Deletes a task.\n"
            + "   help\n       Shows this command guide.\n"
            + "   bye\n       Exits Nga.";
    private final TaskList taskList;
    private final Storage storage;

    /** Creates a handler with an empty task list. */
    public NgaCommandHandler() {
        taskList = new TaskList();
        storage = new Storage();
        for (Task task : storage.load()) {
            if (!taskList.isFull()) {
                taskList.add(task);
            }
        }
    }

    /** Executes one command and returns text for the UI to display. */
    public CommandResult handle(String input) throws TaskParseException {
        ParsedCommand command = CommandParser.parse(input);
        return switch (command.keyword()) {
        case "" -> new CommandResult(false, " Please enter something.");
        case "bye" -> new CommandResult(true, "Peace out");
        case "list" -> new CommandResult(false, formatTasks());
        case "help" -> new CommandResult(false, HELP);
        case "mark" -> updateTask(command.arguments(), true);
        case "unmark" -> updateTask(command.arguments(), false);
        case "delete" -> deleteTask(command.arguments());
        case "todo", "deadline", "event" -> addTask(command);
        default -> addUnprefixedTodo(command.original());
        };
    }

    private CommandResult addTask(ParsedCommand command) throws TaskParseException {
        Task task = TaskParser.createTask(command.keyword(), command.arguments());
        if (taskList.isFull()) {
            return new CommandResult(false, " The task list is full.");
        }
        taskList.add(task);
        return savedResult(addedTaskMessage(task));
    }

    private CommandResult addUnprefixedTodo(String description) throws TaskParseException {
        Task task = TaskParser.createTodo(description);
        if (taskList.isFull()) {
            return new CommandResult(false, " The task list is full.");
        }
        taskList.add(task);
        return savedResult(addedTaskMessage(task));
    }

    private CommandResult addedTaskMessage(Task task) {
        return new CommandResult(false, " Got it. I've added this task:\n   " + task
                + "\n Now you have " + taskList.size() + " tasks in the list.");
    }

    private CommandResult updateTask(String argument, boolean isDone) {
        try {
            int taskNumber = Integer.parseInt(argument);
            if (!taskList.hasTaskNumber(taskNumber)) {
                return new CommandResult(false, " That task number does not exist.");
            }
            Task task = taskList.getTask(taskNumber);
            if (isDone) {
                task.markAsDone();
            } else {
                task.markAsUndone();
            }
            String status = isDone ? " marked as done:" : " marked as not done yet:";
            return savedResult(new CommandResult(false, " OK, I've" + status + "\n   " + task));
        } catch (NumberFormatException exception) {
            return new CommandResult(false, " Please provide a task number.");
        }
    }

    private CommandResult deleteTask(String argument) {
        try {
            int taskNumber = Integer.parseInt(argument);
            if (!taskList.hasTaskNumber(taskNumber)) {
                return new CommandResult(false, " That task number does not exist.");
            }
            Task deletedTask = taskList.removeTask(taskNumber);
            return savedResult(new CommandResult(false, " Noted. I've removed this task:\n   " + deletedTask
                    + "\n Now you have " + taskList.size() + " tasks in the list."));
        } catch (NumberFormatException exception) {
            return new CommandResult(false, " Please provide a task number.");
        }
    }

    private String formatTasks() {
        StringBuilder output = new StringBuilder(" Here are the tasks in your list:");
        for (int taskNumber = 1; taskNumber <= taskList.size(); taskNumber++) {
            output.append("\n ").append(taskNumber).append(".").append(taskList.getTask(taskNumber));
        }
        return output.toString();
    }

    private CommandResult savedResult(CommandResult result) {
        if (storage.save(taskList)) {
            return result;
        }
        return new CommandResult(false, result.message()
                + "\n Warning: I could not save the task list to disk.");
    }
}
