// Exercise 8: Implementing the STRATEGY PATTERN

// PaymentStrategy Interface
interface PaymentStrategy {
    // pay() Method
    void pay(double amount);
}

// Strategy 1: Credit Card Payment
// CreditCardPayment class implementing PaymentStrategy
class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String cardHolderName;

    public CreditCardPayment(String cardNumber, String cardHolderName) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid Rs." + amount + " using Credit Card.");
    }
}

// Strategy 2: PayPal Payment
// PayPalPayment class implementing PaymentStrategy
class PayPalPayment implements PaymentStrategy {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using PayPal.");
    }
}

// PaymentContext class
class PaymentContext {
    // Reference to PaymentStrategy 
    private PaymentStrategy paymentStrategy;

    public PaymentContext(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void executePayment(double amount) {
        paymentStrategy.pay(amount);
    }
}

// Test class to demonstrate Strategy Pattern
public class Exercise8_StrategyPatternExample {
    public static void main(String[] args) {

        PaymentStrategy creditCard = new CreditCardPayment("9834-2195-0034-8926", "Diego Wilson");
        PaymentStrategy paypal = new PayPalPayment("diwgo.wilson@email.com");

        PaymentContext context = new PaymentContext(creditCard);
        System.out.println("Testing Credit Card Payment:");
        context.executePayment(799.00);

        context.setPaymentStrategy(paypal);
        System.out.println("Testing PayPal Payment:");
        context.executePayment(429.00);
    }
}



// OUTPUT:
// Testing Credit Card Payment:
// Paid Rs.799.0 using Credit Card.
// Testing PayPal Payment:
// Paid $429.0 using PayPal.