package com.ns1903372dot.whatsapp;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WhatsappChatbotAssignmentApplicationTests {

	@LocalServerPort
	private int port;

	@Test
	void healthEndpointReturnsOk() throws Exception {
		HttpResponse<String> response = send("GET", "/health", null);

		assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.body()).contains("\"status\":\"ok\"");
	}

	@Test
	void webhookReturnsHelloForHi() throws Exception {
		HttpResponse<String> response = send("POST", "/webhook", """
				{
				  "from": "919999999999",
				  "message": "Hi"
				}
				""");

		assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.body()).contains("\"from\":\"919999999999\"");
		assertThat(response.body()).contains("\"receivedMessage\":\"Hi\"");
		assertThat(response.body()).contains("\"reply\":\"Hello\"");
	}

	@Test
	void webhookReturnsGoodbyeForBye() throws Exception {
		HttpResponse<String> response = send("POST", "/webhook", """
				{
				  "from": "919999999999",
				  "message": "Bye"
				}
				""");

		assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
		assertThat(response.body()).contains("\"reply\":\"Goodbye\"");
	}

	@Test
	void webhookRejectsInvalidRequest() throws Exception {
		HttpResponse<String> response = send("POST", "/webhook", """
				{
				  "from": "",
				  "message": ""
				}
				""");

		assertThat(response.statusCode()).isEqualTo(HttpStatus.BAD_REQUEST.value());
		assertThat(response.body()).contains("\"error\":\"Validation failed\"");
	}

	private String baseUrl(String path) {
		return "http://localhost:" + port + path;
	}

	private HttpResponse<String> send(String method, String path, String body)
			throws IOException, InterruptedException {
		HttpRequest.Builder builder = HttpRequest.newBuilder()
				.uri(URI.create(baseUrl(path)))
				.header("Content-Type", MediaType.APPLICATION_JSON_VALUE);

		if ("POST".equals(method)) {
			builder.POST(HttpRequest.BodyPublishers.ofString(body == null ? "" : body));
		} else {
			builder.GET();
		}

		return HttpClient.newHttpClient().send(builder.build(), HttpResponse.BodyHandlers.ofString());
	}
}
