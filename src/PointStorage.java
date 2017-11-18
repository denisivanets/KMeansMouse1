import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class PointStorage {
    private static final String POINTS_FILE__PATH = "points.txt";
    private static PointStorage ourInstance = new PointStorage();
    private static List<Point> pointList = new ArrayList<>();

    public static PointStorage getInstance() {
        return ourInstance;
    }

    private PointStorage() {
        pointList = new ArrayList<>();
        fillPointsList();
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
        Path path = FileSystems.getDefault().getPath("C:\\Users\\1\\Desktop\\KMeansMouse-master\\resources");
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
