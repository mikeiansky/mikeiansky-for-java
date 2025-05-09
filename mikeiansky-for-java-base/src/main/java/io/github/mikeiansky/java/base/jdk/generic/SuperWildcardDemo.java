package io.github.mikeiansky.java.base.jdk.generic;

/**
 * @author mike ian
 * @date 2025/5/9
 * @desc
 **/
public class SuperWildcardDemo {

    public static class Tag {
    }

    public static class TagGrandFather extends Tag {
    }

    public static class TagFather extends TagGrandFather {
    }

    public static class TagSon extends TagFather {
    }

    public static class BindTag<T> {

        public void useTag(T t) {
            System.out.println("use tag : " + t);
        }

    }

    public static class CreateTag<T> {

        private T t;

        public CreateTag(T t) {
            this.t = t;
        }

        public T getTag() {
            return t;
        }
    }

    public static void useSuperWildcard(BindTag<? super TagGrandFather> superBind,
                                        CreateTag<? extends TagFather> extendParamCreate) {
        superBind.useTag(extendParamCreate.getTag());
    }

    public static void main(String[] args) {
        BindTag<Tag> superBind = new BindTag<Tag>();
        CreateTag<TagFather> createTag = new CreateTag<TagFather>(new TagSon());
        useSuperWildcard(superBind, createTag);
    }

}
