package quizzes.repository.fake;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import quizzes.domain.Quiz;
import quizzes.domain.QuizValidator;
import quizzes.repository.IRepository;
import quizzes.repository.RepositoryException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.when;

class RepositoryInMemoryTest {
    private static IRepository repo;
    @Mock
    private static Quiz quiz;
    @Mock
    private static QuizValidator validator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        when(validator.validate((Quiz)notNull())).thenReturn(new ArrayList<String>());
        try {
            repo = new RepositoryInMemory(validator);
        } catch (RepositoryException ignored) {}
    }

    @Test
    @DisplayName("test getAll")
    void getAll() {
        Assertions.assertNotNull(repo);
        List<Quiz> l = repo.getAll();
        Assertions.assertEquals(l.size(), repo.size());
        if(l.size() != 0) {
            Assertions.assertEquals(l.get(0).getClass(), Quiz.class);
        }
    }

    @Test
    @DisplayName("test add")
    void add() {
        Assertions.assertDoesNotThrow(() -> { repo.add(quiz); });
        when(validator.validate(quiz)).thenReturn(new ArrayList<String>() {
            { add("error"); }
        });
        Assertions.assertThrows(RepositoryException.class, () -> { repo.add(quiz); });
    }
}