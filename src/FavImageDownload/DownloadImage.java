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

    //TODO: 拡張子にどう対応するかを考えておく
    //filePathを返すのはなんか変かもしれない
    //imageの情報を持つクラスを生成してしまうのはあり
    public String storeImage(String urlText) {
        try {
            String fileUri = storeFolderPath + "\\" + urlText;
            File file = new File(fileUri);
            BufferedImage rowImage = getBufferedImageFrom(urlText);
            ImageIO.write(rowImage, "jpg", file);
            return fileUri;
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
        URLConnection urlcon = url.openConnection();
        return ImageIO.read(urlcon.getInputStream());
    }

}
