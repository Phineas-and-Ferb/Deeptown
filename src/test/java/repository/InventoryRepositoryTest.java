package repository;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.InhousePart;
import model.OutsourcedPart;
import model.Part;
import model.Product;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.parallel.ResourceLock;
import org.mockito.MockitoAnnotations;
import service.InventoryService;

import java.util.Iterator;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class InventoryRepositoryTest {
    static InventoryRepository repo;

    @BeforeAll
    static void beforeAll() {
        repo = new InventoryRepository();
    }

    @Test
    @ResourceLock(value = "repo")
    @DisplayName("tc 01")
    void TC01() {
        Product p = repo.lookupProduct("prod");
        Assertions.assertEquals(p.getProductId(), 2);
    }

    @Test
    @ResourceLock(value = "repo")
    @DisplayName("tc 02")
    void TC02() {
        Product p = repo.lookupProduct("1");
        Assertions.assertEquals(p.getProductId(), 1);
    }

    @Test
    @ResourceLock(value = "repo")
    @DisplayName("tc 03")
    void TC03() {
        Product p = repo.lookupProduct("de negasit");
        assertNull(p);
    }

    @Test
    @ResourceLock(value = "repo")
    @DisplayName("tc 04")
    void TC04() {
        for (Iterator<Product> iterator = repo.getAllProducts().iterator(); iterator.hasNext(); ) {
            Product value = iterator.next();
            iterator.remove();
        }
        Product p = repo.lookupProduct("1");
        Assertions.assertEquals(0, p.getProductId());
    }

    @Test
    @ResourceLock(value = "repo")
    @DisplayName("tc 05")
    void TC05() {
        for (Iterator<Product> iterator = repo.getAllProducts().iterator(); iterator.hasNext(); ) {
            Product value = iterator.next();
            iterator.remove();
        }
        Product p = repo.lookupProduct("1");
        Assertions.assertEquals(0, p.getProductId());
    }

}