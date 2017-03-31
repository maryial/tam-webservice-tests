package com.epam.com.client;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.epam.com.product.Product;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProductTest {

	private final Logger LOGGER = LoggerFactory.getLogger(ProductTest.class);
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Autowired
	private ProductClient productClient;
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Value("${product.url}")
	private String url;
	
	@Value("${product.url.post}")
	private String ulrPost;
	
	private static int id;
	
	@BeforeClass
	public static void setUp() {
		id = (int)(Math.random() * 100 + 100);
	}
	
	@Test
    public void testCreatingNewProduct() {
		LOGGER.info("Creating product with id {}", id);
		Product expected = new Product("test", 22.7, id);
		productClient.createProduct(expected);
		Product actual = productClient.getProduct(id);
		Assert.assertEquals(expected, actual);
	}
	
	@Test
    public void testRemovingCreatedProduct() {		
		LOGGER.info("Deleting product with id {}", id);
		productClient.delete(id);
		exception.expect(HttpClientErrorException.class);
		exception.expectMessage("404 Not Found");
		productClient.getProduct(id);		
	}	
}
