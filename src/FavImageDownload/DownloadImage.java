package FavImageDownload;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Masaki on 2017/09/28.
 */
public class DownloadImage {

    private String storeFolderPath = Settings.storeFilePath;

    public DownloadImage setStoreFilePath(String storeFilePath) {
        this.storeFolderPath = storeFilePath;
        return this;
    }

    public Image storeImage(String urlText) {
        try {
            Image image = new Image().setInfoFromUrl(urlText);
            String storeUri = Settings.storeFilePath + "\\" + image.getTitle() + "." + image.getExtension();
            image.setFilePath(storeUri);
            File file = new File(storeUri);
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

    private static BufferedImage getBufferedImageFrom(String urlText) throws Exception {
        URL url = new URL(urlText);
        URLConnection urlConnection = url.openConnection();
        return ImageIO.read(urlConnection.getInputStream());
    }

}
