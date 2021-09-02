package Factory;

class PointClass{
    private double x, y;

    private  PointClass(double x, double y){
        this.x=x;
        this.y=y;
    }

    static class Factory{
        //Inner class cannot have stativ method unless the class is static itself.
        public static PointClass newCartesionPoint(double x, double y) {
            return new PointClass(x,y);
        }

        public static PointClass newPolarPoint(double rho, double theta){
            return new PointClass (rho*Math.cos(theta), rho*Math.sin(theta));
        }
    }

}

public class Factory {
    public static void main(String[] args) {
        PointClass point = PointClass.Factory.newCartesionPoint(2,3);
    }
}
