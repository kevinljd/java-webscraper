import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class CsvExport {

    public static void export(ArrayList<Item> items, String csvExportDirectory, String csvName) {
        String headings = "Name,Description,Image Path\n";

        StringBuilder str = new StringBuilder();
        str.append(headings);

        for (Item item : items) {
            str.append(item.exportCsvRow());
        }

        try {
            File csvFile = new File(csvExportDirectory + csvName + ".csv");
            FileOutputStream out = (new FileOutputStream(csvFile));
            out.write(str.toString().getBytes());
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
