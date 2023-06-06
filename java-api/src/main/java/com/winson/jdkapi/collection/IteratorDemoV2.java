package com.winson.jdkapi.collection;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author mike ian
 * @date 2023/6/6
 * @desc
 **/
public class IteratorDemoV2 {

    public static class MyIterator implements Iterable<Integer> {

        public List<Integer> data = new ArrayList<>();

        @NotNull
        @Override
        public Iterator<Integer> iterator() {
//            return new Iterator<Integer>() {
//
//                private int index = 0;
//
//                @Override
//                public boolean hasNext() {
//                    return data != null && data.size() > 0 && index < data.size();
//                }
//
//                @Override
//                public Integer next() {
//                    Integer result = data.get(index);
//                    index++;
//                    return result;
//                }
//            };

//            return Spliterators.iterator(new Spliterator<Integer>() {
//                private int index = 0;
//
//                @Override
//                public boolean tryAdvance(Consumer<? super Integer> action) {
//                    System.out.println("try advance");
//                    if (index >= data.size()) {
//                        return false;
//                    }
//                    Integer item = data.get(index);
//                    action.accept(item);
//                    index++;
//
////                    System.out.println(action);
//                    return true;
//                }
//
//                @Override
//                public Spliterator<Integer> trySplit() {
//                    System.out.println("try split");
//                    return null;
//                }
//
//                @Override
//                public long estimateSize() {
//                    System.out.println("estimate size");
//                    return 0;
//                }
//
//                @Override
//                public int characteristics() {
//                    System.out.println("characteristics");
//                    return 0;
//                }
//            });


            return Spliterators.iterator(Spliterators.spliterator(data, 0));
//            return Spliterators.iterator(data.spliterator());

        }

    }

    public static void main(String[] args) {
        MyIterator myIterator = new MyIterator();
        myIterator.data.add(1);
        myIterator.data.add(4);
        myIterator.data.add(3);
        myIterator.data.add(2);
        myIterator.data.add(5);

        for (Integer item : myIterator) {
            System.out.println(item);
        }

//        Iterator<Integer> iterator = myIterator.iterator();
//        while (iterator.hasNext()){
//            System.out.println(iterator.next());
//        }

//        myIterator.forEach(System.out::println);


    }

}
