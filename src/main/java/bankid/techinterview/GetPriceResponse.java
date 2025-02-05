package bankid.techinterview;

public class GetPriceResponse {

    public GetPriceResponse(double price) {
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
