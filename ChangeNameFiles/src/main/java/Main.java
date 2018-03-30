import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] arguments) {
        Files file = new Files();
        Scanner scanner = new Scanner(System.in);
        String decision = "";
        ArrayList<File> files = new ArrayList<File>();

        FileUtils fileUtils = new FileUtils();

        do {
            System.out.println("Podaj sciezke do fodleru:");
            String pathToFolder = scanner.next();
            File directory = new File(pathToFolder);

            files = fileUtils.listFilesForFolder(directory);
            System.out.println("Chcesz zmienic nazwe plikow ? Tak(T) / Nie(N) / Wyjscie(Exit) ");
            decision = scanner.next();
            if (decision.equalsIgnoreCase("T")) {
                for (File index : files) {
                    try {
                        Long dateModifi = index.lastModified();

                        Date date = new Date();
                        date.setTime(dateModifi);

                        String dateToName = fileUtils.createDate(date);

                        String str = String.format("Current Date/Time : %F", date);

                        System.out.println("Czas: " + str);

                        System.out.println("zmiana nazwy pliku na: " + date.getTime());
                        File newFile = new File(index.getParent(), date.getTime() + ".png");


                        index.renameTo(newFile);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        } while (!(decision.equalsIgnoreCase("Exit")));
    }
}
