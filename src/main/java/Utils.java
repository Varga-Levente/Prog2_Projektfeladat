import java.io.File;
import java.util.Arrays;
import java.util.List;

public class Utils {
    private Utils(){} // private constructor

    static void pathSubs(String path, List<String> directories){
        File dir = new File(path);
        File[] arr = dir.listFiles();
        assert arr != null;
        List<File> files = Arrays.asList(arr);
        for (File file : files) {
            if (file.isDirectory()) {
                directories.add(file.getPath());
            }
        }
    }
    static void findDirectories(String path, int index, int level, List<Structure> struct) {
        File dir = new File(path);
        File[] arr = dir.listFiles();
        assert arr != null;
        if (index == arr.length)
            return;

        if (arr[index].isDirectory()) {
            struct.add( new Structure(arr[index].getAbsolutePath(), level) );
            findDirectories(arr[index].getAbsolutePath(), 0, level + 1, struct);
        }

        findDirectories(path, ++index, level, struct);
    }

    static void getFiles(String path, int index, List<String> files) {
        File dir = new File(path);
        File[] arr = dir.listFiles();
        assert arr != null;
        if (index == arr.length)
            return;

        if (arr[index].isFile()) {
            String[] extensions = {"png","jpg","jpeg","gif"};
            List<String> ext = Arrays.asList(extensions);
            if (ext.contains(arr[index].getName().substring(arr[index].getName().lastIndexOf(".") + 1))) {
                files.add(arr[index].getAbsolutePath());
            }
        }
        getFiles(path, ++index, files);
    }

    static void checkArgs(String[] args){
        if (args.length == 0) {
            System.out.println("Please provide a path to a directory");
            System.exit(1);
        }

        if (args.length > 1) {
            System.out.println("Please provide only one path to a directory");
            System.exit(1);
        }
    }

}
