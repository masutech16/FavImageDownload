package FavImageDownload;

import java.util.List;

/**
 * Created by Masaki on 2017/09/26.
 */
public class Main {
    public static void main(String[] args) {
        //twitterへのログイン
        TwitterWrapper twitterWrapper = new TwitterWrapper();
        List<String> ImageUrls = twitterWrapper.getImageURLsFromFav();
        IFileUpload fileUploader = new DriveWrapper();
        for(String url : ImageUrls) {
            DownloadImage downloadImage = new DownloadImage()
                    .setStoreFilePath(Settings.storeFolderPath);
            IImage image = downloadImage.storeImage(url);
            fileUploader.uploadImage(image);
        }
    }
}
