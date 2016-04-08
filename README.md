# BlueSpurs Interview Test

The goal of the test is creating a RESTful web service endpoint which will allow to input product name as query parameter and returns the product with the minimum price. The cheapest product is searched from two vendors "BestBuy" and "WalMart". The project will make two RESTful service call to the individual vendor to gather price from them and then retun the cheapset product by comparing the prices.

##Classes

The project is done using the Spring-MVC framework of Java. For the project five new classes are created.

###Controller Class
The MinimumProductPriceController is the main class that handles the user request and returns the response to the user. It is also responsible for making the two web service call to the verndors to grab the product prices from them. 

###Request Class
Three request classes are created to grab price data from the vendors. Two specific classes (BestBuyProductPrices and WalmartProductPrices) represnts the response from the vendors which contains the collection of ProductPrice object. The product price object contains price for individual item.

###Response Class
The MinimumProductPrice Class is used to send the response of the cheapest product to the user.

