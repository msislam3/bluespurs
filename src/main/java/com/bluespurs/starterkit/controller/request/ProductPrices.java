package com.bluespurs.starterkit.controller.request;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/****
 * Class to represent multiple prices of a product
 * 
 * @author Mohammad Saiful Islam
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductPrices {

	private String canonicalUrl;
	private List<ProductPrice> products;
	
	public ProductPrices(){
		
	}
	
	/****
	 * Gets the product prices
	 * 
	 * @return List of ProductPrice
	 */
	public List<ProductPrice> getProductPrices(){
		return products;
	}
	
	/****
	 * Sets the product prices
	 * 
	 * @param products List of product prices to set
	 */
	public void setProductPrices(List<ProductPrice> products){
		this.products = products;
	}

	/****
	 * Gets the minimum price of all the products
	 * 
	 * @return Minimum price of the product
	 *
	 */
	public float getMinimumPrice(){
		
		if(products != null){
			Optional<ProductPrice> price = products.stream().
					min(Comparator.comparing(ProductPrice::getPrice));
			
			if(price.isPresent()){
				return price.get().getPrice();
			}else{
				return -1;
			}
		}else{
			return -2;
		}
	}
}
