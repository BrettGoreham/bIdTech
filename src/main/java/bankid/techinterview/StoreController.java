package bankid.techinterview;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StoreController {

    @PostMapping("")
    public GetPriceResponse GetPrice(@RequestBody String[] items ) {
        for(String item : items) {
            System.out.println(item);
        }

        return new GetPriceResponse(12.34);
    }
}
