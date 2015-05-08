package com.example.ryan.quizdroid;

import java.util.Random;

/**
 * Created by Ryan on 4/27/2015.
 */
public class Question {

    public String question;
    public String possible1;
    public String possible2;
    public String possible3;
    public String answer;
    public int correct;
    public String[] answers = new String[4];

    public Question(String question, String possible1, String possible2, String possible3, String answer) {

        this.question = question;
        this.possible1 = possible1;
        this.possible2 = possible2;
        this.possible3 = possible3;
        this.answer = answer;
        Random random = new Random();
        this.correct = random.nextInt(4);
        answers[correct] = answer;
        for (int i = 0; i < answers.length; i++) {
            if (correct == 0) {
                answers[1] = possible1;
                answers[2] = possible2;
                answers[3] = possible3;
            } else if (correct == 1) {
                answers[0] = possible1;
                answers[2] = possible2;
                answers[3] = possible3;
            } else if (correct == 2) {
                answers[0] = possible1;
                answers[1] = possible2;
                answers[3] = possible3;
            } else {
                answers[0] = possible1;
                answers[1] = possible2;
                answers[2] = possible3;
            }
        }
    }
}
