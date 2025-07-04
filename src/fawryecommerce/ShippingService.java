package fawryecommerce;

import java.util.List;

public class ShippingService
{
    public static void shippingItems(List<Product> products)
    {
        System.out.println("Shipping Items");
        for (Product product : products)
        {
            if(product.isShippable()) {
                System.out.printf("- %s (Weight: %.2f kg)%n", product.getName(), product.getWeight());
            }
            
        }
        System.out.println("Items Shipped Successfully");
    }
}
