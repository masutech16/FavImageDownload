package FavImageDownload;


import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;



/**
 * Created by Masaki on 2017/09/29.
 */
public class DriveWrapper {

    private Drive drive;

    public DriveWrapper() {
        HttpTransport httpTransport = new NetHttpTransport();
        JsonFactory jsonFactory = new JacksonFactory();
        TokenResponse response = new TokenResponse()
                .setAccessToken(Settings.googleAccessToken)
                .setRefreshToken(Settings.googleRefreshToken);
        GoogleCredential credential = new GoogleCredential.Builder().setTransport(httpTransport)
                .setJsonFactory(jsonFactory)
                .setClientSecrets(Settings.googleDriveClientID, Settings.googleDriveSecret)
                .build()
                .setFromTokenResponse(response);

        drive = new Drive.Builder(httpTransport, jsonFactory, credential).build();
    }
}
