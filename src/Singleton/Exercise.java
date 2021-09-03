package Singleton;

import java.util.function.Supplier;

/**
 *  Supplier<T>接口被称为是一个生产型接口，指定接口的泛型是什么类型，那么接口中get方法获取的就是什么类型的数据.
 *
 *  isSingleton is used to check whether a factory method func returns a singleton instance.
 *  
 *  In the method isSingleton, func is a method that returns an Object, which is an instance of a class.
 *
 */
class SingletonTester
{

    public static boolean isSingleton(Supplier<Object> func)
    {
        // todo
        Object o1 = func.get();
        Object o2 = func.get();
        return o1==o2;
    }
}
public class Exercise {
}
