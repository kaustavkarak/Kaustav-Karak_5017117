// Exercise 7: Implementing the Observer Pattern

import java.util.ArrayList;
import java.util.List;

// Stock Interface
interface Stock {
    void registerObserver(Observer observer);
    void deregisterObserver(Observer observer);
    void notifyObservers();
}

// StockMarket class implementing Stock
class StockMarket implements Stock {
    private List<Observer> observers;
    private String stockName;
    private double price;

    public StockMarket(String stockName) {
        this.stockName = stockName;
        this.observers = new ArrayList<>();
    }

    public void setPrice(double price) {
        this.price = price;
        notifyObservers();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void deregisterObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(stockName, price);
        }
    }
}

// Observer Interface
interface Observer {
    // update() Method
    void update(String stockName, double price);
}

// MobileApp class implementing Observer
class MobileApp implements Observer {
    @Override
    public void update(String stockName, double price) {
        System.out.println("Mobile App -> Stock: " + stockName + ", New Price: Rs." + price);
    }
}

// WebApp class implementing Observer
class WebApp implements Observer {
    @Override
    public void update(String stockName, double price) {
        System.out.println("Web App -> Stock: " + stockName + ", New Price: Rs." + price);
    }
}

// Test class to demonstrate Observer Pattern
public class Exercise7_ObserverPatternExample {
    public static void main(String[] args) {

        StockMarket stockMarket = new StockMarket("ABCTech Corporation");

        Observer mobileApp = new MobileApp();
        Observer webApp = new WebApp();

        stockMarket.registerObserver(mobileApp);
        stockMarket.registerObserver(webApp);

        stockMarket.setPrice(230.00);
        stockMarket.setPrice(275.50);
    }
}



// OUTPUT:
// Mobile App -> Stock: ABCTech Corporation, New Price: Rs.230.0
// Web App -> Stock: ABCTech Corporation, New Price: Rs.230.0
// Mobile App -> Stock: ABCTech Corporation, New Price: Rs.275.5
// Web App -> Stock: ABCTech Corporation, New Price: Rs.275.5
