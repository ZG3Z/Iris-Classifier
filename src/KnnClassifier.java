import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class KnnClassifier implements IClassifier{
    private final int parameterK;

    public KnnClassifier(int parameterK){
        this.parameterK = parameterK;
    }

    @Override
    public String classification(List<Point> trainSet, Point testPoint) {
        return trainSet.stream()
               .map(point -> getDistance(point, testPoint) + "-" + point.result)
               .sorted()
               .limit(parameterK)
               .map(result->result.substring(result.indexOf("-")+1))
               .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
               .entrySet().stream().max(Map.Entry.comparingByValue())
               .get()
               .getKey();
    }

    private double getDistance(Point point1, Point point2){
        double distance = 0.0;

        for(int i = 0; i < point1.values.size(); i++)
            distance += Math.pow(point1.values.get(i)-point2.values.get(i), 2);

        return Math.sqrt(distance);
    }

}
