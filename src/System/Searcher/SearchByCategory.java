package System.Searcher;
import System.IShowable;
import System.Enum.SearchCategory;

import java.util.List;

public class SearchByCategory extends ISearchStrategy {


    @Override
    List<IShowable> search(SearchCategory category, String str) {
        return getListByCategory(category);
    }
}
