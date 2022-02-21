import java.util.*;

/**
 * Write a description of ThirdRating here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ThirdRatings {


    private ArrayList<Rater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        this("ratings");
    }
    
    public ThirdRatings(String ratingfile){
        FirstRating fr = new FirstRating();
    
        myRaters = fr.loadRaters(ratingfile);
    
    }
    
    public ArrayList<Rater> explicit(){
        return myRaters;
    }
    
    public int getRaterSize(){
        return myRaters.size();
    }
    
    public int getMovieSize(){ // Number of moviews that been rated
        int result = 0;
        HashSet<String> moviewList = new HashSet<String>();
        for(int i = 0; i<myRaters.size(); i++){
            ArrayList<String> ratedMovie = myRaters.get(i).getItemsRated();
            for(int j = 0; j < ratedMovie.size(); j ++){
                moviewList.add(ratedMovie.get(j));
            }
        }
        result = moviewList.size();
        return result;
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
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters,Filter filterCriteria){
        ArrayList<Rating> primerResult = getAverageRatings(minimalRaters);
        ArrayList<Rating> result = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        
        for(int i = 0; i < primerResult.size(); i ++){
            if(movies.contains(primerResult.get(i).getItem())){
                result.add(primerResult.get(i));
            }
        
        }
        
        
        
        return result;
    }
}
