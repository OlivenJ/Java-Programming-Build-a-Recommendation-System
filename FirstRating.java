import edu.duke.*;
import java.util.*;
import org.apache.commons.csv.*;

/**
 * Write a description of FirstRating here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FirstRating {

    public ArrayList<Movie> loadMovie(String filename){
        
    FileResource fr = new FileResource("/Users/zhiyuan/Desktop/DukeJava/Capstone/StepOneStarterProgram/data/" + filename+".csv");
    ArrayList<Movie> result = new ArrayList<Movie>();
    for(CSVRecord rec : fr.getCSVParser(true)){
        Movie entry =  new Movie(rec.get("id"), rec.get("title"), 
              rec.get("year"),rec.get("genre"), 
              rec.get("director"), rec.get("country"), 
              rec.get("poster"), Integer.parseInt(rec.get("minutes")));
        
        result.add(entry);
        }
    return result;
    }
    
    public ArrayList<Rater> loadRaters(String filename){
    FileResource fr = new FileResource("/Users/zhiyuan/Desktop/DukeJava/Capstone/StepOneStarterProgram/data/" + filename+".csv");
    ArrayList<Rater> result = new ArrayList<Rater>();
    //ArrayList<Rating> ratingList = new ArrayList<Rating>();
    int starterId = 1;
    EfficientRater firstRater = new EfficientRater("1");
    for(CSVRecord rec:fr.getCSVParser(true)){
        String raterId = rec.get("rater_id");
        
        if(Integer.parseInt(raterId) == starterId){
            //ratingList.add(ratingEntry);
            firstRater.addRating(rec.get("movie_id"), Double.parseDouble(rec.get("rating")));
        }else if(Integer.parseInt(raterId) != starterId){
            result.add(firstRater);
            starterId = Integer.parseInt(raterId);
            firstRater = new EfficientRater(raterId);
            firstRater.addRating(rec.get("movie_id"), Double.parseDouble(rec.get("rating")));
        }
        
    }
    result.add(firstRater);
    return result;
    }
    
    
}
