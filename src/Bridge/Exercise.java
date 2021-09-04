package Bridge;

/**
 * The impl
 */
interface Renderer{
    public String whatToRenderAs();
}

/**
 * impl 的实现类
 */
class VectorRenderer implements Renderer{

    @Override
    public String whatToRenderAs(){
        return "lines";
    }
}

/**
 * impl 的实现类
 */
class RasterRenderer implements Renderer{

    @Override
    public String whatToRenderAs(){
        return "pixels";
    }
}

abstract class Shape
{
    Renderer r;
    public abstract String getName();

    public String toString(){
        return "Drawing "+ getName()+ " as "+r.whatToRenderAs();
    }
}

class Triangle extends Shape
{
    public Triangle(Renderer r){
        this.r = r;
    }
    @Override
    public String getName()
    {
        return "Triangle";
    }

}

class Square extends Shape
{
    public Square(Renderer r){
        this.r = r;
    }
    @Override
    public String getName()
    {
        return "Square";
    }
}


// imagine VectorTriangle and RasterTriangle are here too

public class Exercise {
}
