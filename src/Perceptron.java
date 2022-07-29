import java.util.List;
import java.util.Random;

public class Perceptron {
    public final Object id;
    private final double [] weights;
    private double threshold;
    private final double rate;

    public Perceptron(Object id, int numberOfWeight, double threshold, double rate){
        this.id = id;
        this.weights = new Random().doubles(numberOfWeight,0,1).toArray();
        this.threshold = threshold;
        this.rate = rate;
    }

    public void learn(List<Point> trainSet){
        for (Point point : trainSet) {
            double expectedOutValue = id.equals(point.result) ? 1 : 0;
            double computedOutValue = calculateNet(point.values) >= 0 ? 1 : 0;

            for (int value = 0; value < point.values.size(); value++) {
                weights[value] = weights[value] + rate * (expectedOutValue - computedOutValue) * point.values.get(value);
                threshold = threshold - rate * (expectedOutValue - computedOutValue);
            }
        }
    }

    public double calculateNet(List<Double> vector){
        double net = 0;

        for(int i = 0; i < vector.size(); i++)
            net += weights[i]  * vector.get(i);

        return net - threshold;
    }
}
