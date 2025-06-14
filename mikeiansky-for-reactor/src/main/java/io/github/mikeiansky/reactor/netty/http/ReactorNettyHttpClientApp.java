package io.github.mikeiansky.reactor.netty.http;


import reactor.netty.http.client.HttpClient;

/**
 * @author mike ian
 * @date 2025/6/8
 * @desc
 **/
public class ReactorNettyHttpClientApp {

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
