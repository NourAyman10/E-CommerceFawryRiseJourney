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

    public void addToCart(Product product, int quantity)
    {
        for (CartItem prodCartItem : cart)
            if (prodCartItem.getProduct().equals(product))
            {
                int updatedQuantity = prodCartItem.getQuantity() + quantity;
                if (updatedQuantity > product.getQuantity())
                {
                    System.out.println("Product quantity can't be more than " + product.getProduct());
                    return;
                }
                // update same product quantity here
                prodCartItem.setQuantity(updatedQuantity);
                return;
            }
        cart.add(new CartItem(product, quantity));
    }

    public void viewCart()
    {
        System.out.println("\nYour Cart:");
        if (cart.isEmpty())
        {
            System.out.println("Your cart is empty");
            return;
        }

        double subtotal = 0;
        for (CartItem item : cart)
        {
            Product product = item.getProduct();
            double itemTotal = product.getPrice() * item.getQuantity();
            System.out.printf("%dx %-20s %6.2f EGP%n",
                    item.getQuantity(), product.getName(), itemTotal);
            subtotal += itemTotal;
        }

        System.out.println("--------------------------------");
        System.out.printf("Subtotal: %6.2f EGP%n", subtotal);
        System.out.printf("Your balance: %6.2f EGP%n", balance);
    }

    public void checkout()
    {
        if (cart.isEmpty())
        {
            System.out.println("Cannot checkout, Your cart is empty!");
            return;
        }

        // Check for expired or out-of-stock items
        for (CartItem item : cart)
        {
            Product product = item.getProduct();
            if (product.isExpired())
            {
                System.out.println("Cannot checkout - " + product.getName() + " is expired!");
                return;
            }
            if (item.getQuantity() > product.getQuantity())
            {
                System.out.println("Cannot checkout - not enough stock for " + product.getName());
                return;
            }
        }

        // Calculate subtotal
        double subtotal = cart.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();

        // Calculate shipping fees
        List<ShippingItems> shippableItems = new ArrayList<>();
        double shippingWeight = 0;

        for (CartItem item : cart)
        {
            Product product = item.getProduct();
            if (product.isShippable())
                for (int i = 0; i < item.getQuantity(); i++)
                {
                    shippableItems.add(new ShippingItems()
                    {
                        @Override
                        public String getName()
                        {
                            return product.getName();
                        }

                        @Override
                        public double getWeight()
                        {
                            return product.getWeight();
                        }
                    });
                    shippingWeight += product.getWeight();
                }
        }

        double shippingFee = shippingWeight * SHIPPING_FEES;
        double totalAmount = subtotal + shippingFee;

        // Check balance
        if (totalAmount > balance)
        {
            System.out.printf("Insufficient balance! You need %.2f EGP but only have %.2f EGP%n",
                    totalAmount, balance);
            return;
        }

        // Process payment
        balance -= totalAmount;

        // Update product quantities
        for (CartItem item : cart)
        {
            Product product = item.getProduct();
            product.setQuantity(product.getQuantity() - item.getQuantity());
        }

        // Ship items if needed
        if (!shippableItems.isEmpty())
            ShippingService.shippingCartItems(shippableItems);
                
        // Print receipt
        System.out.println("\n** Checkout receipt **");
        for (CartItem item : cart)
            System.out.printf("%dx %-15s %6.2f EGP%n",
                    item.getQuantity(), item.getProduct().getName(),
                    item.getProduct().getPrice() * item.getQuantity());

        System.out.println("--------------------------------");
        System.out.printf("Subtotal\t%6.2f EGP%n", subtotal);
        System.out.printf("Shipping\t%6.2f EGP%n", shippingFee);
        System.out.printf("Amount\t\t%6.2f EGP%n", totalAmount);
        System.out.printf("New balance\t%6.2f EGP%n", balance);
        System.out.println("--------------------------------");

        // Clear cart
        cart.clear();
    }

}
