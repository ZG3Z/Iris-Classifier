public class Main {
    public static void main(String[] args) {
     Classifier classifier = new Classifier(args);
        System.out.println("Accuracy: " + classifier.accuracy("data/iris-train.txt","data/iris-test.txt"));
    }
}
