package com.epam.com.product;

public class Product {
	private String NAME;
	private double PRICE;
	private int ID;

	public Product(String name, double price, int id) {
		NAME = name;
		PRICE = price;
		ID = id;
	}

	public String getName() {
		return NAME;
	}

	public double getPrice() {
		return PRICE;
	}

	public int getId() {
		return ID;
	}
	
	@Override
	public String toString() {
		return "Product [name=" + NAME + ", price=" + PRICE + ", id=" + ID
				+ "]";
	}
	
	@Override
	public boolean equals(Object other) {
		if(other == null || !(other instanceof Product)) {
			return false;
		}
		Product product = (Product) other;
		return this.NAME.equals(product.NAME) && this.ID == product.ID && this.PRICE == product.PRICE;
	}
}
