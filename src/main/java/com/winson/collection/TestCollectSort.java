package com.winson.collection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author winson
 * @date 2021/3/27
 **/
public class TestCollectSort {

    public static class Score {

        private int score;
        private String time;

        public int getScore() {
            return score;
        }

        public void setScore(int score) {
            this.score = score;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        @Override
        public String toString() {
            return "Score{" +
                    "score=" + score +
                    ", time='" + time + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        System.out.println("---------- 1 ----------");

        List<Score> scoreList = new ArrayList<>();

        Score score1 = new Score();
        score1.setScore(15);
        score1.setTime("2021-03-03 12:23:34");
        scoreList.add(score1);

        Score score2 = new Score();
        score2.setScore(12);
        score2.setTime("2021-03-02 12:23:34");
        scoreList.add(score2);

        Score score3 = new Score();
        score3.setScore(19);
        score3.setTime("2021-03-03 15:23:34");
        scoreList.add(score3);

        Score score4 = new Score();
        score4.setScore(15);
        score4.setTime("2021-03-04 12:23:34");
        scoreList.add(score4);

        Score score5 = new Score();
        score5.setScore(14);
        score5.setTime("2021-03-13 12:23:34");
        scoreList.add(score5);

        scoreList.sort(Comparator.comparing(Score::getScore, Comparator.reverseOrder())
                .thenComparing(Score::getTime, Comparator.reverseOrder()));

        System.out.println(scoreList);
        System.out.println("---------- 2 ----------");
    }

}
