package server;

/**
 * Created by fran on 03/06/16.
 */
public class GamePaths {

    public static final String buildLibs = "/build/libs/games/";
    public static final String jar = "-1.0.jar";
    public static final String games = "games/";

    public static final String server = "/server/build/libs";

    public static String getProjectPath() {
        String currentPath = System.getProperty("user.dir");
        String projectPath = currentPath.replaceAll(server,"") + "/";
        return projectPath;
    }

    public static String getGamePath(String gameName) {

        return getProjectPath() + games + gameName + buildLibs + gameName + jar;
    }


}
