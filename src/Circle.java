public class Circle extends Shape {

    private final double radius;

    public Circle(String id, double radius, Renderer renderer) {
        super(id, renderer);
        this.radius = radius;
    }

    public double getRadius() {
        return radius;
    }

    @Override
    public String execute() {
        return renderer.renderCircle(radius);
    }
}
