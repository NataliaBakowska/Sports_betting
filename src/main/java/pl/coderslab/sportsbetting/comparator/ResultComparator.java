package pl.coderslab.sportsbetting.comparator;
import pl.coderslab.sportsbetting.entity.Result;

import java.util.Comparator;

public class ResultComparator implements Comparator<Result> {

    @Override
    public int compare(Result x, Result y){
        if(x.getPosition()<y.getPosition()){
            return -1;
        }
        if(x.getPosition()>y.getPosition()){
            return 1;
        }
        return 0;
    }
}
