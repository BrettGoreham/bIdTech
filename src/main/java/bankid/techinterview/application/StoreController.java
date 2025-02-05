package bankid.techinterview.application;

import bankid.techinterview.models.CheckoutResponse;
import bankid.techinterview.services.ProductsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class StoreController {

    private final ProductsService productsService;

    public StoreController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @PostMapping("/api/v1/checkout")
    public ResponseEntity<CheckoutResponse> checkout(@RequestBody String[] items ) throws Exception {
        HashMap<String, Integer> idToCountMap = new HashMap<>();
        for(String item : items) {
            if (!idToCountMap.containsKey(item)) {
                idToCountMap.put(item, 1);
            }
            else {
                idToCountMap.put(item, idToCountMap.get(item) + 1);
            }
        }

        double total = productsService.getCheckoutPrice(idToCountMap);
        return ResponseEntity.status(200).body(new CheckoutResponse(total));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleNoContent(Exception e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }
}
