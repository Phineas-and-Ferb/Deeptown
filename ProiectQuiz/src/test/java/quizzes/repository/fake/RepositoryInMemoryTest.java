package quizzes.repository.fake;

import org.junit.jupiter.api.BeforeEach;
import quizzes.domain.QuizValidator;

import static org.junit.jupiter.api.Assertions.*;

class RepositoryInMemoryTest {
    private static RepositoryInMemoryTest repo;

    @BeforeEach
    void setUp() {
        QuizValidator validator = new QuizValidator();
        repo = new RepositoryInMemoryTest();
    }
}