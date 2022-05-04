package quizzes.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuizTest {
    private static Quiz q;

    @BeforeEach
    void setUp() {
        q = new Quiz("1", 7, "Medium", 5);
    }

    @Test
    @DisplayName("tests getters")
    void testsGetters() {
        Assertions.assertEquals(q.getId(), "1");
        Assertions.assertEquals(q.getNoQuestions(), 7);
        Assertions.assertEquals(q.getDifficulty(), Difficulty.Medium);
        Assertions.assertEquals(q.getCorrectAnswers(), 5);
    }
    
    @Test
    @DisplayName("tests setters")
    void testsSetters() {
        q.setId("2");
        Assertions.assertEquals(q.getId(), "2");
        q.setNoQuestions(10);
        Assertions.assertEquals(q.getNoQuestions(), 10);
        q.setDifficulty(Difficulty.Easy);
        Assertions.assertEquals(q.getDifficulty(), Difficulty.Easy);
        q.setCorrectAnswers(9);
        Assertions.assertEquals(q.getCorrectAnswers(), 9);
    }
}