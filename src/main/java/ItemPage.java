import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ItemPage {
    public static Item getItem(String url, String exportDirectory) {
        String imgUrl = getImagePath(url);

        String name = getName(url);
        String description = getDescription(url);
        String imgAbsolutePath = null;
        if (imgUrl != null) {
            imgAbsolutePath = exportImg(name, imgUrl, exportDirectory);
        }
        System.out.print("Retrieved item: ");
        System.out.println(name);
        return new Item(name, description, imgAbsolutePath);
    }

    private static String getName(String url) {
        String name = null;
        try {
            Document doc = Jsoup.connect(url).get();

            Element heading = doc.getElementById("firstHeading");
            name = heading.text();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return name;
    }

    private static String getDescription(String url) {
        String description = null;
        try {
            Document doc = Jsoup.connect(url).get();

            Element heading = doc.getElementById("mw-content-text");
            Elements paragraph = heading.getElementsByTag("p");
            Element relevantText = paragraph.get(0);
            description = relevantText.text();
            description = description
                    .replace("\n", "")
                    .replace("\r", "")
                    .replace("\"", "'");

        } catch (IOException e) {
            e.printStackTrace();
        }
        return description;
    }

    private static String exportImg(String name, String url, String exportDirectory) {
        byte[] bytes = fetchImageBytes(url);
        String absoluteFilePath = null;

        String imgFileType = findImgFileType(url);
        try {
            File imageFile = new File(exportDirectory + name + imgFileType);
            FileOutputStream out = (new FileOutputStream(imageFile));
            out.write(bytes);
            out.close();
            absoluteFilePath = imageFile.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return absoluteFilePath;
    }

    private static String findImgFileType(String imgUrl) {
        String regexPattern = "(\\.[a-z]*\\?)";

        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(imgUrl);

        String imgType = null;
        while(matcher.find()) {
            imgType = matcher.group(1);
        }
        imgType = imgType.substring(1, imgType.length() - 1);

        StringBuilder str = new StringBuilder();
        str.append(".");
        str.append(imgType);

        return str.toString();

    }

    private static byte[] fetchImageBytes(String url) {
        byte[] bytes = null;
        try {
            bytes = Jsoup.connect(url).ignoreContentType(true).execute().bodyAsBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bytes;
    }

    private static String getImagePath(String url) {
        String imgPath = null;
        try {
            Document doc = Jsoup.connect(url).get();

            Elements infoboxes = doc.getElementsByClass("infobox");
            if (infoboxes.size() > 0) {
                Element mainInfoBox = infoboxes.get(0);
                Element imageLink = mainInfoBox.getElementsByTag("img").get(0);

                imgPath = imageLink.attr("src");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imgPath;
    }

}
