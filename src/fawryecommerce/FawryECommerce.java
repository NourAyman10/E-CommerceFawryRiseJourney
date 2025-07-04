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
        products.add(new Product(1, "Laptop", 5000, 5, false, false, true, 2.5));
        products.add(new Product(2, "Milk", 30, 10, true, false, false, null));
        products.add(new Product(3, "Yogurt", 15, 20, true, true, false, null));
        products.add(new Product(4, "Headphones", 250, 3, false, false, true, 0.8));
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
        int productIndex = scanner.nextInt() - 1;

        if (productIndex < 0 || productIndex >= products.size())
        {
            System.out.println("Invalid product selection!");
            return;
        }

        Product selectedProduct = products.get(productIndex);

        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine(); // consume newline

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

        customer.addToCart(selectedProduct, quantity);
        System.out.println(quantity + " x " + selectedProduct.getName() + " added to cart!");
    }
}
