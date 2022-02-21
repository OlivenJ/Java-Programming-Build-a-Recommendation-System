import java.util.*;
import edu.duke.*;
/**
 * Write a description of MovieRunnerAverage here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerAverage {

    public void test(){
        SecondRatings sr = new SecondRatings("ratedmoviesfull", "ratings"); //movie first rating second
        
        //String title = "Her";
        //System.out.println(sr.getTitle(sr.getID("Her")).equals(title));
        ArrayList<Rating> result = sr.getAverageRatings(50);
        
        System.out.println(result.size());
    }
    
    
    public void printAverageRatings(){
        SecondRatings sr = new SecondRatings("ratedmoviesfull", "ratings"); //movie first rating second
    
        ArrayList<Rating> result = sr.getAverageRatings(12);
        int j = 1;
        while(j < result.size()){
    
            for(int i = 0; i < result.size()-1; i ++){
                Rating rate1 = result.get(i);
                Rating rate2 = result.get(i+1);
        
                if(rate1.getValue() < rate2.getValue()){
                    result.set(i+1, rate1);
                    result.set(i, rate2);
                }
    
            }
            j ++;
        }
    
    
    
        for(int i = 0; i < result.size(); i ++){
            if(result.get(i).getValue() > 0.0){
                System.out.println(sr.getTitle(result.get(i).getItem()) + ": " + result.get(i).getValue());
            }
        }
        
        System.out.println(result.size());
    
    }
    
    public void getAverageRatingOneMovie(){
    SecondRatings sr = new SecondRatings("ratedmoviesfull", "ratings"); //movie first rating second    
    ArrayList<Rating> result = sr.getAverageRatings(1);
    
    String title = "Vacation";
    
    for(int i = 0; i < result.size(); i ++){
        if(sr.getTitle(result.get(i).getItem()).equals(title)){
            System.out.println(title + ": " + result.get(i).getValue());
        }
    }
    
    }

}
