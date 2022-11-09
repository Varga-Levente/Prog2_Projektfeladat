import java.util.ArrayList;
import java.util.List;

public class Structure {
    String path;
    String dirname;
    List<String>files;
    Boolean isEmpty;
    int filecount;
    int level;

    public Structure(String path, int level){
        setPath(path);
        setDirname(path);
        setFiles(path);
        setEmpty(path);
        setFilecount(files);
        setLevel(level);
    }

    public void setPath(String path){
        this.path = path.replace("\\", "/");
    }

    public void setLevel(int level){
        this.level = level;
    }

    public void setFilecount(List<String> files){
        this.filecount = files.size();
    }

    public void setFiles( String path){
        List<String> filelist = new ArrayList<>();
        Utils.getFiles(path, 0, filelist);
        this.files = filelist;
    }

    public void setDirname(String path){
        String[] parts = path.split("/");
        this.dirname = parts[parts.length-1];
    }

    public void setEmpty(String path){
        List<String> filelist = new ArrayList<>();
        Utils.getFiles(path, 0, filelist);
        this.isEmpty = filelist.size() == 0;
    }

    public String getPath(){
        return path;
    }

    public String getFiles(){
        return files.toString();
    }

    public String getDirname(){
        return dirname;
    }

    public Boolean getEmpty(){
        return isEmpty;
    }

    public int getFilecount(){
        return filecount;
    }

    public List<String> getFilesList(){
        return files;
    }

    public int getLevel(){
        return level;
    }

    @Override
    public String toString() {
        return "Structure{" +
                "path='" + path + '\'' +
                ", dirname='" + dirname + '\'' +
                ", files=" + files +
                ", isEmpty=" + isEmpty +
                ", filecount=" + filecount +
                '}';
    }

}
