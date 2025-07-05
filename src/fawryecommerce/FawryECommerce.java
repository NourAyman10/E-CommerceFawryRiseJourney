package fawryecommerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FawryECommerce
{

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        List<Product> products = fillProducts();
        Customer customer = new Customer(1000);
        boolean programRunning = true;

        while (programRunning)
        {
            menu();
            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option)
            {
                case 1:
                    displayProducts(products);
                    break;
                case 2:
                    addToCart(scanner, products, customer);
                    break;
                case 3:
                    customer.viewCart();
                    break;
                case 4:
                    customer.checkout();
                    programRunning = false;
                    break;
                case 5:
                    programRunning = false;
                    System.out.println("Thank you for using Fawry E-Commerce!");
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
        scanner.close();

    }

    static List<Product> fillProducts()
    {
        List<Product> products = new ArrayList<>();
//        int id, String name, double price, int quantity, boolean canBeExpired, boolean isExpired, boolean isShippable, Double weight
        products.add(new Product(1, "Laptop", 5000, 5, false, false, true, 2500.00));
        products.add(new Product(2, "Milk", 30, 10, true, false, false, null));
        products.add(new Product(3, "Yogurt", 15, 20, true, true, false, null));
        products.add(new Product(4, "Headphones", 250, 3, false, false, true, 800.00));
        products.add(new Product(5, "Online Course", 1000, 999, false, false, false, null));
        return products;
    }

    static void menu()
    {
        System.out.println("1- Display products");
        System.out.println("2- Add to cart");
        System.out.println("3- View cart");
        System.out.println("4- Checkout");
        System.out.println("5- Exit");
        System.out.println("Enter option number");
    }

    static void displayProducts(List<Product> products)
    {
        System.out.println("Our Products");
        for (Product product : products)
            System.out.println(product.getProduct());
    }

    static void addToCart(Scanner scanner, List<Product> products, Customer customer)
    {
        displayProducts(products);
        System.out.print("Enter product ID to add to cart: ");
        int productId = scanner.nextInt();
        Product selectedProduct = null;
        for (Product product : products) {
            if (product.getId() == productId) {
                selectedProduct = product;
                break;
            }
        }
        if (selectedProduct == null) {
            System.out.println("Invalid product ID!");
            return;
        }
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        if (quantity <= 0)
        {
            System.out.println("Quantity must be positive!");
            return;
        }

        if (quantity > selectedProduct.getQuantity())
        {
            System.out.println("Not enough stock available!");
            return;
        }
        
        if (selectedProduct.isExpired()) {
            System.out.println("This product has expired and cannot be added to cart!");
            return;
        }

        customer.addToCart(selectedProduct, quantity);
        System.out.println(quantity + "x " + selectedProduct.getName() + " added to cart!");
    }
}
