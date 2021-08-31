package SOLIDPrinciple;

/**
 * @File LiskovSubstitutionPrinciple.java
 * @Author Emily Weilan Tao
 * @Date 2021-08-31
 * @Description LiskovSubstitutionPrinciple-> you should be able to substitude a derived class for a base class.
 *              Implementation: Factory Design Pattern
 * @Since version-1.0
 * @Copyright Copyright (c) 2021
 */


class Rectangle {
    protected int width, height;

    public Rectangle() {
    }

    ;

    public Rectangle(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "width=" + width +
                ", height=" + height +
                '}';
    }

    public int getArea(){
        return  width* height;
    }
}

class Square extends Rectangle{
    public  Square(){}

    public Square(int size){
        super(size,size);
    }

    /**
     * 父类不能用子类的衍生方法；
     * 但是如果父子出现同名的方法,会运行子类中的方法,因为方法有覆盖的特性。
     */
    @Override
    public void setWidth(int height){
        super.setHeight(height);
        super.setWidth(height);
    }

    @Override
    public void setHeight(int height){
        super.setHeight(height);
        super.setWidth(height);
    }



    public boolean isSquare(){
        return width==height;
    }

}

/**
 * Inorder to make the Square class substitutable
 */
class RectangleFactory{
    public static Rectangle newRectangle(int w, int h){
        return new Rectangle(w, h);
    }

    public static Rectangle newSquare(int s){
        return new Rectangle(s,s);
    }

}

public class LiskovSubstitutionPrinciple {

    static  void useIt(Rectangle r){
        int width = r.getWidth();
        r.setHeight(10);
        System.out.println("Expected area of "+ (width*10)+
                " , got "+ r.getArea());
    }

    public static void main(String[] args) {
        Rectangle rc = new Rectangle(2,3);
        useIt(rc); //output area: 20

        Rectangle square = new Square();
        square.setWidth(5);
        useIt(square); //output area: 100
    }

}
