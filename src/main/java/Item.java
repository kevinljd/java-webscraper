public class Item {
    private String name;
    private String description;
    private String imagePath;

    public Item(String name, String description, String imagePath) {
        this.name = name;
        this.imagePath = imagePath;
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getImagePath() {
        return imagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public String exportCsvRow() {
        StringBuilder str = new StringBuilder();
        str.append(this.name);
        str.append(",");
        str.append("\"");
        str.append(this.description);
        str.append("\"");
        str.append(",");
        str.append(this.imagePath);
        str.append("\n");
        return str.toString();
    }


}
