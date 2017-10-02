package FavImageDownload;


import com.google.api.client.auth.oauth2.TokenResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;

import java.io.IOException;


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
                .setRefreshToken(Settings.googleRefreshToken)
                .setExpiresInSeconds(3599l)
                .setTokenType("Bearer");
        GoogleCredential credential = new GoogleCredential.Builder().setTransport(httpTransport)
                .setJsonFactory(jsonFactory)
                .setClientSecrets(Settings.googleDriveClientID, Settings.googleDriveSecret)
                .build()
                .setFromTokenResponse(response);

        drive = new Drive.Builder(httpTransport, jsonFactory, credential).build();
    }

    public void storeImage(java.io.File file) {
        File body = new File().setTitle("test");
        FileContent mediaContent = new FileContent(null,file);
        try {
            drive.files().insert(body,mediaContent).execute();
        } catch (Exception e) {
            System.out.println("driveの書き込み中にエラーが発生しました");
            e.printStackTrace();
        }

    }
}
