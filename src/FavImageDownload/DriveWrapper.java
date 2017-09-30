package FavImageDownload;


import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.FileContent;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.drive.model.File;
import com.google.api.services.drive.model.FileList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * Created by Masaki on 2017/09/29.
 */
public class DriveWrapper {

    public DriveWrapper() throws IOException {
        HttpTransport httpTransport = new NetHttpTransport();
        JsonFactory jsonFactory = new JacksonFactory();
        GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
                httpTransport,
                jsonFactory,
                Settings.googleDriveClientID,
                Settings.googleDriveSecret,
                Arrays.asList(DriveScopes.DRIVE)
                ).setAccessType("online").setApprovalPrompt("auto").build();

        String url = flow.newAuthorizationUrl().setRedirectUri(Settings.googleRedirectUrl).build();
        System.out.println(url);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String code = br.readLine();

        GoogleTokenResponse response = flow.newTokenRequest(code).setRedirectUri(Settings.googleRedirectUrl).execute();
        GoogleCredential credential = new GoogleCredential().setFromTokenResponse(response);

        Drive service = new Drive.Builder(httpTransport,jsonFactory,credential).build();

        File body = new File();
        Date date = new Date();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy'-'MM'-'dd' 'HH':'mm':'ss");
        body.setTitle("DriveDocument" + sdf.format(date));
        body.setMimeType("text/plain");

        java.io.File fileContent = new java.io.File("C:\\Users\\Masaki\\Desktop\\test\\po.txt");
        FileContent mediaContent = new FileContent("text/plain", fileContent);
        File file = service.files().insert(body,mediaContent).execute();
        System.out.println("File ID:" +file.getId());

        List<File> result = new ArrayList<File>();
        Drive.Files.List request = service.files().list();

        do{
            try{
                FileList files = request.execute();
                result.addAll(files.getItems());
                request.setPageToken(files.getNextPageToken());
            } catch(Exception e) {
                System.out.println("An error occurred:" + e);
                request.setPageToken(null);
            }
        } while(request.getPageToken() != null && request.getPageToken().length() > 0);
        System.out.println(file.getTitle());

    }
}
