package modele;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Filling {
    static HashMap<Rocket, List<Object>> fifoFilling(List<Object> allObjects, List<Rocket> rockets)
    {
        HashMap<Rocket, List<Object>> res = new HashMap<>();
        for(Rocket rocket : rockets)
        {
            int w = 0;
            List<Object> objects = new ArrayList<>();
            while(allObjects.size() > 0 && (allObjects.get(0).getWeight() + w) <= rocket.getMaxWeight())
            {
                w+= allObjects.get(0).getWeight();
                objects.add(allObjects.get(0));
                allObjects.remove(0);
            }
            rocket.setWeight(w);
            res.put(rocket,objects);
        }

        return res;
    }

    static HashMap<Rocket, List<Object>> packetFilling(List<Object> allObjects, List<Rocket> rockets)
    {
        HashMap<Rocket, List<Object>> res = new HashMap<>();
        HashMap<Integer, List<Object>> objectsSorted = splitObject(allObjects);
        int priority = objectsSorted.size()-1;

        if(allObjects.size()>0) {
            for(Rocket rocket : rockets)
            {
                int w = 0;
                List<Object> objects = new ArrayList<>();
                while (priority >= 0 && (objectsSorted.get(priority).get(0).getWeight() + w) <= rocket.getMaxWeight()) {
                    Object o = objectsSorted.get(priority).get(0);
                    w += o.getWeight();
                    objects.add(o);
                    objectsSorted.get(priority).remove(0);
                    if(objectsSorted.get(priority).size() == 0) priority--;
                }
                rocket.setWeight(w);
                res.put(rocket,objects);
            }
        }
        return res;
    }

    static HashMap<Integer, List<Object>> splitObject(List<Object> objects)
    {
        HashMap<Integer, List<Object>> res = new HashMap<>();

        for(Object object : objects)
        {
            int priority;
            switch (object.getPriority())
            {
                case "low":
                    priority = 0;
                    break;
                case "medium":
                    priority = 1;
                    break;
                case "high":
                    priority = 2;
                    break;
                default:
                    priority = -1;
                    break;
            }
            if(!res.containsKey(priority)) res.put(priority,new ArrayList<>());
            res.get(priority).add(object);
        }
        return res;
    }

    static HashMap<Rocket, List<Object>> moreSpaceFilling(List<Object> allObjects, List<Rocket> rockets)
    {
        HashMap<Rocket, List<Object>> res = new HashMap<>();

        if(allObjects.size()>0) {
            for (Rocket rocket : rockets) {
                int w = 0;
                List<Object> objects = new ArrayList<>();
                while (allObjects.size() > 0 && (allObjects.get(0).getWeight() + w) <= rocket.getMaxWeight()) {
                    w += allObjects.get(0).getWeight();
                    objects.add(allObjects.get(0));
                    allObjects.remove(0);
                }

                boolean findMore = true;

                while (findMore) {
                    if (allObjects.size() == 0) findMore = false;
                    for (Object object : allObjects) {
                        if ((object.getWeight() + w) <= rocket.getMaxWeight()) {
                            findMore = true;
                            objects.add(object);
                            allObjects.remove(object);

                            break;
                        } else {
                            findMore = false;
                        }
                    }
                }
                rocket.setWeight(w);
                res.put(rocket, objects);
            }
        }

        return res;
    }
}
