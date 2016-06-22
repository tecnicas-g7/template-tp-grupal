package server;

/**
 * Created by fran on 03/06/16.
 */
public class GamePaths {

    public static final String separator = System.getProperty("file.separator");
    public static final String buildLibs = separator + "build" + separator + "libs" + separator + "games" + separator ;
    public static final String jar = "-1.0.jar";
    public static final String games = "games"  + separator ;

    public static final String server =  separator + "server";

    public static String getProjectPath() {
        String currentPath = System.getProperty("user.dir");
        String projectPath = currentPath.replace(server,"") + separator;
        projectPath = projectPath.replace(buildLibs,"");
        return projectPath;
    }

    public static String getGamePath(String gameName) {
        return getProjectPath() + games + gameName + buildLibs + gameName + jar;
    }


}
