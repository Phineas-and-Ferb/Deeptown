package quizzes.service;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import quizzes.domain.Quiz;
import quizzes.domain.QuizValidator;
import quizzes.repository.IRepository;
import quizzes.repository.RepositoryException;
import quizzes.repository.fake.RepositoryInMemory;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.notNull;
import static org.mockito.Mockito.when;

class RepoIntegrationTest {
    private static QuizService service;
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
        service = new QuizService(repo);
    }

//        list.add(new Quiz("q1", 10, "Easy", 7));
//        list.add(new Quiz("q2", 15, "Medium", 3));
//        list.add(new Quiz("q3", 9, "Easy", 9));
//        list.add(new Quiz("q4", 5, "Hard", 2));
//        list.add(new Quiz("q5", 20, "Medium", 9));

    @Test
    @DisplayName("test allQuizzes")
    void allQuizzes() {
        Assertions.assertNotNull(repo);
        List<Quiz> l = service.allQuizzes();
        Assertions.assertEquals(l.size(), repo.size());
        for (int i=0; i<repo.size(); i++) {
            Assertions.assertEquals(l.get(0).getClass(), Quiz.class);
        }
    }

    @Test
    @DisplayName("test addQuiz")
    void addQuiz() {
        Assertions.assertNotNull(repo);
        int size = repo.size();
        Assertions.assertDoesNotThrow(() -> { service.addQuiz(quiz); });
        when(validator.validate(quiz)).thenReturn(new ArrayList<String>() {
            { add("error"); }
        });
        Assertions.assertThrows(RepositoryException.class, () -> { service.addQuiz(quiz); });
        Assertions.assertEquals(repo.size(), size+1);
    }
}