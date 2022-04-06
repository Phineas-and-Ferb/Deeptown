package service;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.InhousePart;
import model.OutsourcedPart;
import model.Part;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import repository.InventoryRepository;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class InventoryServiceTest {
    static InventoryService service;
    @Mock
    static InventoryRepository repoMock;

    private static ObservableList<Part> parts;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        service = new InventoryService(repoMock);
    }

    @BeforeAll
    static void beforeAll() {
        parts = FXCollections.observableArrayList();
        parts.add(new InhousePart(1, "name", 2.5, 10, 5, 20, 2));
        parts.add(new OutsourcedPart(2, "name", 3, 20, 10, 30, "comp name"));
    }

    @Tag("nu face nimic dar e o adnotare in plus :)")
    @ParameterizedTest
    @DisplayName("test valid add product")
    @MethodSource(value = "validAddProductECP")
    void testValidAddProductECP(String name, double price, int inStock, int min, int  max, ObservableList<Part> addParts) {
        when(repoMock.getAutoProductId()).thenReturn(1);
        Assertions.assertDoesNotThrow(() -> {
            service.addProduct(name, price, inStock, min, max, addParts);
        });
    }

    @TestFactory
    private static Stream<Arguments> validAddProductECP() {
        return Stream.of(
                Arguments.of("p1", 12, 5, 1, 10, parts),
                Arguments.of("p2", 5.75, 10, 10, 50, parts)
        );
    }

    @ParameterizedTest
    @DisplayName("test invalid add product")
    @MethodSource(value = "invalidAddProductECP")
    void testInvalidAddProductECP(String name, double price, int inStock, int min, int  max, ObservableList<Part> addParts) {
        when(repoMock.getAutoProductId()).thenReturn(1);
        Assertions.assertThrows(RuntimeException.class ,(Executable) () -> {
            service.addProduct(name, price, inStock, min, max, addParts);
        });
    }

    @TestFactory
    private static Stream<Arguments> invalidAddProductECP() {
        return Stream.of(
                Arguments.of("p1", 12, 5, 10, 20, parts),
                Arguments.of("p1", 12, 25, 10, 20, parts),
                Arguments.of("p2", 0.75, 13, 10, 50, parts),
                Arguments.of("p2", 9.75, 13, 20, 10, parts),
                Arguments.of("p3", 7.75, 10, 10, 50, FXCollections.observableArrayList())
        );
    }

    @ParameterizedTest
    @DisplayName("test valid add product")
    @MethodSource(value = "validAddProductBVA")
    void testValidAddProductBVA(String name, double price, int inStock, int min, int  max, ObservableList<Part> addParts) {
        when(repoMock.getAutoProductId()).thenReturn(1);
        Assertions.assertDoesNotThrow(() -> {
            service.addProduct(name, price, inStock, min, max, addParts);
        });
    }

    @TestFactory
    private static Stream<Arguments> validAddProductBVA() {
        return Stream.of(
                Arguments.of("p1", 12, 1, 1, 10, parts),
                Arguments.of("p1", 5.75, 50, 10, 50, parts),
                Arguments.of("p1", 5.51, 50, 10, 50, parts)
        );
    }

    @ParameterizedTest
    @DisplayName("test invalid add product")
    @MethodSource(value = "invalidAddProductBVA")
    void testInvalidAddProductBVA(String name, double price, int inStock, int min, int  max, ObservableList<Part> addParts) {
        when(repoMock.getAutoProductId()).thenReturn(1);
        Assertions.assertThrows(RuntimeException.class ,(Executable) () -> {
            service.addProduct(name, price, inStock, min, max, addParts);
        });
    }

    @TestFactory
    private static Stream<Arguments> invalidAddProductBVA() {
        return Stream.of(
                Arguments.of("p1", 5.5, 5, 10, 20, parts),
                Arguments.of("p1", 12, 10, 11, 10, parts),
                Arguments.of("p1", 12, 9, 10, 11, parts),
                Arguments.of("p2", 0.01, 13, 10, 50, parts),
                Arguments.of("p3", 7.75, 10, 10, 50, FXCollections.observableArrayList())
        );
    }
    
}