package com.winson.jdkapi.collection;

import org.jetbrains.annotations.NotNull;

import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * @author winson
 * @date 2022/1/27
 **/
public class IterableDemo {

    // Spliterator
    // Spliterator
    // Iterable

    public static class FlagSon extends Flag{

        public FlagSon(String flag) {
            super(flag);
        }
    }

    public static class FlagParent{

    }

    public static class Flag extends FlagParent{
        public final String flag;

        public Flag(String flag) {
            this.flag = flag;
        }

        @Override
        public String toString() {
            return "Flag{" +
                    "flag='" + flag + '\'' +
                    '}';
        }
    }

    public static class MyCollection implements Iterable<Flag>{

        private List<Flag> flagList = new ArrayList<>();

        public void addFlag(Flag flag){
            flagList.add(flag);
        }

        public void removeFlag(Flag flag){
            flagList.remove(flag);
        }

        @NotNull
        @Override
        public Iterator<Flag> iterator() {
            System.out.println("create iterator");
//            return new Iterator<Flag>() {
//                private int cursor = 0;
//
//                @Override
//                public boolean hasNext() {
//                    return cursor < flagList.size();
//                }
//
//                @Override
//                public Flag next() {
//                    return flagList.get(cursor++);
//                }
//
//            };

//            return Collections.<Flag>emptyList().iterator();
//            return Collections.emptyIterator();
            return Spliterators.iterator(new MySpliterator(flagList));
        }

        public Stream<Flag> stream(){
            return StreamSupport.stream(new MySpliterator(flagList), false);
        }

        public Stream<Flag> stream2(){
            return StreamSupport.stream(flagList.spliterator(), false);
        }

    }

    public static class MySpliterator implements Spliterator<Flag> {

        private int cursor = 0;

        private List<Flag> flagList;

        public MySpliterator(List<Flag> flagList) {
            System.out.println("MySpliterator init ... ");
            this.flagList = flagList;
        }

        @Override
        public boolean tryAdvance(Consumer<? super Flag> action) {
            System.out.println("MySpliterator tryAdvance ");
            if(cursor >= flagList.size()){
                return false;
            }
            action.accept(flagList.get(cursor++));
            return true;
        }

        @Override
        public Spliterator<Flag> trySplit() {
            System.out.println("MySpliterator trySplit ");
            return null;
        }

        @Override
        public long estimateSize() {
            System.out.println("MySpliterator estimateSize ");
            return 0;
        }

        @Override
        public int characteristics() {
            System.out.println("MySpliterator characteristics ");
            return 0;
        }
    }

    public static void main(String[] args) {

        MyCollection myCollection = new MyCollection();
        myCollection.addFlag(new Flag("1"));
        myCollection.addFlag(new Flag("2"));
        myCollection.addFlag(new Flag("3"));



        System.out.println(" -- 1 --  ");
        for (Object o : myCollection) {
            System.out.println(o);
        }

        System.out.println(" -- 2 --  ");

        for (Object o : myCollection) {
            System.out.println(o);
        }

        System.out.println(" -- 3 --  ");
        myCollection.stream().forEach(System.out::println);

        System.out.println("app end ... ");

    }

}
