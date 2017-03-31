package com.epam.com.client;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.xml.MarshallingHttpMessageConverter;
import org.springframework.oxm.xstream.XStreamMarshaller;
import org.springframework.web.client.RestOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.epam.com.product.Product;

@EnableWebMvc
@Configuration
public class RestConfig extends WebMvcConfigurerAdapter {

	@Bean
	public RestOperations createRestTemplate(
			final ClientHttpRequestFactory clientHttpRequestFactory) {
		RestTemplate restTemplate = new RestTemplate(clientHttpRequestFactory);
		restTemplate.setMessageConverters(getMessageConverters());
		return restTemplate;
	}

	private List<HttpMessageConverter<?>> getMessageConverters() {
		XStreamMarshaller marshaller = new XStreamMarshaller();
		Map<String, Class> aliases = new HashMap<String, Class>();
		aliases.put("PRODUCT", com.epam.com.product.Product.class);
		marshaller.setAliases(aliases);
		MarshallingHttpMessageConverter marshallingConverter = new MarshallingHttpMessageConverter(
				marshaller);
		List<HttpMessageConverter<?>> converters = new ArrayList<HttpMessageConverter<?>>();
		converters.add(marshallingConverter);
		return converters;
	}

	@Bean
	public ClientHttpRequestFactory createClientHttpRequestFactory(
			@Value("${connect.timeout}") final int connectTimeout,
			@Value("${read.timeout}") int readTimeout) {
		HttpComponentsClientHttpRequestFactory httpComponentsClientHttpRequestFactory = new HttpComponentsClientHttpRequestFactory();
		httpComponentsClientHttpRequestFactory.setReadTimeout(readTimeout);
		httpComponentsClientHttpRequestFactory
				.setConnectTimeout(connectTimeout);
		return httpComponentsClientHttpRequestFactory;
	}
}
