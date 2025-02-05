How to build:
    mvn clean install will build the project on the base folder.


How to run: mvn spring-boot:run from base folder will run the project and startup on port 8080


How I approached the problem.

1) Collect all items from array into a map of productId to count of how many of that product was in the basket.
2) For each item in the map get price for that count of products
   1) first check that product exists if not throw exception
   2) check if there is a discount.
      1) if there is a discount check how many times it applies
      2) apply regular price to all left over after applying discount x times
   3) if no discount return price * count

Things to improve if I had more time.

Error handling - custom error types for different problems edge cases (for example stock of items and such)
Data access level - With a product database or integration another layer would be useful for accessing that data instead of hardcoded products
separate maven projects for different sections of the project. 
interfaces to decouple layers