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
public class GetImage {


    //URL先の画像のbufferdImageを返す
    public static BufferedImage getBufferedImageFrom(String urlText) {
        URL url = null;
        try {
            url = new URL(urlText);
        } catch (MalformedURLException e) {
            System.out.println("URLの形式が不正です");
            return null;
        }
        try {
            URLConnection urlcon = url.openConnection();
            return ImageIO.read(urlcon.getInputStream());
        } catch (IOException e) {
            System.out.println( url.toString() + "との接続中に問題が発生しました");
            e.printStackTrace();
            return null;
        }
    }

    public static void storeImage(BufferedImage rowImage, String filepath) {
        storeImage(rowImage, new File(filepath));
    }

    //TODO: 拡張子にどう対応するかを考えておく
    public static void storeImage(BufferedImage rowImage, File file) {
        try {
            ImageIO.write(rowImage, "jpg", file);
        } catch (IOException e) {
            System.out.println("書き込み中にエラーが発生しました");
            e.printStackTrace();
        }
    }
}
