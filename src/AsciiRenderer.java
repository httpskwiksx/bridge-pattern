public class AsciiRenderer implements Renderer {

    @Override
    public String renderCircle(double radius) {
        return "ASCII circle radius=" + radius;
    }

    @Override
    public String renderSquare(double side) {
        return "ASCII square side=" + side;
    }
}
