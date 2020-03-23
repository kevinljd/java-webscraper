import java.util.ArrayList;

public class UrlModifier {
    public static ArrayList<String> appendRootUrl(String rootUrl, ArrayList<String> links) {
        ArrayList<String> newLinks = new ArrayList<>();

        for (String link : links) {
            StringBuilder str = new StringBuilder();
            str.append(rootUrl);
            str.append(link);
            newLinks.add(str.toString());
        }
        return newLinks;
    }
}
