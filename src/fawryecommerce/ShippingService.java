package fawryecommerce;

import java.util.List;

public class ShippingService
{
    public static void shippingCartItems(List<ShippingItems> shippingItems)
    {
        System.out.println("\n** Shipment notice **");
        double totalWeight = 0;
        
        for (ShippingItems item : shippingItems) {
            double weight = item.getWeight();
            System.out.printf("%s\t%.2fkg%n", item.getName(), weight);
            totalWeight += weight;
        }
        
        System.out.printf("Total package weight %.2fkg%n\n", totalWeight);
    }
}
