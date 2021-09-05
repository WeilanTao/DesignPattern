package Decorator;

interface Shape{
    String info();
}

class Circle implements  Shape{
    private float radius;

    public Circle(float radius) {
        this.radius = radius;
    }

    public Circle() {
    }

    void resize(float factor){
        radius *=factor;
    }
    @Override
    public String info() {
        return "A circle of radius "+ radius;
    }
}

class Square implements  Shape{

    private float side;

    public Square(float side) {
        this.side = side;
    }

    public Square() {
    }

    @Override
    public String info() {
        return "A square of side "+ side;
    }
}

class ColoredShape implements  Shape{

    private  Shape shape;
    private  String  color;

    public ColoredShape(Shape shape, String color) {
        this.shape = shape;
        this.color = color;
    }

    @Override
    public String info() {
        return shape.info()+" has the color "+color;
    }
}

class TransparentShape implements  Shape{

    private Shape shape;
    private int transparency;

    public TransparentShape(Shape shape, int transparency) {
        this.shape = shape;
        this.transparency = transparency;
    }

    public TransparentShape() {
    }

    @Override
    public String info() {
        return shape.info()+ " has "+transparency+"% transparency";
    }
}

public class _2DynamicDecoratorComposition {
    public static void main(String[] args) {
        Circle circle = new Circle(10);
        System.out.println(circle.info());

        ColoredShape square = new ColoredShape( new Square(20),
                "blue");
        System.out.println(square.info());

        TransparentShape transparentShape
                = new TransparentShape(
                        new ColoredShape(
                                new Circle(5),"green")
                                , 50);

        System.out.println(transparentShape.info());
    }
}
