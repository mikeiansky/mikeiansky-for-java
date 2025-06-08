package io.github.mikeiansky.java.base.jdk.generic;

/**
 * @author mike ian
 * @date 2025/6/8
 * @desc 递归泛型示例
 **/
public class RecursiveGenericDemo {

    public interface One<T extends One<T, C>, C> {

        void config(C c);

        T create();

    }

    public class Two implements One<Two, String> {

        private String config;

        @Override
        public void config(String c) {
            this.config = c;
        }

        @Override
        public Two create() {
            return new Two();
        }

        @Override
        public String toString() {
            return "Two{" +
                    "config='" + config + '\'' +
                    '}';
        }
    }

    public class Three implements One<Two, String> {

        @Override
        public void config(String s) {

        }

        @Override
        public Two create() {
            return null;
        }

    }


    public static void main(String[] args) {

    }

}
