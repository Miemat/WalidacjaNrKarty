import java.io.File;
import java.util.ArrayList;
import java.util.Date;

public class FileUtils {

    public ArrayList<File> listFilesForFolder(final File folder) {
        ArrayList<File> fileFromFolder = new ArrayList<File>();

        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                listFilesForFolder(fileEntry);
            } else {
                System.out.println(fileEntry.getName());
                fileFromFolder.add(fileEntry);
            }
        }
        return fileFromFolder;
    }

    public String createDate(Date date) {
        String dateString = "";

        dateString = date.getDay() + "_" + date.getMonth() + "_" + date.getYear() + "_" + date.getHours() + ":" + date.getMinutes() + ":" + date.getSeconds();

        return dateString;
    }
}
