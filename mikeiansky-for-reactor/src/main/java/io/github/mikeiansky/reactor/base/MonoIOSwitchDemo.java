package io.github.mikeiansky.reactor.base;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Mono;

import java.time.Duration;

/**
 *
 * @author mike ian
 * @date 2025/6/18
 * @desc
 **/
public class MonoIOSwitchDemo {

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class Tag {

        private int version;

        private String name;

    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Data
    public static class One {
        private String version;
        private Tag tag;
    }

    public static void printValue(String prefix , Object value){
        System.out.println(prefix + ", Thread name : " + Thread.currentThread().getName() + ", value : " + value);
    }

    public static void main(String[] args) {

        Tag tag = new Tag();
        tag.version = 1;
        tag.name = "tag-001";

        One one = new One();
        one.setTag(tag);
        one.setVersion("0.0.1");

        // 这里
        Mono.just(one)
                .flatMap(value->{
                    printValue("first flatMap", value);
                    return Mono.delay(Duration.ofSeconds(1))
                            .then(Mono.just(value).map(v->{
                                v.setTag(new Tag(22, "222"));
                                return v;
                            }));
                })
                .doOnNext(value -> {
                        printValue("complete", value);
                })
                .block();

    }

}
