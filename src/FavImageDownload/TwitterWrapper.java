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
        ResponseList<Status> favoritedTweets = null;
        try {
            favoritedTweets = twitter.getFavorites();
        } catch(TwitterException te) {
            te.printStackTrace();
            System.exit(1);
        }
        List<Status> statuses = new ArrayList<Status>();
        for(Status tweet : favoritedTweets) {
            if(tweet.getMediaEntities().length != 0) {
                statuses.add(tweet);
            }
        }
        return statuses;
    }

    //最新のツイートがこれ欲しいとかだったら、直近のRTした画像を保存する。


    //画像データがあるURLをListで返します
    public List<String> getImageURLsFromFav() {
        List<Status> imageStatuses = getFavTweetWithImages();
        //Statusの中から画像をとり出して送る。
        List<String> urls = new ArrayList<String>();
        for(Status tw : imageStatuses) {
            MediaEntity[] rowImages = tw.getMediaEntities();
            for(MediaEntity me : rowImages) {
               urls.add(me.getMediaURL());
            }
        }
        return urls;
    }
}
