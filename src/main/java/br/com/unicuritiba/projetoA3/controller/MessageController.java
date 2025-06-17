package br.com.unicuritiba.projetoA3.controller;

import br.com.unicuritiba.projetoA3.models.ExternalApiMessage;
import br.com.unicuritiba.projetoA3.models.MessageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus; // Importe HttpStatus
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import java.util.Collections;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class MessageController {

    private static final String API_TOKEN = "ZpXO1G0TV9hau4owI1OqTYecBvZXOG";
    private static final String EXTERNAL_API_ENDPOINT = "https://apisphere.vision.inf.br/api/messages/send";

    @PostMapping("/send-message")
    public ResponseEntity<Map<String, Object>> sendMessage(@RequestBody MessageRequest request) {
        try {
            System.out.println("Requisição recebida do HTML: " + request.toString());

            String cleanNumber = "55" + request.getNumber().replaceAll("[^0-9]", "");
            System.out.println("Número limpo para API: " + cleanNumber);

            ExternalApiMessage externalApiMessage = new ExternalApiMessage(
                cleanNumber,
                request.getMessageBody(),
                "10",
                "11",
                false,
                true
            );

            System.out.println("JSON que será enviado para API externa: " + externalApiMessage.toString());

            WebClient webClient = WebClient.builder()
                    .baseUrl(EXTERNAL_API_ENDPOINT)
                    .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + API_TOKEN)
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .build();

            String apiResponse = webClient.post()
                    .bodyValue(externalApiMessage)
                    .retrieve()
                    // **Alteração AQUI:** Usar lambda para o predicado de status
                    .onStatus(status -> status.is4xxClientError(), clientResponse ->
                            clientResponse.bodyToMono(String.class).flatMap(body ->
                                Mono.error(new RuntimeException("Erro do cliente da API externa (" + clientResponse.statusCode() + "): " + body))
                            ))
                    // **Alteração AQUI:** Usar lambda para o predicado de status
                    .onStatus(status -> status.is5xxServerError(), clientResponse ->
                            clientResponse.bodyToMono(String.class).flatMap(body ->
                                Mono.error(new RuntimeException("Erro do servidor da API externa (" + clientResponse.statusCode() + "): " + body))
                            ))
                    .bodyToMono(String.class)
                    .block();

            System.out.println("Resposta da API externa: " + apiResponse);

            return ResponseEntity.ok(Map.of(
                    "sentJson", externalApiMessage,
                    "externalApiResponse", apiResponse,
                    "status", "success"
            ));

        } catch (Exception e) {
            System.err.println("Erro ao enviar mensagem: " + e.getMessage());
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Erro ao processar a requisição no backend", "details", e.getMessage()));
        }
    }
}