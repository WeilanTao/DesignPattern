package Flyweight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

class User{
    static List<String> names = new ArrayList<>();
    public static int[] indices;

    public User(String fullName) {
        Function<String, Integer> getOrAdd = (String s)->{
            int idx = names.indexOf(s);
            if(idx!=-1){
                return idx;
            }else{
                names.add(s);
                return names.size()-1;
            }
        };

        indices = Arrays.stream(fullName.split(" "))
                .mapToInt(s-> getOrAdd.apply(s))
                .toArray();
    }
}

public class _1RepeatingUserName {
    public static void main(String[] args) {
        User user = new User("Emily Tao");
        System.out.println(Arrays.toString(User.indices)); // [0, 1]

        User user1 = new User("Weilan Tao");
        System.out.println(Arrays.toString(User.indices)); // [2, 1]

        User user2 = new User("Emily Tao");
        System.out.println(Arrays.toString(User.indices)); // [0, 1]

    }
}
