package bankid.techinterview;

public class Product {

    private String id;
    private String name;
    private double price;
    private int discountCount;
    private double discountPrice;

    public Product(String id, String name, double price, int discountCount, double discountPrice) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.discountCount = discountCount;
        this.discountPrice = discountPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getDiscountCount() {
        return discountCount;
    }

    public void setDiscountCount(int discountCount) {
        this.discountCount = discountCount;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }
}
