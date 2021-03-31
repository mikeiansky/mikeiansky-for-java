package com.winson.compute;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author winson
 * @date 2021/3/30
 **/
public class TestCompare {

    public static class Score{
        public int score;
        public String time;

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

        // 对比字符串
        String one = "2020-03-03 04:00:00";
        String two = "2020-03-03 03:00:00";
        System.out.println(one.compareTo(two));

        // 排序集合
        List<Score> scoreList = new ArrayList<>();
        Score score1 = new Score();
        score1.setScore(4);
        score1.setTime("2020-05");
        scoreList.add(score1);

        Score score2 = new Score();
        score2.setScore(7);
        score2.setTime("2020-02");
        scoreList.add(score2);

        Score score3 = new Score();
        score3.setScore(4);
        score3.setTime("2020-09");
        scoreList.add(score3);

        Score score4 = new Score();
        score4.setScore(8);
        score4.setTime("2020-07");
        scoreList.add(score4);

        Score score5 = new Score();
        score5.setScore(4);
        score5.setTime("2020-07");
        scoreList.add(score5);

        System.out.println(scoreList);

        List<Score> compareScoreList = scoreList.stream().sorted(
                Comparator.comparing(Score::getScore, Comparator.reverseOrder())
                .thenComparing(Score::getTime, Comparator.reverseOrder())
        ).collect(Collectors.toList());
        System.out.println(compareScoreList);
    }

}
