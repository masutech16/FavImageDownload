package FavImageDownload;

import com.google.api.services.drive.Drive;
import com.sun.xml.internal.ws.api.config.management.policy.ManagementAssertion;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

/**
 * Created by Masaki on 2017/09/26.
 */
public class Main {



    public static void main(String[] args) throws IOException {
        //twitterへのログイン
//        TwitterWrapper twitterWrapper = new TwitterWrapper();
//        List<String> ImageUrls = twitterWrapper.getImageURLsFromFav();
        IFileDownload fileUploader = new DriveWrapper();
        //TODO: ファイルの保存名をどうにかしたい
//        for(String url : ImageUrls) {
//            DownloadImage downloadImage = new DownloadImage()
//                    .setStoreFilePath(Settings.storeFilePath);
//            String storeUri = downloadImage.storeImage(url);
//            fileUploader.uploadImage(storeUri);
//        }
        fileUploader.uploadImage(new java.io.File(Settings.storeFilePath + "\\po.jpg"));
    }
}
