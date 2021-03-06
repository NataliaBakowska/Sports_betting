package pl.coderslab.sportsbetting.comparator;
import pl.coderslab.sportsbetting.entity.Game;

import java.util.Comparator;

public class GameComparator implements Comparator<Game> {


    @Override
    public int compare(Game x, Game y) {
        if(x.getStartingAt().before(y.getStartingAt())){
            return -1;
        }
        if(x.getStartingAt().after(y.getStartingAt())){
            return 1;
        }
        return 0;
    }
}