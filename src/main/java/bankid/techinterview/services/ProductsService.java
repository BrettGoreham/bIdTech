package bankid.techinterview.services;

import bankid.techinterview.services.models.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

@Service
public class ProductsService {

    public static List<Product> products = new ArrayList<>(
            List.of(
                    new Product("0001", "Rolex Watch", 1000.00, 3, 2000.00),
                    new Product("0002", "Michael Kors Purse", 80.00, 2, 120.00),
                    new Product("0003", "iPhone XS", 400.00, -1, -1),
                    new Product("0004", "Casio Watch", 30.00, -1, -1)
            )
    );


    public double getCheckoutPrice(Map<String, Integer> idToCountMap) throws Exception {
        double price = 0;

        for (var entry : idToCountMap.entrySet()) {
            price += getPriceForId(entry.getKey(), entry.getValue());
        }

        return price;
    }

    private double getPriceForId(String id, int count) throws Exception {

        Product product = products.stream().filter(book -> book.getId().equals(id))
                .findFirst().orElse(null);

        if (product == null) {
            throw new Exception("unknown product Id: " + id);
        }

        if (product.getDiscountCount() > 0 ){
            int discounts = count / product.getDiscountCount();
            int regularPriced = count % product.getDiscountCount();

            return (discounts * product.getDiscountPrice()) + (regularPriced * product.getPrice());
        }
        else {
            return product.getPrice() * count;
        }

    }
}
