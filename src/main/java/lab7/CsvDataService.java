package lab7;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CsvDataService implements DataService {
    private static final String CSV_FILE_PATH = "recipes.csv";

    @Override
    public List<Recipe> getRecipes() throws DataService.DataException {
        try (Reader reader = Files.newBufferedReader(Paths.get(CSV_FILE_PATH))) {
            CsvToBean<Recipe> csvToBean = new CsvToBeanBuilder<Recipe>(reader)
                    .withType(Recipe.class)
                    .withIgnoreLeadingWhiteSpace(true)
                    .build();

            return csvToBean.parse();
        } catch (Exception e) {
            throw new DataService.DataException(e);
        }
    }
}