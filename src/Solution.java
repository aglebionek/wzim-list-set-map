import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Solution {
    static <T> void print(WzimList<T> list) {
        for (T elem : list) {
            System.out.println(elem);
        }
    }
    public static void main(String[] args) throws Exception {
        WzimList<Integer> list = new WzimList<>();
        assert (list.isEmpty());


        WzimList<String> list2 = new WzimList<>(20);
        System.out.println(list2.size());


        Collection<Integer> number = IntStream.range(0, 10).boxed().collect(Collectors.toSet());
        WzimList<Integer> list3 = new WzimList<>();

        System.out.println(list.contains(5));
        System.out.println(list.indexOf(5));
        print(list);
    }
}
