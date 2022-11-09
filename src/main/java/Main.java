
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //Check provided arguments (Not NULL, Only one argument)
        Utils.checkArgs(args);

            //String path = "/home/vlevente/Desktop/images";

        //Set path to the provided argument
        String path = args[0];

        //Define a list of images and directories
        List<Structure> struct = new ArrayList<>();

        //Add path to the list
        struct.add( new Structure(path, -1) );

        //Add all paths to the list
        Utils.findDirectories(path, 0, 0, struct);

        //Sort the list by directory name
        struct.sort(Comparator.comparing(Structure::getDirname));

        //Generating files
        Generator.generateIndex(struct, path);
        Generator.generateImageView(struct, path);
        Generator.generateDirView(struct, path);
    }
}