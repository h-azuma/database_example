package encryption;

import java.util.*;

public class SaltMaker {
    public static String makeSalt(String str) {
        StringBuilder sb = new StringBuilder();
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < str.length(); i++) {
            list.add(i);
        }

        Random rand = new Random(decideSeed(str));
        Collections.shuffle(list, rand);

        for (int index : list) {
            sb.append(str.charAt(index));
        }

        return sb.toString();
    }

    private static int decideSeed(String str) {
        int seed = 0;

        for (int i = 0; i < str.length(); i++) {
            seed += str.charAt(i);
        }

        return seed;
    }
}
