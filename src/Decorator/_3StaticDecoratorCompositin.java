package Decorator;

import java.util.function.Supplier;

interface SShape{
    String info();
}

class SCircle implements  SShape{
    private float radius;

    public SCircle(float radius) {
        this.radius = radius;
    }

    public SCircle() {
    }

    void resize(float factor){
        radius *=factor;
    }
    @Override
    public String info() {
        return "A circle of radius "+ radius;
    }
}

class SSquare implements  SShape{

    private float side;

    public SSquare(float side) {
        this.side = side;
    }

    public SSquare() {
    }

    @Override
    public String info() {
        return "A square of side "+ side;
    }
}

class SColoredShape<T extends SShape> implements  SShape{

    private SShape shape;
    private String color;

    public SColoredShape(Supplier<? extends T> ctor,
                         String color) {
        this.color=color;
        shape=ctor.get();
    }

    @Override
    public String info() {
        return shape.info()+"has the color "+ color;
    }
}

class STransparentShape<T extends  SShape> implements SShape{

    private SShape shape;
    private int transparency;

    public STransparentShape(Supplier<? extends T> ctor,
                             int trans){
        shape=ctor.get();
        this.transparency=trans;
    }

    @Override
    public String info() {
        return null;
    }
}

public class _3StaticDecoratorCompositin {
    public static void main(String[] args) {
        SColoredShape<SSquare> blueSquare=
                new SColoredShape(()->new SSquare(20), "BLUE");

        System.out.println(blueSquare.info());
    }
}
