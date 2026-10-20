import java.util.*;

class Stock {
    String name;
    double price;

    Stock(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

public class StockTradingPlatform {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Stock[] stocks = {
            new Stock("TCS", 3500),
            new Stock("INFY", 1500),
            new Stock("WIPRO", 500)
        };

        int[] shares = new int[3];
        double balance = 10000;
        int choice;

        do {
            System.out.println("\n--- STOCK TRADING PLATFORM ---");
            System.out.println("Balance: Rs." + balance);
            System.out.println("1. View Stocks");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            if (choice == 1) {
                for (int i = 0; i < stocks.length; i++) {
                    System.out.println((i + 1) + ". "
                        + stocks[i].name + " - Rs."
                        + stocks[i].price);
                }
            } else if (choice == 2 || choice == 3) {
                System.out.print("Enter stock number (1-3): ");
                int n = sc.nextInt() - 1;

                if (n < 0 || n >= stocks.length) {
                    System.out.println("Invalid stock number!");
                    continue;
                }

                System.out.print("Enter quantity: ");
                int qty = sc.nextInt();

                if (qty <= 0) {
                    System.out.println("Invalid quantity!");
                } else if (choice == 2) {
                    double cost = stocks[n].price * qty;

                    if (cost <= balance) {
                        balance -= cost;
                        shares[n] += qty;
                        System.out.println("Stock purchased!");
                    } else {
                        System.out.println("Insufficient balance!");
                    }
                } else {
                    if (qty <= shares[n]) {
                        shares[n] -= qty;
                        balance += stocks[n].price * qty;
                        System.out.println("Stock sold!");
                    } else {
                        System.out.println("Not enough shares!");
                    }
                }
            } else if (choice == 4) {
                double total = 0;

                for (int i = 0; i < stocks.length; i++) {
                    double value = shares[i] * stocks[i].price;
                    total += value;
                    System.out.println(stocks[i].name
                        + ": " + shares[i] + " shares, Value: Rs."
                        + value);
                }

                System.out.println("Cash Balance: Rs." + balance);
                System.out.println("Portfolio Value: Rs." + total);
                System.out.println("Total Assets: Rs." + (balance + total));
            } else if (choice != 5) {
                System.out.println("Invalid choice!");
            }

        } while (choice != 5);

        System.out.println("Thank you for trading!");
        sc.close();
    }
}
