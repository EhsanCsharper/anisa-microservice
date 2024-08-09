package org.example;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class FallBackController {

    @RequestMapping("/circuitBreaker/fallback")
    public Mono<String> contactSupport() {
        return Mono.just("please contact administrator");
    }
}
