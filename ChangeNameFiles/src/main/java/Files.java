import java.io.File;
import java.util.ArrayList;

public class Files {

    public void start(String pathToFolder) {
        FileUtils fileUtils = new FileUtils();

        File directPath = new File(pathToFolder);

        directoryExist(directPath);
    }

    private void directoryExist(File directPath) {
        FileUtils fileUtils = new FileUtils();
        if (directPath.exists()) {
            ArrayList<File> files = new ArrayList<File>();
            System.out.println("Znalesiony Folder");
            files = fileUtils.listFilesForFolder(directPath);
        }
    }

}
