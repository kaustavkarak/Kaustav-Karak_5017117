// Exercise 5: Implementing the DECORATOR PATTERN

public class Exercise5_DecoratorPatternExample {

    // Notifier interface
    interface Notifier {
        // send() Method
        void send(String email);
    }

    // EmailNotifier class implementing Notifier
        static class EmailNotifier implements Notifier {
        @Override
        public void send(String email) {
            System.out.println("Sending Email: " + email);
        }
    }

    // Abstract class NotifierDecorator implementing Notifier
    static abstract class NotifierDecorator implements Notifier {
        // Notifier Object
        protected Notifier notifier;

        public NotifierDecorator(Notifier notifier) {
            this.notifier = notifier;
        }

        @Override
        public void send(String email) {
            notifier.send(email);
        }
    }

    // Concrete class SMSNotifierDecorator extending NotifierDecorator
    static class SMSNotifierDecorator extends NotifierDecorator {
        public SMSNotifierDecorator(Notifier notifier) {
            super(notifier);
        }

        @Override
        public void send(String email) {
            super.send(email); 
            sendSMS(email);    
        }

        private void sendSMS(String email) {
            System.out.println("Sending SMS: " + email);
        }
    }

    // Concrete class SlackNotifierDecorator extending NotifierDecorator
    static class SlackNotifierDecorator extends NotifierDecorator {
        public SlackNotifierDecorator(Notifier notifier) {
            super(notifier);
        }

        @Override
        public void send(String email) {
            super.send(email); 
            sendSlack(email); 
        }

        private void sendSlack(String email) {
            System.out.println("Sending Slack message: " + email);
        }
    }

    // Test class to demonstrate Decorator Pattern
    public static void main(String[] args) {
        Notifier emailNotifier = new EmailNotifier();

        Notifier smsEmailNotifier = new SMSNotifierDecorator(emailNotifier);
        
        Notifier slackSmsEmailNotifier = new SlackNotifierDecorator(smsEmailNotifier);
        
        slackSmsEmailNotifier.send("Test notification.");
    }
}


// OUTPUT:
// Sending Email: Test notification.
// Sending SMS: Test notification.
// Sending Slack message: Test notification.
