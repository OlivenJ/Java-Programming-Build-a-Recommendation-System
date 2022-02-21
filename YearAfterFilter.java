
public class YearAfterFilter implements Filter {
    private int myYear;
    
    public YearAfterFilter(int year) {
        this.myYear = year;
    }
    
    @Override
    public boolean satisfies(String id) {
        return MovieDatabase.getYear(id) >= myYear;
    }

}
