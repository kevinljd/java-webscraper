import java.io.File;
import java.util.ArrayList;

public class App {
    public static void main(String args[]) {
        String rootUrl = "https://townshiptale.gamepedia.com";
        String categoryPageUrl = "https://townshiptale.gamepedia.com/Category:Items";


        String imgExportDirectory = "imagesExport";
        String imagesDir = initialiseDirectoryFolder("imagesExport");
        imgExportDirectory = imgExportDirectory + "/";

        String csvExportDirectory = "csvExport";
        String csvDir = initialiseDirectoryFolder("csvExport");
        csvExportDirectory = csvExportDirectory + "/";

        ArrayList<String> itemLinks = CategoriesPage.getItemLinks(categoryPageUrl);
        itemLinks = UrlModifier.appendRootUrl(rootUrl, itemLinks);

        ArrayList<Item> items = new ArrayList<>();

        for (String link : itemLinks) {
            items.add(ItemPage.getItem(link, imgExportDirectory));
        }

        CsvExport.export(items, csvExportDirectory, "data");
        System.out.print("Number of items scraped and exported: ");
        System.out.println(items.size());

        System.out.print("Images exported to: ");
        System.out.println(imagesDir);

        System.out.print("CSV data exported to: ");
        System.out.println(csvDir);

    }

    private static String initialiseDirectoryFolder(String directoryName) {
        File directory = new File(directoryName);
        if (! directory.exists()){
            directory.mkdir();
        }
        return directory.getAbsolutePath();
    }


}