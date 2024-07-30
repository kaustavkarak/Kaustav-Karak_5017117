// Exercise 4: Implementing the ADAPTER PATTERN

public class Exercise4_AdapterPatternExample {

    // PaymentProcessor interface
    interface PaymentProcessor {
        // PaymentProcessor Method
        void processPayment(double amount);
    }

    // Adapter class for Gateway A
    static class GatewayA {
        void makePayment(double amount) {
            System.out.println("Processing payment of Rs." + amount + " through Gateway A.");
        }
    }

    // Adapter class for Gateway B
    static class GatewayB {
        void pay(double amount) {
            System.out.println("Processing payment of Rs." + amount + " through Gateway B.");
        }
    }

    // For Gateway A
    static class GatewayAAdapter implements PaymentProcessor {
        private GatewayA gatewayA;

        public GatewayAAdapter(GatewayA gatewayA) {
            this.gatewayA = gatewayA;
        }

        @Override
        public void processPayment(double amount) {
            gatewayA.makePayment(amount);
        }
    }

    // For Gateway B
    static class GatewayBAdapter implements PaymentProcessor {
        private GatewayB gatewayB;

        public GatewayBAdapter(GatewayB gatewayB) {
            this.gatewayB = gatewayB;
        }

        @Override
        public void processPayment(double amount) {
            gatewayB.pay(amount);
        }
    }

    // Test class to demonstrate Adapter Pattern
    public static void main(String[] args) {
        GatewayA gatewayA = new GatewayA();
        GatewayB gatewayB = new GatewayB();

        PaymentProcessor processorA = new GatewayAAdapter(gatewayA);
        PaymentProcessor processorB = new GatewayBAdapter(gatewayB);

        // Processing payments using different payment gateways 
        processorA.processPayment(699.0);
        processorB.processPayment(349.0);
    }
}



// OUTPUT
// Processing payment of Rs.699.0 through Gateway A.
// Processing payment of Rs.349.0 through Gateway B.