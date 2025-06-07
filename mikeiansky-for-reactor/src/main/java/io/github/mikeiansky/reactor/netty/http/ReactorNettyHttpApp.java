package io.github.mikeiansky.reactor.netty.http;

import reactor.core.publisher.Flux;
import reactor.netty.http.server.HttpServer;

/**
 * @author mike ian
 * @date 2025/6/7
 * @desc
 **///TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class ReactorNettyHttpApp {

    public static void main(String[] args) {

        HttpServer.create()
                .host("0.0.0.0")
                .port(60066)
                .handle((req, res) -> {
                    System.out.println("request uri is : " + req.uri());
                    return res.sendString(Flux.just("Hello, this is a Netty HTTP server!"));
                })
                .bindNow()
                .onDispose()
                .block();

        System.out.println("Reactor Netty HTTP server started on port 60066");

    }

}