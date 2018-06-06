package pl.coderslab.sportsbetting.entity;
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
