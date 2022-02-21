import java.util.*;

/**
 * Write a description of DirectorFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class DirectorFilter implements Filter {
    private String[] directorName;
    
    public DirectorFilter(String name){
        this.directorName = name.split(",");
    }
    
    public boolean satisfies(String id){
        for(int i = 0; i < directorName.length; i++){
            if(MovieDatabase.getDirector(id).contains(directorName[i])){
                return true;
            }
        }
        return false;
    }
}
