package nga;

/** Starts the Nga command-line application. */
public class Nga {
    /** Starts Nga and delegates interaction to the UI layer. */
    public static void main(String[] args) {
        new NgaUi(new NgaCommandHandler()).run();
    }
}
