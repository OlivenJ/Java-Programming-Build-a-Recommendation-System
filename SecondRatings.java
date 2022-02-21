import java.util.*;


/**
 * Write a description of SecondRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class SecondRatings {
    private ArrayList<Movie> myMovies;
    private ArrayList<Rater> myRaters;
    
    public SecondRatings() {
        // default constructor
        this("ratedmoviesfull.csv", "ratings.csv");
    }
    
    public SecondRatings(String moviefile, String ratingfile){
    FirstRating fr = new FirstRating();
    
    myMovies = fr.loadMovie(moviefile);
    myRaters = fr.loadRaters(ratingfile);
    
    }
    
    
    public int getMovieSize(){
    return myMovies.size();
    }
    
    public int getRaterSize(){
    return myRaters.size();
    }
    
    public double getAverageByID(String movieID, int minimalRaters){
    double result = 0.0;
    int ratersAmount = 0;
    double totalRating = 0.0;
    
        for(int i = 0; i < myRaters.size(); i ++){
            if(myRaters.get(i).getItemsRated().contains(movieID)){
                ratersAmount ++;
                totalRating += myRaters.get(i).getRating(movieID);
            }
        }
    
        if(ratersAmount >= minimalRaters){
            result = totalRating/ratersAmount;
        }else{
            result = 0.0;
        }
    
    return result;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
    //public void getAverageRatings(){
        
    ArrayList<Rating> result = new ArrayList<Rating>();
    
    String movieID;
    HashSet<String> ratedMovie = new HashSet<String>();
    for(int i = 0; i < myRaters.size(); i ++){
        ArrayList<String> tempRated = myRaters.get(i).getItemsRated();
        for(int j = 0; j < tempRated.size(); j ++){
            ratedMovie.add(tempRated.get(j));
        }
    }
    
    for(String s : ratedMovie){
        double score = getAverageByID(s, minimalRaters);
        result.add(new Rating(s,score));
    }
    
    for(int i = 0; i < result.size(); i ++){
        if(result.get(i).getValue() <= 0.0){
            result.remove(i);
        }
    }
    return result;
    }
    
    public String getTitle(String id){
        String result = "ID not found";
            for(int i = 0; i <myMovies.size(); i ++){
                if(id.equals(myMovies.get(i).getID())){
                    return myMovies.get(i).getTitle();
                }
            }
        return result;
    }
    
    public String getID(String title){
    String result = "NO SUCH TITLE.";
        for(int i = 0; i <myMovies.size(); i ++){
                if(title.equals(myMovies.get(i).getTitle())){
                    return myMovies.get(i).getID();
                }
            }
    return result;
    }
}