
/**
 * Write a description of minutesFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MinutesFilter implements Filter {
    private int minMin;
    private int maxMin;
    
    public MinutesFilter(int minInput, int maxInput){
        this.minMin = minInput;
        this.maxMin = maxInput;
    }
    
    public boolean satisfies(String id){
    
        return (MovieDatabase.getMinutes(id) >= minMin && MovieDatabase.getMinutes(id) <= maxMin);
    }
}
