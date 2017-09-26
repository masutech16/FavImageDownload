package FavImageDownload;

import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

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
    //TODO: 画像の受け渡し方を調べる
    //ふぁぼしたツイートの中で最も新しいツイートについている画像を返す。
    //四枚付いている可能性もあるので配列で返したいところ。
    public String getRecentImages() {
        //開発中
        return "";
    }

    private ResponseList<Status> getFavTweet() {
        try {
            ResponseList<Status> favs = twitter.getFavorites();
            return favs;
        } catch(TwitterException te) {
            te.printStackTrace();
            System.exit(1);
        }
        return null;
    }
}
