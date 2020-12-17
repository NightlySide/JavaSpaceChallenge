package modele;

import java.util.ArrayList;
import java.util.List;

public class Filling {
    static List<Object> fifoFilling(List<Object> allObjects, double weightmax)
    {
        List<Object> res = new ArrayList<>();
        double w = 0;
        for(Object o : allObjects)
        {
            if(w != weightmax)
            {
                res.add(o);
                w+=o.getWeight();
            }
        }
        return res;
    }
}
