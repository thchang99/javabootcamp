import java.util.*;

public class seats {
    public static void main(String[] args)
    {
       String[] l = {"태연", "수민","예진","현재","종하","은혜","태희","은서","윤지"};
       List <String> sl = Arrays.asList(l);
       Collections.shuffle(sl);
       Iterator<String> i = sl.iterator();
       int count = 0;
       while(i.hasNext())
       {
        System.out.println(++count + i.next());
       }
    }
}
