package FavImageDownload;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Masaki on 2017/09/26.
 */
public class TwitterWrapper {

    private Twitter twitter;

    public TwitterWrapper() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setOAuthConsumerKey(Settings.twitterConsumerToken);
        cb.setOAuthConsumerSecret(Settings.twitterConsumerSecret);
        cb.setOAuthAccessToken(Settings.twitterAccessToken);
        cb.setOAuthAccessTokenSecret(Settings.twitterAccessSecret);
        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();
    }

    //多めに取得するようにする
    private List<Status> getFavTweetWithImages() {
        ResponseList<Status> favs = null;
        try {
            favs = twitter.getFavorites();
        } catch(TwitterException te) {
            te.printStackTrace();
            System.exit(1);
        }
        List<Status> statuses = new ArrayList<Status>();
        for(Status tweet : favs) {
            System.out.println(tweet.getText());
            if(tweet.getMediaEntities().length != 0) {
                statuses.add(tweet);
            }
        }
        return statuses;
    }


    //画像データの配列を返すメソッド
    //形式が不明なのでとりあえずvoidで置いておきます。今度調べます
    public void getImages() {
        List<Status> imageStatuses = getFavTweetWithImages();
        //Statusの中から画像をとり出して送る。
    }
}
