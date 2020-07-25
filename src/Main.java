import java.io.File;
import java.text.CharacterIterator;
import java.text.StringCharacterIterator;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        String folderPath = "C:" + File.separator
                + "Users" + File.separator
                + "admin" + File.separator
                + "Downloads";
        File file = new File(folderPath);

        long start = System.currentTimeMillis();

        FolderSizeCalculator calculator =
                new FolderSizeCalculator(file);
        ForkJoinPool pool = new ForkJoinPool();
        long size = pool.invoke(calculator);

        System.out.println(getHumanReadableSize(size));

        long duration = System.currentTimeMillis() - start;
        System.out.println(duration + " ms");
    }

    public static long getFolderSize(File folder) {
        if (folder.isFile()) {
            return folder.length();
        }
        long sum = 0;
        File[] files = folder.listFiles();
        for (File file : files) {
            sum += getFolderSize(file);
        }
        return sum;
    }


    private static String getHumanReadableSize(long bytes) {
        long kilobyte = 1024;
        long megabyte = kilobyte * 1024;
        long gigabyte = megabyte * 1024;
        long terabyte = gigabyte * 1024;

        if ((bytes >= 0) && (bytes < kilobyte)) {
            return bytes + " B";

        } else if ((bytes >= kilobyte) && (bytes < megabyte)) {
            return (bytes / kilobyte) + " KB";

        } else if ((bytes >= megabyte) && (bytes < gigabyte)) {
            return (bytes / megabyte) + " MB";

        } else if ((bytes >= gigabyte) && (bytes < terabyte)) {
            return (bytes / gigabyte) + " GB";

        } else if (bytes >= terabyte) {
            return (bytes / terabyte) + " TB";

        } else {
            return bytes + " Bytes";
        }
    }


    public static long getSizeFromHumanReadable(String stringSize) {
        long kilobyte = 1024;
        long megabyte = kilobyte * 1024;
        long gigabyte = megabyte * 1024;
        long terabyte = gigabyte * 1024;
        String[] size = stringSize.split("\\s+");

        if (size[2].equals("KB")){
            return Long.parseLong(size[1]) * kilobyte;
        }
        if (size[2].equals("MB")){
            return Long.parseLong(size[1]) * megabyte;
        }
        if (size[2].equals("GB")){
            return Long.parseLong(size[1]) * gigabyte;
        }
        if (size[2].equals("TB")){
            return Long.parseLong(size[1]) * terabyte;
        }
        return 0;
    }
}
