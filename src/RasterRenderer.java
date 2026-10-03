public class RasterRenderer implements Renderer {

    @Override
    public String renderCircle(double radius) {
        return "RASTER circle radius=" + radius;
    }

    @Override
    public String renderSquare(double side) {
        return "RASTER square side=" + side;
    }
}
