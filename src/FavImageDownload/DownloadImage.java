package FavImageDownload;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Masaki on 2017/09/28.
 */
public class DownloadImage {

    private String storeFolderPath = Settings.storeFolderPath;

    public DownloadImage setStoreFilePath(String storeFilePath) {
        this.storeFolderPath = storeFilePath;
        return this;
    }

    public IImage storeImage(String urlText) {
        try {
            IImage image = createImageInstance(urlText);
            File file = image.getFile();
            BufferedImage rowImage = getBufferedImageFrom(urlText);
            ImageIO.write(rowImage, image.getExtension(), file);
            return image;
        } catch (MalformedURLException mue) {
            System.out.println("URLの形式が不正です");
            mue.printStackTrace();
            return null;
        } catch (Exception e) {
            System.out.println("書き込み中にエラーが発生しました");
            e.printStackTrace();
            return null;
        }
    }

    private String[] formatUrl(String url) {
        String[] fragments = url.split("/");
        String[] val = fragments[fragments.length -1].split("\\.");
        return val;
    }

    private IImage createImageInstance(String url) {
        String[] fileInfos = formatUrl(url);
        String title = fileInfos[0];
        String extension = fileInfos[1];
        String storeUri = storeFolderPath + "\\" + title + "." + extension;
        return new Image().setTitle(title)
                .setExtension(extension)
                .setFilePath(storeUri);
    }

    private BufferedImage getBufferedImageFrom(String urlText) throws Exception {
        URL url = new URL(urlText);
        URLConnection urlConnection = url.openConnection();
        return ImageIO.read(urlConnection.getInputStream());
    }

}
