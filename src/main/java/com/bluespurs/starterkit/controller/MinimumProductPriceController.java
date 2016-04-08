package com.bluespurs.starterkit.controller;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.bind.annotation.RequestParam;

import com.bluespurs.starterkit.controller.request.BestbuyProductPrices;
import com.bluespurs.starterkit.controller.request.WalmartProductPrices;
import com.bluespurs.starterkit.controller.resource.MinimumProductPrice;

/***
 * Controller that responds to minimum product price request
 * 
 * @author Mohammad Saiful Islam
 * @version 1.0.1
 *
 */
@RestController
public class MinimumProductPriceController {

	public static final String BESTBUYQUERYFORMAT = "http://api.bestbuy.com/v1/products(name=%s*)?show=name,salePrice&apiKey=pfe9fpy68yg28hvvma49sc89&format=json";	
	public static final String WALMARTQUERYFORMAT = "http://api.walmartlabs.com/v1/search?apiKey=rm25tyum3p9jm9x9x7zxshfa&query=%s";
	
    /**
     * The page returns the best price given the product name
     * The method is mapped to "/product/search" as a GET request. It accepts 
     * the product name in the 'name' query string and returns the best price of
     * the product along with the location and currency as response by comparing 
     * prices from BestBuy and WalMart. It makes two REST queries to get the 
     * prices from each of the stores. Then it returns the minimum price as a 
     * response
     */
    @RequestMapping(value = "/product/search", method = RequestMethod.GET)
	public ResponseEntity minimumProductPrice(@RequestParam(value="name") String name){
    	
    	if(name.isEmpty()){
    		return ResponseEntity
    				.status(HttpStatus.BAD_REQUEST)
    				.body("Missing Product Name");
    	}
    	
    	try{
	    	RestTemplate restTemplate = new RestTemplate();
	    	ResponseEntity<BestbuyProductPrices> bestBuyResponse =  restTemplate.
	    			exchange(String.format(BESTBUYQUERYFORMAT, name), 
	    					HttpMethod.GET, 
	    					null,
	    					new ParameterizedTypeReference<BestbuyProductPrices>(){});
	    	
	    	BestbuyProductPrices bestbuyPrices = bestBuyResponse.getBody();
	    	
	    	restTemplate = new RestTemplate();
	    	ResponseEntity<WalmartProductPrices> walmartResponse =  restTemplate.
	    			exchange(String.format(WALMARTQUERYFORMAT, name), 
	    					HttpMethod.GET, 
	    					null,
	    					new ParameterizedTypeReference<WalmartProductPrices>(){});
	    	
	    	WalmartProductPrices walmartPrices = walmartResponse.getBody();
	    	
	    	MinimumProductPrice price;
	    	
	    	if(walmartPrices.getMinimumPrice() <= bestbuyPrices.getMinimumPrice())
	    	{
	    		price = new MinimumProductPrice(name,
	    				walmartPrices.getMinimumPrice(),"CAD","Walmart");
		    	
	    	}else{
	    		price = new MinimumProductPrice(name,
		    			bestbuyPrices.getMinimumPrice(),"CAD","BestBuy");
	    	}
	    
	    	return ResponseEntity
	    			.status(HttpStatus.OK)
	    			.body(price);
    	}catch(Exception ex){
    		return ResponseEntity
    				.status(HttpStatus.BAD_REQUEST)
    				.body(ex.toString());
    	}
	}
}