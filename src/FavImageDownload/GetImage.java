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

    private URL url;
    private BufferedImage bi = null;
    private URLConnection urlcon;

    public GetImage(String url) {
        try {
            this.url = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    //所持しているURL先の画像を所定の場所に保存する
    //urlを引数にとって静的メソッド化するのはあり
    private void convertToImage() {
        try {
            urlcon = url.openConnection();
            bi = ImageIO.read(urlcon.getInputStream());
            File saveFile = new File(Settings.storeFilePath);
            ImageIO.write(bi,"jpg",saveFile);
        } catch (IOException e) {
            System.out.println( url.toString() + "との接続中に問題が発生しました");
        }
    }
}
