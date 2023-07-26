package chatGPT;
public class ColorPrint {
    public static void print(String message, String color) {
        // Reset color to default
        String reset = "\033[0m";

        // Define color codes
        String purple = "\033[35m";
        String red = "\033[31m";
        String green = "\033[32m";
        String yellow = "\033[33m";
        String blue = "\033[34m";

        // Set color based on input
        String code = reset;
        switch(color) {
            case "purple":
                code = purple;
                break;
            case "red":
                code = red;
                break;
            case "green":
                code = green;
                break;
            case "yellow":
                code = yellow;
                break;
            case "blue":
                code = blue;
                break;
            default:
                code = reset;
                break;
        }

        // Print message with color
        System.out.println(code + message + reset);
    }
}

