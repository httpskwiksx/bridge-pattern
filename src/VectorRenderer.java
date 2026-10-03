public class VectorRenderer implements Renderer {

    @Override
    public String renderCircle(double radius) {
        return "VECTOR circle radius=" + radius;
    }

    @Override
    public String renderSquare(double side) {
        return "VECTOR square side=" + side;
    }
}
