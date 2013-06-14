package de.seco.bloxx.dataaccess;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJacksonHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

public class RestClientImpl<T> {
	private StringBuilder queryString;
	private HttpEntity<?> requestEntity;
	private RestTemplate restTemplate;
	private String uri;

	public RestClientImpl(final String uri) {
		this.uri = uri;
		queryString = new StringBuilder();
		setHeader();
		List<HttpMessageConverter<?>> messageConverters = setAndReturnMessageConverterList();
		setRestTemplate(messageConverters);
	}
	
	private void setHeader() {
		HttpHeaders requestHeaders = new HttpHeaders();
	    requestHeaders.setAccept(Collections.singletonList(new MediaType("application", "json")));
	    requestEntity = new HttpEntity<Object>(requestHeaders);
	}
	
	private List<HttpMessageConverter<?>> setAndReturnMessageConverterList() {
	    MappingJacksonHttpMessageConverter messageConverter = new MappingJacksonHttpMessageConverter();
	    List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
	    messageConverters.add(messageConverter);
	    return messageConverters;
	}
	
	private void setRestTemplate(final List<HttpMessageConverter<?>> messageConverters) {
	    restTemplate = new RestTemplate();
	    restTemplate.setMessageConverters(messageConverters);
	}
	
	public void addKeyValueParam(final String key, final String value) {
		if (queryString.length() == 0) {
			queryString.append("?");
		}
		else {
			queryString.append("&");
		}
		queryString.append(key + "=" + value);
	}
	
	public T sendRequestAndMarshallResult(Class<T> classType) {
//		example of uri: "http://10.0.2.2:8080/mobile/login?email={email}"
	    ResponseEntity<T> responseEntity = restTemplate.exchange(uri+queryString.toString(), HttpMethod.GET, requestEntity, classType);
	    return responseEntity.getBody();
	}
	
	public T[] sendRequestAndMarshallResult(Class<T[]> classType) {
	    ResponseEntity<T[]> responseEntity = restTemplate.exchange(uri+queryString.toString(), HttpMethod.GET, requestEntity, classType);
	    return responseEntity.getBody();
	}
}