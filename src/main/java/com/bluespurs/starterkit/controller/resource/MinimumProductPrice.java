package com.bluespurs.starterkit.controller.resource;

/***
 * Class that represents the minimum product price response
 * 
 * @author Mohammad Saiful Islam
 * @version 1.0.1
 *
 */
public class MinimumProductPrice {
	
	private final String productName;
	private final float bestPrice;
	private final String currency;
	private final String location;
	
	public MinimumProductPrice(String productName, float bestPrice, String currency, 
			String location){
		this.productName = productName;
		this.bestPrice = bestPrice;
		this.currency = currency;
		this.location = location;
	}
	
	/***
	 * Gets the product name
	 * 
	 * @return The product name
	 */
	public String getProductName(){
		return productName;
	}
	
	/***
	 * Gets the minimum product price
	 * 
	 * @return The minimum product price
	 */
	public float getBestPrice(){
		return bestPrice;
	}
	
	/***
	 * Gets the currency
	 * 
	 * @return The currency
	 */
	public String getCurrency(){
		return currency;
	}
	
	/***
	 * Gets the location of the minimum priced product
	 * 
	 * @return The location
	 */
	public String getLocation(){
		return location;
	}
}
