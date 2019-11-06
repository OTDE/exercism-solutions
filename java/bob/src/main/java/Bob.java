import java.util.regex.Pattern;

public class Bob {

    private static final Pattern LETTERS = Pattern.compile(".*[a-zA-Z]+.*");

    private enum Response {
        QUESTION("Sure."),
        YELLING("Whoa, chill out!"),
        YELLING_QUESTION("Calm down, I know what I'm doing!"),
        SILENT("Fine. Be that way!"),
        DEFAULT("Whatever.");

        private final String dialog;

        Response(String dialog) {
            this.dialog = dialog;
        }

        @Override
        public String toString() {
            return dialog;
        }
    }

    public String hey(String dialog) {
        return Bob.respondTo(dialog.trim()).toString();
    }

    private static Response respondTo(String dialog) {
        if (dialog.isEmpty()) {
            return Response.SILENT;
        }

        boolean yelling = isYelling(dialog);
        boolean questioning = isQuestion(dialog);

        if (yelling && questioning) {
            return Response.YELLING_QUESTION;
        }

        if(yelling) {
            return Response.YELLING;
        }

        if(questioning) {
            return Response.QUESTION;
        }

        return Response.DEFAULT;
    }

    private static boolean isYelling(String dialog) {
        return containsLetters(dialog) && dialog.toUpperCase().equals(dialog);
    }

    private static boolean containsLetters(String dialog) {
        return LETTERS.matcher(dialog).matches();
    }

    private static boolean isQuestion(String dialog) {
        return dialog.endsWith("?");
    }

}