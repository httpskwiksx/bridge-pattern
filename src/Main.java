public class Main {

    public static void main(String[] args) {

        if (args.length > 0 && args[0].equals("--demo")) {
            runDemo();
        }
    }

    private static void runDemo() {
        int passed = 0;

        Renderer vector = new VectorRenderer();
        Renderer raster = new RasterRenderer();
        Renderer ascii = new AsciiRenderer();

        // T1: Circle + VectorRenderer
        Shape circleVector = new Circle("C1", 2, vector);
        String t1Actual = circleVector.execute();
        String t1Expected = "VECTOR circle radius=2.0";

        if (printResult(
                "T1",
                "Circle + VectorRenderer",
                t1Actual,
                t1Expected)) {
            passed++;
        }


        // T2: Circle + RasterRenderer
        Shape circleRaster = new Circle("C2", 2, raster);
        String t2Actual = circleRaster.execute();
        String t2Expected = "RASTER circle radius=2.0";

        if (printResult(
                "T2",
                "Circle + RasterRenderer",
                t2Actual,
                t2Expected)) {
            passed++;
        }


        // T3: Square + VectorRenderer
        Shape squareVector = new Square("S1", 3, vector);
        String t3Actual = squareVector.execute();
        String t3Expected = "VECTOR square side=3.0";

        if (printResult(
                "T3",
                "Square + VectorRenderer",
                t3Actual,
                t3Expected)) {
            passed++;
        }


        // T4: Square + RasterRenderer
        Shape squareRaster = new Square("S2", 3, raster);
        String t4Actual = squareRaster.execute();
        String t4Expected = "RASTER square side=3.0";

        if (printResult(
                "T4",
                "Square + RasterRenderer",
                t4Actual,
                t4Expected)) {
            passed++;
        }


        // T5: Runtime implementation switch
        Circle circle = new Circle("C3", 2, vector);

        Circle originalReference = circle;

        String originalId = circle.getId();
        double originalRadius = circle.getRadius();

        String before = circle.execute();

        circle.setImplementation(raster);

        String after = circle.execute();

        boolean sameObject = originalReference == circle;

        boolean stateUnchanged =
                originalId.equals(circle.getId())
                        && originalRadius == circle.getRadius();

        boolean t5Passed =
                sameObject
                        && stateUnchanged
                        && before.equals("VECTOR circle radius=2.0")
                        && after.equals("RASTER circle radius=2.0");

        System.out.println(
                "T5 " + (t5Passed ? "PASS" : "FAIL")
                        + " | sameObject=" + sameObject
                        + " | stateUnchanged=" + stateUnchanged
        );

        System.out.println(" before=" + before);
        System.out.println(" after=" + after);

        if (t5Passed) {
            passed++;
        }


        // T6: Circle + AsciiRenderer
        Shape circleAscii = new Circle("C4", 2, ascii);
        String t6Actual = circleAscii.execute();
        String t6Expected = "ASCII circle radius=2.0";

        if (printResult(
                "T6",
                "Circle + AsciiRenderer",
                t6Actual,
                t6Expected)) {
            passed++;
        }


        // T7: Square + AsciiRenderer
        Shape squareAscii = new Square("S3", 3, ascii);
        String t7Actual = squareAscii.execute();
        String t7Expected = "ASCII square side=3.0";

        if (printResult(
                "T7",
                "Square + AsciiRenderer",
                t7Actual,
                t7Expected)) {
            passed++;
        }


        System.out.println();
        System.out.println("SUMMARY: " + passed + "/7 PASS");
    }


    private static boolean printResult(
            String testId,
            String classes,
            String actual,
            String expected
    ) {

        boolean passed = actual.equals(expected);

        System.out.println(
                testId + " "
                        + (passed ? "PASS" : "FAIL")
                        + " | " + classes
                        + " | result=" + actual
        );

        if (!passed) {
            System.out.println(" expected=" + expected);
        }

        return passed;
    }
}