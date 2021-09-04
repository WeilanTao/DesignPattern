package Bridge;

class GG{
    public  void chase(MM mm){
        Gift g = new WarmGift(new Flower());
        give (mm, g);
    }
    public void give(MM mm, Gift g){}
}

class MM{
    public String name;
}
///////////////////////////////////////////////////////////////////
abstract  class GiftImpl{

}
abstract class Gift{
    GiftImpl impl;
}
class WildGift extends Gift{
    public WildGift(GiftImpl impl){
        this.impl= impl;
    }

}

class WarmGift extends Gift{
    public WarmGift(GiftImpl impl){
        this.impl= impl;
    }
}

class Book extends  GiftImpl{

}

class Flower extends GiftImpl{

}

/////////////////////////////////////////////////////////////////////
public class Bridge {
    public static void main(String[] args) {

    }
}
