package System.Searcher;
import System.Enum.SearchCategory;
import System.IShowable;

import java.util.LinkedList;
import java.util.List;

public class SearchByKeyWord extends ISearchStrategy {

    @Override
    public List<IShowable> search(SearchCategory category, String str) {
        List<IShowable> list= getListByCategory(category);
        List<IShowable> searchList= new LinkedList<IShowable>();
        for (IShowable obj: list){
            if(obj.getDetails().contains(str)){
                searchList.add(obj);
            }
        }
        return searchList;
    }
}