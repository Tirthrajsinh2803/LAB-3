package lab7;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class MainTest {

    // Test data
    private final List<Recipe> sampleRecipes = List.of(
            new Recipe(1, "Chicken Soup", "Homemade chicken soup", "Boil chicken", 4, 10, 30, 40),
            new Recipe(2, "Vegetable Salad", "Fresh garden salad", "Mix vegetables", 2, 5, 0, 5),
            new Recipe(3, "Beef Stew", "Hearty beef stew", "Slow cook beef", 6, 20, 120, 140)
    );

    @Test
    void findsMatchingRecipes() {
        DataService mockService = () -> sampleRecipes;
        List<Recipe> results = Main.searchRecipes("soup", mockService);
        assertEquals(1, results.size());
        assertEquals("Chicken Soup", results.getFirst().name());
    }

    @Test
    void caseInsensitiveSearch() {
        DataService mockService = () -> sampleRecipes;
        List<Recipe> results = Main.searchRecipes("CHICKEN", mockService);
        assertEquals(1, results.size());
    }

    @Test
    void searchesBothNameAndDescription() {
        DataService mockService = () -> List.of(
                new Recipe(1, "Pasta", "Creamy chicken pasta", "Cook pasta", 2, 10, 15, 25)
        );
        List<Recipe> results = Main.searchRecipes("chicken", mockService);
        assertEquals(1, results.size());
    }

    @Test
    void returnsEmptyForNoMatches() {
        DataService mockService = () -> sampleRecipes;
        List<Recipe> results = Main.searchRecipes("pizza", mockService);
        assertTrue(results.isEmpty());
    }

    @Test
    void handlesErrorsGracefully() {
        DataService failingService = () -> { throw new DataService.DataException(new Exception("DB error")); };
        List<Recipe> results = Main.searchRecipes("soup", failingService);
        assertTrue(results.isEmpty());
    }
}