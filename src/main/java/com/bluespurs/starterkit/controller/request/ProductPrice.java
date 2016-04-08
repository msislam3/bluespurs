package com.bluespurs.starterkit.controller.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/****
 * Class to represent price of a product
 * 
 * @author Mohammad Saiful Islam
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductPrice {

	private String name;
	private float salePrice;
	
	public ProductPrice(){
		
	}
	
	/****
	 * Gets the name of the product
	 * 
	 * @return Name of the product
	 *
	 */
	public String getName(){
		return name;
	}
	
	/****
	 * Sets the name of the product
	 *
	 * @param Name of the product
	 */
	public void setName(String name){
		this.name = name;
	}
	
	/****
	 * Gets the price of the product
	 * 
	 * @return Price of the product
	 *
	 */
	public float getPrice(){
		return salePrice;
	}
	
	/****
	 * Sets the price of the product
	 * 
	 *@param Price of the product
	 */
	public void setPrice(float price){
		this.salePrice = price;
	}
}
