// Exercise 7: FINANCIAL FORECASTING

import java.util.*;

public class Exercise7_FinancialForecasting {

    // Method to calculate the future value using a Recursive approach
    public static double calculateFutureValue(double currentValue, double growthRate, int periods) {
        if (periods == 0) {
            return currentValue;
        }
        // Recursive algorithm to predict future values based on past growth rates
        return calculateFutureValue(currentValue * (1 + growthRate / 100), growthRate, periods - 1);
    }

    public static void main(String[] args) {
        double currentValue = 50000.0; 
        double growthRate = 5.50;     
        int periods = 10;             

        double futureValueRecursive = calculateFutureValue(currentValue, growthRate, periods);
        System.out.printf("The future value after %d periods (recursive) is: %.2f%n", periods, futureValueRecursive);
    }
}



// OUTPUT:
// The future value after 10 periods (recursive) is: 85407.22