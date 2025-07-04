package fawryecommerce;

import java.util.List;
import java.util.ArrayList;

public class Customer
{

    private double balance;
    private List<CartItem> cart = new ArrayList<>();
    private static final double SHIPPING_FEES = 30.0;


    public Customer(double balance)
    {
        this.balance = balance;
    }
    
    public void addToCart(Product product, int quantity) {
        for(CartItem prodCartItem: cart) {
            if(prodCartItem.getProduct().equals(product)) {
                int updatedQuantity = prodCartItem.getQuantity() + quantity;
                if(updatedQuantity > product.getQuantity()) {
                    System.out.println("Product quantity can't be more than " + product.getProduct());
                    return;
                }
                // update same product quantity here
                prodCartItem.setQuantity(updatedQuantity);
            }
        }
        CartItem item = new CartItem(product, quantity);
        cart.add(item);
    }

    public void viewCart() {
        System.out.println("\nYour Cart:");
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty");
            return;
        }

        double total = 0;
        for (int i = 0; i < cart.size(); i++) {
            CartItem item = cart.get(i);
            Product product = item.getProduct();
            System.out.printf("%d. %s x %d = %.2f EGP%n", 
                i + 1, 
                product.getName(), 
                item.getQuantity(), 
                product.getPrice() * item.getQuantity());
            total += product.getPrice() * item.getQuantity();
        }
        System.out.printf("Total: %.2f EGP%n", total);
        System.out.printf("Your balance: %.2f EGP%n", balance);
    }

    public void checkout() {
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty!");
            return;
        }

        double totalAmount = cart.stream()
            .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
            .sum();

        if (totalAmount > balance) {
            System.out.printf("Insufficient balance. Your balance is %.2f EGP and total amount is %.2f EGP%n", 
                balance, totalAmount);
            return;
        }
        
        balance -= totalAmount;
        cart.clear();
        System.out.println("Order created successfully! Your new balance is " + balance + " EGP");
    }

}
