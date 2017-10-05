package FavImageDownload;

import java.io.IOException;

/**
 * Created by Masaki on 2017/09/26.
 */
public class Main {



    public static void main(String[] args) throws IOException {
        //twitterへのログイン
//        TwitterWrapper twitterWrapper = new TwitterWrapper();
//        List<String> ImageUrls = twitterWrapper.getImageURLsFromFav();
        IFileUpload fileUploader = new DriveWrapper();
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
