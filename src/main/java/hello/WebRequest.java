// stupid basic request 
// https://www.baeldung.com/java-http-request

package hello;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ReactiveHttpOutputMessage;
import org.springframework.http.client.reactive.ClientHttpRequest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

public class WebRequest {

	public static String readCurrentSettings(String url, String path, GetTempRequest getTempReq) {

		// using the big version for flexibility
		WebClient client = WebClient.builder().baseUrl(url) // the server
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();

		WebClient.RequestBodySpec uri = client.method(HttpMethod.POST).uri(path);

		BodyInserter<GetTempRequest, ReactiveHttpOutputMessage> inserter = BodyInserters.fromObject(getTempReq);

		WebClient.ResponseSpec response1 = uri.body(inserter)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML).retrieve();

		return response1.bodyToMono(String.class).block();

	}

	public static String setCurrentSettings(String url, String path, SetTempRequest setTempReq) {// 111111111111

		// using the big version for flexibility
		WebClient client = WebClient.builder().baseUrl(url) // the server
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();

		WebClient.RequestBodySpec uri = client.method(HttpMethod.POST).uri(path);

		BodyInserter<SetTempRequest, ReactiveHttpOutputMessage> inserter = BodyInserters.fromObject(setTempReq);

		WebClient.ResponseSpec response1 = uri.body(inserter)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML).retrieve();

		return response1.bodyToMono(String.class).block();

	}

	public static String makeFakeRequest(String url, String path) {

		// using the big version for flexibility
		WebClient client = WebClient.builder().baseUrl(url) // the server
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();

		WebClient.RequestBodySpec uri = client.method(HttpMethod.POST).uri(path);

		LinkedMultiValueMap<String, Object> map = new LinkedMultiValueMap<>();

		map.add("name", "Serge");

		BodyInserter<MultiValueMap<String, Object>, ClientHttpRequest> inserter = BodyInserters.fromMultipartData(map);

		WebClient.ResponseSpec response1 = uri.body(inserter)
				.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
				.accept(MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML).retrieve();

		return response1.bodyToMono(String.class).block();

	}

}
