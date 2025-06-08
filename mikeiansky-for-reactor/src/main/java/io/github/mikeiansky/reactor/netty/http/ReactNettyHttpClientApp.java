package io.github.mikeiansky.reactor.netty.http;


import org.reactivestreams.Publisher;
import reactor.netty.ByteBufFlux;
import reactor.netty.http.client.HttpClient;
import reactor.netty.http.client.HttpClientResponse;

import java.util.function.BiFunction;

/**
 * @author mike ian
 * @date 2025/6/8
 * @desc
 **/
public class ReactNettyHttpClientApp {

    public static void main(String[] args) throws InterruptedException {

        HttpClient.create()
                .baseUrl("http://localhost:60066")
                .get()
                .responseSingle((resp, content) ->
                        content.asString().map(body -> {
                            System.out.println("complete ... " + resp);
                            System.out.println(resp.status().code());
                            System.out.println(resp.currentContextView().size());
                            System.out.println("response body: " + body);
                            return body;
                        })
                )
                .block();
    }

}
