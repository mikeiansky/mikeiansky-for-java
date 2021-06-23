package com.winson.juc.version_3;

import com.winson.reflect.Make;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author winson
 * @date 2021/6/23
 **/
public class ForJoinStatisticsDemo {

    public static class WordComputeTask extends RecursiveTask<Map<String, Integer>> {

        private String[] wordLines;

        private int start;

        private int end;

        public WordComputeTask(String[] wordLines, int start, int end) {
            this.wordLines = wordLines;
            this.start = start;
            this.end = end;
        }

        private Map<String, Integer> merge(Map<String, Integer> m1, Map<String, Integer> m2) {
            for (String m1Key : m1.keySet()) {
                Integer count = m1.get(m1Key);
                Integer c2 = m2.get(m1Key);
                if (c2 != null) {
                    m2.put(m1Key, count + c2);
                } else {
                    m2.put(m1Key, count);
                }
            }
            return m2;
        }

        private Map<String, Integer> calc(String line) {
            String[] words = line.trim().split(" ");
            Map<String, Integer> wordCountMap = new HashMap<>();

            for (String word : words) {
                Integer oldCount = wordCountMap.get(word);
                int oc = oldCount != null ? oldCount : 0;
                wordCountMap.put(word, oc + 1);
            }

            return wordCountMap;
        }

        @Override
        protected Map<String, Integer> compute() {
            if ((end - start) <= 1) {
                // compute
                return calc(wordLines[start]);
            }

            int mid = (start + end) / 2;

            WordComputeTask t1 = new WordComputeTask(wordLines, start, mid);
            t1.fork();
            WordComputeTask t2 = new WordComputeTask(wordLines, mid, end);

            return merge(t2.compute(), t1.join());
        }

    }


    public static void main(String[] args) {
        String[] lines = new String[]{
                "hello ciwei",
                "shenzhen winson",
                "hello winson",
                "hello shenzhen"
        };
        ForkJoinPool forkJoinPool = new ForkJoinPool(5);
        WordComputeTask task = new WordComputeTask(lines, 0, lines.length);
        Map<String, Integer> result = forkJoinPool.invoke(task);
        System.out.println(result);
    }

}
