package FavImageDownload;

import java.io.File;

/**
 * Created by Masaki on 2017/10/05.
 */
public interface IFileUpload
{
    public void uploadImage(File file);

    public void uploadImage(String storeUri);
}
