public class Main {
    public static void main(String[] args) {

        Renderer vector = new VectorRenderer();
        Renderer raster = new RasterRenderer();

        Shape circleVector = new Circle("C1", 2, vector);
        Shape circleRaster = new Circle("C2", 2, raster);
        Shape squareVector = new Square("S1", 3, vector);
        Shape squareRaster = new Square("S2", 3, raster);

        System.out.println(circleVector.execute());
        System.out.println(circleRaster.execute());
        System.out.println(squareVector.execute());
        System.out.println(squareRaster.execute());


        // ===== T5: Runtime implementation switch =====

        Circle circle = new Circle("C3", 2, new VectorRenderer());

        Circle originalReference = circle;

        String before = circle.execute();

        String originalId = circle.getId();
        double originalRadius = circle.getRadius();

        circle.setImplementation(new RasterRenderer());

        String after = circle.execute();

        boolean sameObject = originalReference == circle;

        boolean stateUnchanged =
                originalId.equals(circle.getId())
                        && originalRadius == circle.getRadius();

        System.out.println("sameObject=" + sameObject);
        System.out.println("stateUnchanged=" + stateUnchanged);
        System.out.println("before=" + before);
        System.out.println("after=" + after);
    }
}