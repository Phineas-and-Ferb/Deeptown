package repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import service.InventoryService;

import static org.junit.jupiter.api.Assertions.*;

class InventoryRepositoryTest {
    static InventoryRepository repo;

    @BeforeEach
    void setUp() {
        repo = new InventoryRepository();
    }

    @Test
    void lookupProduct() {
    }
}