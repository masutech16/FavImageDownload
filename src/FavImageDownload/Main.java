package FavImageDownload;

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
        TwitterWrapper twitterWrapper = new TwitterWrapper();
        List<String> po = twitterWrapper.getImageURLs();
        //new DriveWrapper();
    }


}
