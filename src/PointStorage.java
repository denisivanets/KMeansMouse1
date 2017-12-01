import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PointStorage {
    private static final String POINTS_FILE__PATH = "points.txt";
    private static PointStorage ourInstance;
    private static List<Point> pointList;

    public static PointStorage getInstance() {
        if (ourInstance != null) {
            return ourInstance;
        } else {
            ourInstance = new PointStorage();
            ourInstance.setPointList(new ArrayList<>());
            ourInstance.fillPointsList();
            return ourInstance;
        }
    }

    private PointStorage() {
    }

    public static void setPointList(List<Point> pointList) {
        PointStorage.pointList = pointList;
    }

    public List<Point> getPointList() {
        return pointList;
    }

    public void dropFlags(){
        pointList.forEach(
                point -> point.setIsAlreadyAdded(false)
        );
    }

    private static void fillPointsList() {
        Path path = FileSystems.getDefault().getPath("C:\\ideaProjects\\KMeansMouse1\\resources");
        try {
            Files.lines(path.resolve(POINTS_FILE__PATH))
                    .forEach(strPoint -> pointList.add(readPoint(strPoint)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Point readPoint(final String pointAsString) {
        String[] processed = pointAsString.split(";");
        int x = Integer.parseInt(processed[0]);
        int y = Integer.parseInt(processed[1]);
        return new Point(x, y);
    }

}
