package uz.devfest.projectthree;

public class Question {
    private int question;
    private int answerA;
    private int answerB;
    private int answerC;
    private int answerD;
    private boolean answer;
    private int correctAnswer;

    public Question(int question, boolean answer) {
        this.question = question;
        this.answer = answer;
    }

    public Question(int answerA, int answerB, int answerC, int answerD) {
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
    }

    public Question(int correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public int getCorrectAnswer() {
        return correctAnswer;
    }

    public int getQuestion() {
        return question;
    }

    public int getAnswerA() {
        return answerA;
    }

    public int getAnswerB() {
        return answerB;
    }

    public int getAnswerC() {
        return answerC;
    }

    public int getAnswerD() {
        return answerD;
    }

    public boolean isAnswer() {
        return answer;
    }
}
