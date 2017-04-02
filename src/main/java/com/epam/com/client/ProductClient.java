package com.epam.com.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestOperations;

import com.epam.com.product.Product;


@Component
public class ProductClient {
	
	@Autowired
	private RestOperations restOperations;
	
	private final  String url;
	private final String urlPost;
	
	@Autowired
	public ProductClient(@Value("${product.url}")final String url, @Value("${product.url.post}") final String urlPost) {
		this.url = url;
		this.urlPost = urlPost;
	}
	
	public Product getProduct(final int productId) {
		return restOperations.getForObject(url, Product.class, productId);
	}
	
	public void createProduct(final Product product) {
		restOperations.postForObject(urlPost, product, String.class);
	}
	
	public void delete(final int productId) {
		restOperations.delete(url, productId);
	}

}
