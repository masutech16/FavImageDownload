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
import com.google.api.services.drive.model.FileList;
import com.google.api.services.drive.model.ParentReference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Masaki on 2017/09/29.
 */
public class DriveWrapper implements IFileUpload {

    private Drive drive;
    private final static String FOLDER = "application/vnd.google-apps.folder";
    private final static String PHOTO = "image/jpeg";
    private final static String FOLDER_NAME = "FavImages";


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

        drive = new Drive.Builder(httpTransport, jsonFactory, credential)
                .setApplicationName("FavImageDownload").build();
    }

    @Override
    public void uploadImage(IImage image) {
        if (!(image.exist())) {
            System.out.println("指定されたファイルは存在しません");
            return;
        }
        try {
            File parent = getParentDirectory(FOLDER_NAME);
            File metaData = new File().setTitle(image.getTitle())
                    .setMimeType(PHOTO)
                    .setParents(Arrays.asList(new ParentReference().setId(parent.getId())));
            FileContent mediaContent = new FileContent(null, image.getFile());
            drive.files().insert(metaData, mediaContent).execute();
        } catch (Exception e) {
            System.out.println("driveの書き込み中にエラーが発生しました");
            e.printStackTrace();
        }
    }

    //root直下のディレクトリを探します
    //将来的には任意のパスをとれるようにしたい
    private File getParentDirectory(String parentPath) throws IOException {
        List<File> children = searchAllFiles(drive.files().list().setQ("trashed = false"));
        for (File file : children) {
            if (file.getTitle().equals(parentPath)) return file;
        }
        File parent = new File()
                .setTitle(parentPath)
                .setMimeType(FOLDER);
        parent = drive.files().insert(parent).execute();
        return parent;
    }

    private List<File> searchAllFiles(Drive.Files.List request) throws IOException {
        List<File> result = new ArrayList<>();

        do {
            FileList files = request.execute();
            result.addAll(files.getItems());
            request.setPageToken(files.getNextPageToken());
        }
        while (request.getPageToken() != null && request.getPageToken().length() > 0);

        return result;
    }
}
