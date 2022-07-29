import java.util.List;

public interface IClassifier {
    String classification(List<Point> trainSet, Point testPoint);
}
