package bankid.techinterview.application.models;

public class CheckoutResponse {

    public CheckoutResponse(double price) {
        this.price = price;
    }
    public double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

}
