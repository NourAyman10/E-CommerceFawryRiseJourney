package fawryecommerce;

import java.util.Map;
import java.util.HashMap;
import java.util.List;

public class ShippingService
{

    public static void shippingCartItems(List<ShippingItems> shippingItems)
    {
        System.out.println("\n** Shipment notice **");

        // Group items by name and sum their weights
        Map<String, Integer> itemCounts = new HashMap<>();
        Map<String, Double> itemWeights = new HashMap<>();

        for (ShippingItems item : shippingItems)
        {
            String name = item.getName();
            double weight = item.getWeight();

            itemCounts.put(name, itemCounts.getOrDefault(name, 0) + 1);
            itemWeights.put(name, itemWeights.getOrDefault(name, 0.0) + weight);
        }

        double totalWeightKg = 0;

        // Print grouped items
        for (Map.Entry<String, Integer> entry : itemCounts.entrySet())
        {
            String name = entry.getKey();
            int count = entry.getValue();
            double weightG = itemWeights.get(name);

            System.out.printf("%dx %s\t%.0fg%n", count, name, weightG);
            totalWeightKg += weightG / 1000; 
        }

        System.out.printf("Total package weight %.2fkg%n", totalWeightKg);
    }
}
