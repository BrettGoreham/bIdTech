package bankid.techinterview.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static java.util.Map.entry;

public class ProductsServiceTests {

    public ProductsService productsService = new ProductsService();

    @Test
    public void getCheckoutPrice_oneProductPrice_test() throws Exception {
        double price = productsService.getCheckoutPrice(Map.ofEntries(
                entry(ProductsService.products.getFirst().getId(), 1)
        ));

        Assertions.assertEquals(ProductsService.products.getFirst().getPrice(), price);
    }

    @Test
    public void getCheckoutPrice_twoProductPrice_test() throws Exception {
        double price = productsService.getCheckoutPrice(Map.ofEntries(
                entry("0001", 2)
        ));

        Assertions.assertEquals(2000, price);
    }

    @Test
    public void getCheckoutPrice_ProductDiscountPrice_test() throws Exception {
        double price = productsService.getCheckoutPrice(Map.ofEntries(
                entry("0001", 3)
        ));

        Assertions.assertEquals(2000, price);
    }

    @Test
    public void getCheckoutPrice_ProductExceedsDiscountAmountPrice_test() throws Exception {
        double price = productsService.getCheckoutPrice(Map.ofEntries(
                entry("0001", 8)
        ));

        //2 x 3 for 2000 + 2 for 2000

        Assertions.assertEquals(6000, price);
    }

    @Test
    public void getCheckoutPrice_aLotOfProducts_test() throws Exception {
        double price = productsService.getCheckoutPrice(Map.ofEntries(
                entry("0001", 3001)
        ));

        //1000 x 3 for 2000 + 1 for 1000

        Assertions.assertEquals(1000 * 2000 + 1000, price);
    }

    @Test
    public void getCheckoutPrice_simpleMixedProducts_test() throws Exception {
        double price = productsService.getCheckoutPrice(Map.ofEntries(
                entry("0001", 1),
                entry("0002", 1)
        ));

        Assertions.assertEquals(1080, price);
    }

    @Test
    public void getCheckoutPrice_allMixedProducts_test() throws Exception {
        double price = productsService.getCheckoutPrice(Map.ofEntries(
                entry("0001", 1),
                entry("0002", 1),
                entry("0003", 1),
                entry("0004", 1)
        ));

        Assertions.assertEquals(1000 + 80 + 400 + 30, price);
    }

    @Test
    public void getCheckoutPrice_MixedProductsWithDiscounts_test() throws Exception {
        double price = productsService.getCheckoutPrice(Map.ofEntries(
                entry("0001", 3),
                entry("0002", 2)
        ));

        Assertions.assertEquals(2000 + 120, price);
    }


    @Test
    public void getCheckoutPrice_throwsInvalidProductIdException_test() {

        Assertions.assertThrows(Exception.class,
            () -> productsService.getCheckoutPrice(
                    Map.ofEntries(entry("invalid", 2))
            )
        );
    }
}
