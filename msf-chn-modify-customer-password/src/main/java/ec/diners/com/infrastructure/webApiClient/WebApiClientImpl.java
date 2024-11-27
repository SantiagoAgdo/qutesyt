package ec.diners.com.infrastructure.webApiClient;

import an.awesome.pipelinr.Pipeline;
import ec.diners.com.domain.constants.LogType;
import ec.diners.com.domain.interfaces.webApiClient.WebApiClient;
import ec.diners.com.domain.services.entityLog.systemLog.CreateEntityLogSystemService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

import java.time.Duration;
import java.util.List;

@Component
public class WebApiClientImpl implements WebApiClient {
    private final Pipeline mediator;

    public WebApiClientImpl(Pipeline mediator) {
        this.mediator = mediator;
    }

    public String get(String urlString, List<String> headers) {

        mediator.send(new CreateEntityLogSystemService(
                LogType.INFO,
                String.format("API-REQUEST: GET %s", urlString))
        );

        try {
            var customHeaders = new HttpHeaders();
            customHeaders.add("User-Agent", "Java/" + System.getProperty("java.version"));
            customHeaders.add("Content-Type", "application/json");
            customHeaders.add("Accept", "application/json");
            if (headers != null) {
                for (String header : headers) {
                    String[] headerSplit = header.split(":");
                    customHeaders.add(headerSplit[0], headerSplit[1]);
                }
            }

            ConnectionProvider provider = ConnectionProvider.builder("fixed")
                    .maxConnections(500)
                    .maxIdleTime(Duration.ofSeconds(20))
                    .maxLifeTime(Duration.ofSeconds(60))
                    .pendingAcquireTimeout(Duration.ofSeconds(60))
                    .evictInBackground(Duration.ofSeconds(120)).build();

            var weClientResponse = WebClient.builder()
                    .clientConnector(new ReactorClientHttpConnector(HttpClient.create(provider)))
                    .build()
                    .get()
                    .uri(urlString)
                    .headers(httpHeaders -> httpHeaders.addAll(customHeaders))
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            mediator.send(new CreateEntityLogSystemService(
                    LogType.INFO,
                    String.format("API-RESPONSE: GET %s response %s", urlString, weClientResponse))
            );
            return weClientResponse;
        }
        catch (Exception e) {
            mediator.send(new CreateEntityLogSystemService(
                LogType.ERROR,
                String.format("API-EXCEPTION: GET %s error %s", urlString, e.getMessage()))
            );
        }

        return null;
    }

    public String get(String urlString) {
        return get(urlString, null);
    }

    public String post(String urlString, String arguments, List<String> headers) {
        mediator.send(new CreateEntityLogSystemService(
                LogType.INFO,
                String.format("API-REQUEST: POST %s arguments %s", urlString, arguments))
        );

        try {
            var customHeaders = new HttpHeaders();
            customHeaders.add("User-Agent", "Java/" + System.getProperty("java.version"));
            customHeaders.add("Content-Type", "application/json");
            customHeaders.add("Accept", "application/json");
            if (headers != null) {
                for (String header : headers) {
                    String[] headerSplit = header.split(":");
                    customHeaders.add(headerSplit[0], headerSplit[1]);
                }
            }

            ConnectionProvider provider = ConnectionProvider.builder("fixed")
                    .maxConnections(500)
                    .maxIdleTime(Duration.ofSeconds(20))
                    .maxLifeTime(Duration.ofSeconds(60))
                    .pendingAcquireTimeout(Duration.ofSeconds(60))
                    .evictInBackground(Duration.ofSeconds(120))
                    .build();

            return WebClient.builder()
                    .clientConnector(new ReactorClientHttpConnector(HttpClient.create(provider)))
                    .build()
                    .post()
                    .uri(urlString)
                    .headers(httpHeaders -> httpHeaders.addAll(customHeaders))
                    .bodyValue(arguments)
                    .retrieve()
                    .onStatus(status -> status.is4xxClientError(), clientResponse -> {
                        return clientResponse.bodyToMono(String.class)
                                .flatMap(errorBody -> {
                                    mediator.send(new CreateEntityLogSystemService(
                                            LogType.ERROR,
                                            String.format("API-CLIENT-ERROR: POST %s Status: %s Error: %s",
                                                    urlString,
                                                    clientResponse.statusCode(),
                                                    errorBody))
                                    );
                                    return Mono.error(new WebClientResponseException(
                                            clientResponse.statusCode().value(),
                                            HttpStatus.valueOf(clientResponse.statusCode().value()).getReasonPhrase(),
                                            clientResponse.headers().asHttpHeaders(),
                                            errorBody.getBytes(),
                                            null
                                    ));
                                });
                    })
                    .onStatus(status -> status.is5xxServerError(), clientResponse -> {
                        return clientResponse.bodyToMono(String.class)
                                .flatMap(errorBody -> {
                                    mediator.send(new CreateEntityLogSystemService(
                                            LogType.ERROR,
                                            String.format("API-SERVER-ERROR: POST %s Status: %s Error: %s",
                                                    urlString,
                                                    clientResponse.statusCode(),
                                                    errorBody))
                                    );
                                    return Mono.error(new WebClientResponseException(
                                            clientResponse.statusCode().value(),
                                            HttpStatus.valueOf(clientResponse.statusCode().value()).getReasonPhrase(),
                                            clientResponse.headers().asHttpHeaders(),
                                            errorBody.getBytes(),
                                            null
                                    ));
                                });
                    })
                    .bodyToMono(String.class)
                    .doOnSuccess(response -> {
                        mediator.send(new CreateEntityLogSystemService(
                                LogType.INFO,
                                String.format("API-RESPONSE: POST %s response %s", urlString, response))
                        );
                    })
                    .block();
        } catch (WebClientResponseException e) {
            mediator.send(new CreateEntityLogSystemService(
                    LogType.ERROR,
                    String.format("API-HTTP-EXCEPTION: POST %s Status: %d Error: %s",
                            urlString,
                            e.getStatusCode().value(),
                            e.getResponseBodyAsString()))
            );
            throw e;
        } catch (Exception e) {
            mediator.send(new CreateEntityLogSystemService(
                    LogType.ERROR,
                    String.format("API-EXCEPTION: POST %s error %s", urlString, e.getMessage()))
            );
            throw new RuntimeException("Error en la llamada HTTP", e);
        }
    }

    public String post(String urlString, String arguments) {
        return post(urlString, arguments, null);
    }

    public String put(String urlString, String arguments, List<String> headers) {

        mediator.send(new CreateEntityLogSystemService(
                LogType.INFO,
                String.format("API-REQUEST: PUT %s arguments %s", urlString, arguments))
        );

        try {
            var customHeaders = new HttpHeaders();
            customHeaders.add("User-Agent", "Java/" + System.getProperty("java.version"));
            customHeaders.add("Content-Type", "application/json");
            customHeaders.add("Accept", "application/json");
            if (headers != null) {
                for (String header : headers) {
                    String[] headerSplit = header.split(":");
                    customHeaders.add(headerSplit[0], headerSplit[1]);
                }
            }

            ConnectionProvider provider = ConnectionProvider.builder("fixed")
                    .maxConnections(500)
                    .maxIdleTime(Duration.ofSeconds(20))
                    .maxLifeTime(Duration.ofSeconds(60))
                    .pendingAcquireTimeout(Duration.ofSeconds(60))
                    .evictInBackground(Duration.ofSeconds(120)).build();

            var weClientResponse = WebClient.builder()
                    .clientConnector(new ReactorClientHttpConnector(HttpClient.create(provider)))
                    .build()
                    .put()
                    .uri(urlString)
                    .headers(httpHeaders -> httpHeaders.addAll(customHeaders))
                    .bodyValue(arguments)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            mediator.send(new CreateEntityLogSystemService(
                    LogType.INFO,
                    String.format("API-RESPONSE: PUT %s response %s", urlString, weClientResponse))
            );
            return weClientResponse;
        }
        catch (Exception e) {
            mediator.send(new CreateEntityLogSystemService(
                    LogType.ERROR,
                    String.format("API-EXCEPTION: PUT %s error %s", urlString, e.getMessage()))
            );
        }

        return null;
    }

    public String put(String urlString, String arguments) {
        return put(urlString, arguments, null);
    }

    public String patch(String urlString, String arguments, List<String> headers) {

        mediator.send(new CreateEntityLogSystemService(
                LogType.INFO,
                String.format("API-REQUEST: PATCH %s arguments %s", urlString, arguments))
        );

        try {
            var customHeaders = new HttpHeaders();
            customHeaders.add("User-Agent", "Java/" + System.getProperty("java.version"));
            customHeaders.add("Content-Type", "application/json");
            customHeaders.add("Accept", "application/json");
            if (headers != null) {
                for (String header : headers) {
                    String[] headerSplit = header.split(":");
                    customHeaders.add(headerSplit[0], headerSplit[1]);
                }
            }

            ConnectionProvider provider = ConnectionProvider.builder("fixed")
                    .maxConnections(500)
                    .maxIdleTime(Duration.ofSeconds(20))
                    .maxLifeTime(Duration.ofSeconds(60))
                    .pendingAcquireTimeout(Duration.ofSeconds(60))
                    .evictInBackground(Duration.ofSeconds(120)).build();

            var weClientResponse = WebClient.builder()
                    .clientConnector(new ReactorClientHttpConnector(HttpClient.create(provider)))
                    .build()
                    .patch()
                    .uri(urlString)
                    .headers(httpHeaders -> httpHeaders.addAll(customHeaders))
                    .bodyValue(arguments)
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();

            mediator.send(new CreateEntityLogSystemService(
                    LogType.INFO,
                    String.format("API-RESPONSE: PATCH %s response %s", urlString, weClientResponse))
            );
            return weClientResponse;
        }
        catch (WebClientException | NullPointerException | IllegalArgumentException e) {
            mediator.send(new CreateEntityLogSystemService(
                    LogType.ERROR,
                    String.format("API-EXCEPTION: PATCH %s error %s", urlString, e.getMessage()))
            );
        }

        return null;
    }

    public String patch(String urlString, String arguments) {
        return patch(urlString, arguments, null);
    }
}
