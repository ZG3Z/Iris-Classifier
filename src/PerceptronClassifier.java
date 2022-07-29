import java.util.*;

public class PerceptronClassifier implements IClassifier{
    private static final int NUMBER_OF_LEARN = 30;
    private final List<Perceptron> perceptrons = new ArrayList<>();
    private final double threshold;
    private final double rate;

    public PerceptronClassifier(double threshold, double rate){
        this.threshold = threshold;
        this.rate = rate;
    }

    private void createPerceptrons(Object[] identities, int numberOfWeight){
        for (Object identity : identities)
            perceptrons.add(new Perceptron(identity.toString(), numberOfWeight, threshold, rate));
    }

    @Override
    public String classification(List<Point> trainSet, Point testPoint){

        createPerceptrons(trainSet.stream().map(point -> point.result).distinct().toArray(),
                trainSet.get(0).values.size());

        for(int i = 0; i < NUMBER_OF_LEARN; i++)
            for(Perceptron perceptron : perceptrons)
                perceptron.learn(trainSet);

        Map<Object, Double> nets = new HashMap<>();

        for(Perceptron perceptron : perceptrons)
            nets.put(perceptron.id, perceptron.calculateNet(testPoint.values));

        return  nets.entrySet().stream().max((p1,p2)-> (int) (p1.getValue()-p2.getValue())).get().getKey().toString();
    }
}
