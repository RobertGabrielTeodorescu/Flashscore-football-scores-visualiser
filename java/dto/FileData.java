package dto;

import javafx.beans.property.*;

//Model pentru tabele. Adica un fel de DTO.

public class FileData {

    public SimpleStringProperty fileName;
    public SimpleStringProperty path;
    /*  public SimpleLongProperty l;
      public SimpleIntegerProperty i;*/
    public SimpleStringProperty size;
    public SimpleStringProperty dateModified;

    public FileData(String fileName, String path, String size, String dateModified) {
        this.fileName = new SimpleStringProperty(fileName);
        this.path = new SimpleStringProperty(path);
        this.size = new SimpleStringProperty(size);
        this.dateModified = new SimpleStringProperty(dateModified);
    }

    public String getFileName() {
        return fileName.get();
    }

    public void setFileName(String fname) {
        fileName.set(fname);
    }

    public String getPath() {
        return path.get();
    }

    public void setPath(String fpath) {
        path.set(fpath);
    }

    public String getSize() {
        return size.get();
    }

    public void setSize(String fsize) {
        size.set(fsize);
    }

    public String getDateModified() {
        return dateModified.get();
    }

    public void setModified(String fmodified) {
        dateModified.set(fmodified);
    }
}