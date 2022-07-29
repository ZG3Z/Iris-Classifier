import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Classifier {
    private final IClassifier iClassifier;

    public Classifier(String [] args){
        switch (args.length){
            case 1->
                iClassifier = new KnnClassifier(Integer.parseInt(args[0]));
            case 2 ->
                iClassifier = new PerceptronClassifier(Double.parseDouble(args[0]),Double.parseDouble(args[1]));
            default ->
                throw new IllegalArgumentException("Invalid number of arguments");
        }
    }

    private  List<Point> convertFromFileToPoints (String fileName){
        List <Point> points = new ArrayList<>();

        File file = new File(fileName);
        BufferedReader br;
        try {
            br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null) {
                List<Double> values = new ArrayList<>();
                String result;

                String [] data = line.split(",");
                result = data[data.length - 1];

                for (int i = 0; i < data.length - 1; i++)
                    values.add(Double.parseDouble(data[i]));

                points.add(new Point(values, result));
            }
        } catch (IOException e) { e.printStackTrace(); }

        return points;
    }

    public double accuracy(String trainSetFileName, String testSetFileName){
        List<Point> trainSet = convertFromFileToPoints(String.valueOf(trainSetFileName));
        List<Point> testSet = convertFromFileToPoints(String.valueOf(testSetFileName));

        double accuracy = 0;
        for(Point point : testSet)
            if(iClassifier.classification(trainSet, point).equals(point.result))
                accuracy++;

        return accuracy/testSet.size()*100;
    }
}
