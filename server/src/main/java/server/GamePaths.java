package server;

/**
 * Created by fran on 03/06/16.
 */
public class GamePaths {

    public static String buildLibs = "/build/libs/";
    public static String jar = "-1.0.jar";

    public static String server = "server" + buildLibs;

    public static String getProjectPath() {
        String currentPath = System.getProperty("user.dir");

        String projectPath = currentPath.replace(server,"") + "/";

        return projectPath;
    }

    public static String getGamePath(String gameName) {
        return getProjectPath() + gameName + buildLibs + gameName + jar;
    }


}
