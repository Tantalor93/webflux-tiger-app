package com.github.tantalor93;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@RestController
public class PerfTestController {

    WebClient client;

    public PerfTestController() {
        client = WebClient.builder().baseUrl("http://mock-server:8081").build();
    }

    @PostMapping("/perf-test")
    public Mono<String> perfTest(@RequestBody String body) {
        return client.post().uri("/mock").bodyValue(body)
                .retrieve()
                .bodyToMono(SomeResponse.class)
                .map(SomeResponse::getMsg);
    }

}
