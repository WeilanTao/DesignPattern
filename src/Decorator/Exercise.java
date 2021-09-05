package Decorator;

class Bird
{
    public int age;

    public String fly()
    {
        return age < 10 ? "flying" : "too old";
    }
}

class Lizard
{
    public int age;

    public String crawl()
    {
        return (age > 1) ? "crawling" : "too young";
    }
}

class Dragon
{
    private Bird b =  new Bird();
    private Lizard l = new Lizard();
    private int age;
    public void setAge(int age)
    {
        // todo
        this.age = age;
        b.age= age;
        l.age=age;
    }
    public String fly()
    {
        // todo
        return b.fly();
    }
    public String crawl()
    {
        // todo
        return l.crawl();
    }
}

public class Exercise {
}
