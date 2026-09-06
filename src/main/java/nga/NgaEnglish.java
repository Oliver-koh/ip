package nga;

/** Starts Nga using the same separated UI and command-handling layers. */
public class NgaEnglish {
    /** Starts the English-language entry point. */
    public static void main(String[] args) {
        new NgaUi(new NgaCommandHandler()).run();
    }
}
