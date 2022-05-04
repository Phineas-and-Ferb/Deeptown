package quizzes.service;

import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import quizzes.domain.Quiz;
import quizzes.repository.IRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class QuizServiceTest {
    private static QuizService service;
    @Mock
    private static IRepository repoMock;

    private static List<Quiz> list;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new QuizService(repoMock);
    }

    @BeforeAll
    static void beforeAll() {
        list = new ArrayList<>();
        list.add(new Quiz("q1", 10, "Easy", 7));
        list.add(new Quiz("q2", 15, "Medium", 3));
        list.add(new Quiz("q3", 9, "Easy", 9));
        list.add(new Quiz("q4", 5, "Hard", 2));
        list.add(new Quiz("q5", 20, "Medium", 9));
    }

    @Test
    @DisplayName("test allQuizzes")
    void allQuizzes() {
        when(repoMock.getAll()).thenReturn(list);
        Assertions.assertEquals(service.allQuizzes().size(), 5);
    }

    @Test
    @DisplayName("test maxScore")
    void maxScore() {
        when(repoMock.getAll()).thenReturn(list);
        Assertions.assertEquals(service.maxScore(), 9);
    }

    @Test
    @DisplayName("test maxScoreQuizList")
    void maxScoreQuizList() {
        when(repoMock.getAll()).thenReturn(list);
        Assertions.assertEquals(service.maxScoreQuizList().size(), 2);
    }

    @Test
    @DisplayName("test maxScoreQuizCounter")
    void maxScoreQuizCounter() {
        when(repoMock.getAll()).thenReturn(list);
        Assertions.assertEquals(service.maxScoreQuizCounter(), 2);
    }
}