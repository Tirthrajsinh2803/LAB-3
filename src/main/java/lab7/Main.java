package lab7;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    /**
     * Get all recipes that can be made in 15 minutes or less
     * @param dataService a service that provides access to recipe data
     * @return a list of quick recipes
     */
    public static List<Recipe> getQuickRecipes(DataService dataService) {
        try {
            var recipes = dataService.getRecipes();
            return recipes.stream().filter(r -> r.totalTime() <= 15).toList();
        } catch(Exception e) {
            logger.error("Error while getting quick recipes: {}", e.getMessage());
            logger.debug("Stack trace: {}", Arrays.toString(e.getStackTrace()));
            return List.of();
        }
    }

    /**
     * Searches recipes containing the term in name/description (case-insensitive)
     * @param searchTerm term to search for
     * @param dataService data source implementation
     * @return List of matching recipes (empty if no matches or error occurs)
     */
    public static List<Recipe> searchRecipes(String searchTerm, DataService dataService) {
        try {
            return dataService.getRecipes().stream()
                    .filter(recipe ->
                            recipe.name().toLowerCase().contains(searchTerm.toLowerCase()) ||
                                    recipe.description().toLowerCase().contains(searchTerm.toLowerCase()))
                    .toList();
        } catch (DataService.DataException e) {
            logger.error("Data access error in search: {}", e.getMessage());
            return List.of();
        } catch (Exception e) {
            logger.error("Unexpected search error: {}", e.getMessage());
            return List.of();
        }
    }

    public static void main(String[] args) {
        // Determine data source from command line
        DataService dataService = selectDataSource(args);

        // Demo quick recipes
        System.out.println("=== Quick Recipes (15 mains or less) ===");
        getQuickRecipes(dataService).forEach(System.out::println);

        // Demo search functionality
        demoSearchFunctionality(dataService);
    }

    private static DataService selectDataSource(String[] args) {
        if (args.length > 0 && args[0].equalsIgnoreCase("csv")) {
            System.out.println("\nUsing CSV data source");
            return new CsvDataService();
        }
        System.out.println("\nUsing SQLite data source (default)");
        return new SqliteDataService();
    }

    private static void demoSearchFunctionality(DataService dataService) {
        System.out.println("\n=== Recipe Search Demo ===");
        String[] testTerms = {"soup", "chicken", "salad", "pasta"};

        for (String term : testTerms) {
            System.out.println("\nSearching for: '" + term + "'");
            List<Recipe> results = searchRecipes(term, dataService);

            if (results.isEmpty()) {
                System.out.println("No recipes found containing '" + term + "'");
            } else {
                System.out.println("Found " + results.size() + " recipe(s):");
                results.forEach(recipe ->
                        System.out.printf("- %s: %s (Total time: %d mains)%n",
                                recipe.name(),
                                recipe.description(),
                                recipe.totalTime()));
            }
        }
    }
}