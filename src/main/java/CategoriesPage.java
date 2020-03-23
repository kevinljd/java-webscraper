import java.io.IOException;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class CategoriesPage {

    public static ArrayList<String> getItemLinks(String url) {
        ArrayList<String> links = new ArrayList<>();
        try {
            Document doc = Jsoup.connect(url).get();

            Elements categoryGroups = doc.getElementsByClass("mw-category-group");
            for (Element group : categoryGroups) {
                Elements anchorTags = group.getElementsByTag("a");
                for (Element anchorTag : anchorTags) {
                    links.add(anchorTag.attr("href"));
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return links;
    }
}
