package FavImageDownload;

import java.io.File;

/**
 * Created by Masaki on 2017/10/06.
 */
public class Image {
    private String title;
    private String extension;
    private File file;

    public Image setTitle(String title) {
        this.title = title;
        return this;
    }

    public Image setExtension(String extension) {
        this.extension = extension;
        return this;
    }

    public Image setInfoFromUrl(String url) {
        String[] str = url.split("/");
        String[] po = str[str.length -1].split("\\.");
        return this.setTitle(po[0])
                .setExtension(po[1]);
    }

    public Image setFilePath(String path) {
        this.file = new File(path);
        return this;
    }

    public String getTitle() {
        return title;
    }

    public  String getExtension() {
        return extension;
    }

    public File getFile() {
        return file;
    }
}