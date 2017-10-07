package FavImageDownload;

import java.io.File;

public interface IImage {

    public boolean exist();
    public String getTitle();
    public String getExtension();
    public File getFile();
}
