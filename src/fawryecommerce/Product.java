package fawryecommerce;

public class Product
{

    private int id;
    private String name;
    private double price;
    private int quantity;
    private boolean canBeExpired;
    private boolean isExpired;
    private boolean isShippable;
    private Double weight;

    public Product(int id, String name, double price, int quantity, boolean canBeExpired, boolean isExpired, boolean isShippable, Double weight)
    {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.canBeExpired = canBeExpired;
        this.isExpired = isExpired;
        this.isShippable = isShippable;
        this.weight = isShippable ? weight : null;
    }

    public String getProduct()
    {
        return "ID: " + id
                +" | Name: " + name
                + ", Price: " + price
                + ", Quantity: " + quantity
                + (canBeExpired ? (isExpired ? ", (Expired)" : ", (Not Expired)") : "")
                + (isShippable ? ", Weight: " + weight + "g" : "");
    }

    public double getPrice()
    {
        return price;
    }

    public String getName()
    {
        return name;
    }

    public int getQuantity()
    {
        return quantity;
    }
    
    public Double getWeight()
    {
        return weight;
    }

    public boolean isExpired()
    {
        return isExpired;
    }
    public boolean isShippable()
    {
        return isShippable;
    }

}
