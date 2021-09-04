package Adapter;


class Square
{
    public int side;

    public Square(int side)
    {
        this.side = side;
    }
}

interface Rectangle
{
    int getWidth();
    int getHeight();

    default int getArea()
    {
        return getWidth() * getHeight();
    }
}

class SquareToRectangleAdapter implements Rectangle
{
    private Square square;

    public SquareToRectangleAdapter(Square square)
    {
        // todo
        this.square = square;
    }

    public int getWidth(){
        return square.side;
    }
    public int getHeight(){
        return square.side;
    }


}

public class Exercise {
}
