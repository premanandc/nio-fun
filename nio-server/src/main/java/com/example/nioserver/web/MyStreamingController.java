package com.example.nioserver.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

import static java.util.stream.Stream.generate;
import static org.springframework.http.MediaType.APPLICATION_STREAM_JSON_VALUE;

@RestController
@RequestMapping("/")
public class MyStreamingController {

    private final AtomicInteger counter = new AtomicInteger();

    @GetMapping(value = "/stream", produces = APPLICATION_STREAM_JSON_VALUE)
    public Flux<MyNumber> getStream() {
        return Flux.fromStream(generate(counter::incrementAndGet))
                .map(MyNumber::new)
                .log()
                .delayElements(Duration.ofSeconds(1));
    }
}
