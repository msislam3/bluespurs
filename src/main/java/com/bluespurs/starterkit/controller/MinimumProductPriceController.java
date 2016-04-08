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

import com.bluespurs.starterkit.controller.request.ProductPrices;
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
	public static final String BESTBUYQUERY = "http://api.bestbuy.com/v1/products(name=ipad*)?show=name,salePrice&apiKey=pfe9fpy68yg28hvvma49sc89&format=json";
	
    /**
     * The page returns the best price given the product name
     * The method is mapped to "/product/search" as a GET request. It accepts 
     * the product name in the 'name' query string and returns the best price of
     * the product along with the location and currency as response  
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
	    	ResponseEntity<ProductPrices> response =  restTemplate.
	    			exchange(String.format(BESTBUYQUERYFORMAT, name), 
	    					HttpMethod.GET, 
	    					null,
	    					new ParameterizedTypeReference<ProductPrices>(){});
	    	
	    	ProductPrices bestbuyPrices = response.getBody();
	    	
	    	restTemplate = new RestTemplate();
	    	response =  restTemplate.
	    			exchange(String.format(BESTBUYQUERYFORMAT, name), 
	    					HttpMethod.GET, 
	    					null,
	    					new ParameterizedTypeReference<ProductPrices>(){});
	    	
	    	ProductPrices walmartPrices = response.getBody();
	    	
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