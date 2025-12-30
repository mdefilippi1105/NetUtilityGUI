package NetworkClasses;

import java.util.ArrayList;

public class Test {

    public static void main (String[] args) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 45 ; i++) {
            list.add(""+i);
            System.out.println(list);
        }
    }
}
