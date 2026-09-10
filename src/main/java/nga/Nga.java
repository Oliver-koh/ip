package nga;

/** Runs Nga's introductory greet-and-exit interaction. */
public class Nga {
    private static final String DIVIDER = "____________________________________________________________";

    /** Starts Nga, displays a greeting, and exits immediately. */
    public static void main(String[] args) {
        System.out.println(DIVIDER);
        System.out.println("Hello! I'm Nga.");
        System.out.println("What can I do for you?");
        System.out.println(DIVIDER);
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(DIVIDER);
    }
}
