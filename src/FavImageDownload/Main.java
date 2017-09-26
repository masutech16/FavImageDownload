package FavImageDownload;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Created by Masaki on 2017/09/26.
 */
public class Main {



    public static void main(String[] args) {
        //twitterへのログイン
        Twitter twitter;
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setOAuthConsumerKey(Settings.twitterConsumerToken);
        cb.setOAuthConsumerSecret(Settings.twitterConsumerSecret);
        cb.setOAuthAccessToken(Settings.twitterAccessToken);
        cb.setOAuthAccessTokenSecret(Settings.twitterAccessSecret);
        TwitterFactory tf = new TwitterFactory(cb.build());
        twitter = tf.getInstance();

        //Test
        try {
            twitter.updateStatus("test");
        } catch(TwitterException te) {
            te.printStackTrace();
            System.exit(1);
        }
    }
}
