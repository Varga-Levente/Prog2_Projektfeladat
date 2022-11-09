import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Generator {
    private Generator(){} // private constructor

    public static void generateIndex(List<Structure> struct, String path) {
        String file_start = """
                <!DOCTYPE html>
                <html lang="en">
                                
                <head>
                    <meta charset="utf-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
                    <title>Explorer</title>
                    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css">
                    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.12.0/css/all.css">
                </head>
                                
                <body>
                    <div class="container" style="margin-top: 1%;">
                        <div class="row">
                            <div class="col-xl-2"><a class="btn btn-primary disabled" role="button" href="#backurl" style="width: 100%;"><strong><i class="fas fa-backward"></i></strong></a></div>
                            <div class="col-md-12 col-xl-10"><a class="btn btn-primary" role="button" style="width: 100%;" href="#homelink"><strong>Start Page</strong></a></div>
                        </div>
                        <div class="row" style="margin-top: 1%;">
                            <div class="col-md-6" style="border-right: 3px solid black;">
                                <div>
                                    <h2>Directories:</h2>
                                    <div style="display: flex;flex-flow: column;">""";

        String file_middle = """
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div>
                                    <h2>Images:</h2>
                                    <div style="display: flex;flex-direction: column;">""";

        String file_end = """
                </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js"></script>
                </body>
                                
                </html>""";

        // Create filewriter
        try (FileWriter file = new FileWriter(path + "/index.html")) {
            file.write(file_start);
            for(Structure s : struct) {
                if (s.getLevel() == 0) {
                    file.write("<a class=\"btn btn-warning\" role=\"button\" style=\"margin-bottom: 5px;\" href=\"." + s.path.replace(path, "") + "/index.html\"><i class=\"fas fa-folder\"></i>&nbsp;" + s.getDirname() + "</a>");
                }
            }
            file.write(file_middle);
            for(Structure s : struct){
                if(s.getPath().equals(path)){
                    for (String f : s.files) {
                        file.write("<a class=\"btn btn-success\" role=\"button\" style=\"margin-bottom: 5px;\" href=\"." + f.replace(f.split("\\.")[1], "html").replace(path, "") + "\"><i class=\"fas fa-image\"></i>&nbsp;" + f.split("/")[f.split("/").length - 1] + "</a>");
                    }
                }
            }
            file.write(file_end);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void generateDirView(List<Structure> struct, String root){
        for (Structure s : struct) {
            List<String> files = s.getFilesList();
            List<String> directories = new ArrayList<>();
            Utils.pathSubs(s.getPath(), directories);
            String homeurl = "";
            int tohomecount = s.getPath().replace(root, "").length() - s.getPath().replace(root, "").replace("/", "").length();
            for (int i = 0; i < tohomecount; i++) {
                homeurl += "../";
            }

            String backurl = "../index.html";

            String file_start = "<!DOCTYPE html>\n" +
                    "<html lang=\"en\">\n" +
                    "\n" +
                    "<head>\n" +
                    "    <meta charset=\"utf-8\">\n" +
                    "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, shrink-to-fit=no\">\n" +
                    "    <title>Explorer</title>\n" +
                    "    <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css\">\n" +
                    "    <link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.12.0/css/all.css\">\n" +
                    "</head>\n" +
                    "\n" +
                    "<body>\n" +
                    "    <div class=\"container\" style=\"margin-top: 1%;\">\n" +
                    "        <div class=\"row\">\n" +
                    "            <div class=\"col-xl-2\"><a class=\"btn btn-primary\" role=\"button\" href=\""+backurl+"\" style=\"width: 100%;\"><strong><i class=\"fas fa-backward\"></i></strong></a></div>\n" +
                    "            <div class=\"col-md-12 col-xl-10\"><a class=\"btn btn-primary\" role=\"button\" style=\"width: 100%;\" href=\""+homeurl+"index.html\"><strong>Start Page</strong></a></div>\n" +
                    "        </div>\n" +
                    "        <div class=\"row\" style=\"margin-top: 1%;\">\n" +
                    "            <div class=\"col-md-6\" style=\"border-right: 3px solid black;\">\n" +
                    "                <div>\n" +
                    "                    <h2>Directories:</h2>\n" +
                    "                    <div style=\"display: flex;flex-flow: column;\">";

            String file_middle = "</div>\n" +
                    "                </div>\n" +
                    "            </div>\n" +
                    "            <div class=\"col-md-6\">\n" +
                    "                <div>\n" +
                    "                    <h2>Images:</h2>\n" +
                    "                    <div style=\"display: flex;flex-direction: column;\">";

            String file_end = "</div>\n" +
                    "                </div>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js\"></script>\n" +
                    "</body>\n" +
                    "\n" +
                    "</html>";

                if(s.getPath().equals(root)){
                    return;
                }

                try (FileWriter file = new FileWriter(s.getPath() + "/index.html")) {
                    file.write(file_start);
                    for (String d : directories) {
                        String[] arr = d.split("/");

                        String dirname = d.split("/")[d.split("/").length - 1];
                        file.write("<a class=\"btn btn-warning\" role=\"button\" style=\"margin-bottom: 5px;\" href=\"."+d.replace(s.getPath(), "")+"/index.html\"><i class=\"fas fa-folder\"></i>&nbsp;"+dirname+"</a>");
                    }
                    file.write(file_middle);
                    for (String f : files) {
                        String filename = f.split("/")[f.split("/").length - 1];
                        file.write("<a class=\"btn btn-success\" role=\"button\" style=\"margin-bottom: 5px;\" href=\""+filename.replace(f.split("\\.")[1], "html")+"\"><i class=\"fas fa-image\"></i>&nbsp;"+filename+"</a>");
                    }
                    file.write(file_end);
                    file.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            System.out.println("");
        }
        System.out.println("Done");
    }

    public static void generateImageView( List<Structure> struct, String root){
        for (Structure s : struct) {
            String backurl = "./index.html";
            List<String> files = s.getFilesList();
            for (int i = 0; i < s.getFilecount(); i++) {
                String file_previouse = "";
                String file_current = files.get(i);
                String file_next = "";
                String prev_disabled = "";
                String next_disabled = "";

                String homeurl = "";
                int tohomec = file_current.replace(root, "").length() - file_current.replace(root, "").replace("/", "").length();
                for (int j = 1; j < tohomec; j++) {
                    homeurl += "../";
                }

                if(i<=0){
                    file_previouse = files.get(i);
                    prev_disabled = "disabled";
                }else{
                    file_previouse = files.get(i-1);
                }

                if(i>=s.getFilecount()-1){
                    file_next = files.get(i);
                    next_disabled = "disabled";
                }else{
                    file_next = files.get(i+1);
                }

                String filename = file_current.split("/")[file_current.split("/").length - 1];

                file_next = file_next.split("/")[file_next.split("/").length - 1].replace(file_next.split("\\.")[1], "html");
                file_previouse = file_previouse.split("/")[file_previouse.split("/").length - 1].replace(file_previouse.split("\\.")[1], "html");

                //Create file writer
                try (FileWriter file = new FileWriter(file_current.replace(file_current.split("\\.")[1], "html"))) {
                    file.write("<!DOCTYPE html>\n" +
                            "<html lang=\"en\">\n" +
                            "\n" +
                            "<head>\n" +
                            "    <meta charset=\"utf-8\">\n" +
                            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0, shrink-to-fit=no\">\n" +
                            "    <title>Image View: "+filename+"</title>\n" +
                            "    <link rel=\"stylesheet\" href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css\">\n" +
                            "    <link rel=\"stylesheet\" href=\"https://use.fontawesome.com/releases/v5.12.0/css/all.css\">\n" +
                            "</head>\n" +
                            "\n" +
                            "<body>\n" +
                            "    <div class=\"container\" style=\"margin-top: 1%;\">\n" +
                            "        <div class=\"row\">\n" +
                            "            <div class=\"col-md-2 col-xl-2\"><a class=\"btn btn-primary\" role=\"button\" style=\"width: 100%;\" href=\""+backurl+"\"><strong><i class=\"fas fa-backward\"></i></strong></a></div>\n" +
                            "            <div class=\"col\"><a class=\"btn btn-primary\" role=\"button\" style=\"width: 100%;\" href=\""+homeurl+"index.html\"><strong>Start Page</strong></a></div>\n" +
                            "        </div>\n" +
                            "        <div class=\"row\" style=\"margin-top: 3%;\">\n" +
                            "            <div class=\"col\">\n" +
                            "                <p style=\"text-align: center;\"><strong>"+filename+"</strong></p>\n" +
                            "            </div>\n" +
                            "        </div>\n" +
                            "        <div class=\"row\">\n" +
                            "            <div class=\"col-2 col-sm-2 col-md-1 col-lg-2 col-xl-2\" style=\"display: flex;align-items: center;flex-direction: row-reverse;\">\n" +
                            "                <a class=\"btn btn-primary "+prev_disabled+"\" role=\"button\" style=\"float: right;height: 50%;width: 30%;display: inline-flex;align-items: center;flex-direction: row-reverse;\" href=\""+file_previouse+"\"><i class=\"fas fa-chevron-left\" style=\"width: 100%;\"></i></a>\n" +
                            "            </div>\n" +
                            "            <div class=\"col-8 col-sm-8 col-md-9 col-lg-8 col-xxl-8\">\n" +
                            "                <img class=\"img-responsive\" src=\"./"+filename+"\" style=\"/*width: 100%;*//*height: auto;*//*border: 2px black solid;*/display: block;margin-left: auto;margin-right: auto;max-width: 100%;max-height: 80vh;/*margin: auto;*/\">\n" +
                            "            </div>\n" +
                            "            <div class=\"col-2 col-sm-2 col-md-1 col-lg-2\" style=\"display: flex;align-items: center;\">\n" +
                            "                <a class=\"btn btn-primary "+next_disabled+"\" role=\"button\" style=\"float: left;height: 50%;width: 30%;display: inline-flex;align-items: center;\" href=\""+file_next+"\"><i class=\"fas fa-chevron-right\" style=\"width: 100%;\"></i></a>\n" +
                            "            </div>\n" +
                            "        </div>\n" +
                            "    </div>\n" +
                            "    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/js/bootstrap.bundle.min.js\"></script>\n" +
                            "</body>\n" +
                            "\n" +
                            "</html>");
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }
    }

}
