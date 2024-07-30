// Exercise 1: // Implementing the SINGLETON PATTERN

public class Exercise1_SingletonPatternExample {

    // Logger class 
    public static class Logger {
        // Private static instance of the Logger class
        private static Logger instance;

        // Private constructor to prevent instantiation
        private Logger() {}

        // Public static method to provide the single instance of the Logger class
        public static Logger getInstance() {
            if (instance == null) {
                instance = new Logger();
            }
            return instance;
        }

        // Method to log messages
        public void log(String message) {
            System.out.println("Log message: " + message);
        }
    }

    // Test class to implement Singleton Pattern
    public static void main(String[] args) {
        // Getting the single instance of Logger
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        // Log messages using logger1 and logger2
        logger1.log("Logger 1 message.");
        logger2.log("Logger 2 message.");

        // Verifying if both instances are the same
        if (logger1 == logger2) {
            System.out.println("Both logger1 and logger2 are the same instance.");
        } else {
            System.out.println("logger1 and logger2 are different instances.");
        }
    }
}


// OUTPUT:
// Log message: Logger 1 message.
// Log message: Logger 2 message.
// Both logger1 and logger2 are the same instance.