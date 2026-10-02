# NGA User Guide

NGA stands for **No Goobers Allowed**. NGA is a command-line task manager for
keeping track of todos, deadlines, and events.

// Product screenshot goes here

// Product intro goes here

## Finding tasks

Search for a keyword in task descriptions with the `find` command. The search
is case-insensitive and matches the keyword anywhere in the description.

For example:

`find book`

NGA displays the matching tasks in their original order:

```
Here are the matching tasks in your list:
1.[T][X] read book
2.[D][X] return book (by: Jun 6 2019)
```

The `find` command does not change your task list. If no task description
contains the keyword, NGA reports that no matching tasks were found.

## Adding deadlines

Enter a date using `yyyy-MM-dd`, or enter a date and time using `d/M/yyyy HHmm`.
For example:

`deadline return book /by 2/12/2019 1800`

NGA stores the deadline as a typed date and time, then displays it as:

```
[D][ ] return book (by: Dec 2 2019 6:00 PM)
```

```
expected output
```

## Feature ABC

// Feature details


## Feature XYZ

// Feature details
