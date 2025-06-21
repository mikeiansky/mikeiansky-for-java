package io.github.mikeiansky.reactor.base;

import reactor.core.publisher.Mono;

import java.util.function.Consumer;

/**
 * @author mike ian
 * @date 2025/6/14
 * @desc
 **/
public class MonoCreateDemo {

    public static class Tag {
        private final String name;

        public Tag(String name) {
            this.name = name;
        }
    }

    public static void main(String[] args) {

        Mono<Tag> mono = Mono.create(sink -> {
            System.out.println("create active ... ");
            sink.success(new Tag("hello"));
//            sink.error(new RuntimeException("none"));
        });

        mono.subscribe(new Consumer<Tag>() {
            @Override
            public void accept(Tag tag) {
                System.out.println("success : " + tag);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) {
                System.out.println("error : " + throwable);

            }
        }, new Runnable() {
            @Override
            public void run() {
                System.out.println("complete ...");
            }
        });

    }

}
