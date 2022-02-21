import java.util.*;
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FourthRatings {
    //private ArrayList<Rater> myRaters;
    
    public FourthRatings() {
        // default constructor
        this("ratings");
    }
    
    public FourthRatings(String ratingfile){
        FirstRating fr = new FirstRating();
        RaterDatabase.initialize(ratingfile);
        
        
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
    
    }
    
    public double getAverageByID(String movieID, int minimalRaters){
        double result = 0.0;
        int ratersAmount = 0;
        double totalRating = 0.0;
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
        
            //for(int i = 0; i < ourRaters.size(); i ++){
                for(int i = 0; i < myRaters.size(); i++){
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
    
      
    public double getWeightAverageByID(String movieID, int minimalRaters, List<Rating> similarRaters){
        double result = 0.0;
        int ratersAmount = 0;
        double totalRating = 0.0;
        //ArrayList<Rater> myRaters = RaterDatabase.getRaters();
        
            //for(int i = 0; i < ourRaters.size(); i ++){
                for(Rating r: similarRaters){
                    //if(myRaters.get(i).getItemsRated().contains(movieID)){
                    String  raterID =r.getItem();
                    if(RaterDatabase.getRater(raterID).getItemsRated().contains(movieID)){
                        ratersAmount ++;
                        //totalRating += myRaters.get(i).getRating(movieID);
                        totalRating += (RaterDatabase.getRater(raterID).getRating(movieID)) * (r.getValue()) ;
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
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
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
        ArrayList<Rater> myRaters = RaterDatabase.getRaters();
        
        for(int i = 0; i < primerResult.size(); i ++){
            if(movies.contains(primerResult.get(i).getItem())){
                result.add(primerResult.get(i));
            }
        
        }
        
        return result;
    }
    
    private double dotProduct(Rater me, Rater r){
        ArrayList<String> bothRated = new ArrayList<String>();
        
        ArrayList<String> myRated = me.getItemsRated();
        ArrayList<String> rRated = r.getItemsRated();
        
        //ArrayList<Double> myRatings = new ArrayList<Double>();
        HashMap<String, Double> myScale = new HashMap<String, Double>();
        //ArrayList<Double> rRatings = new ArrayList<Double>();
        HashMap<String, Double> rScale = new HashMap<String, Double>();
        
        double result = 0.0;
        
        for(int i = 0; i < myRated.size(); i ++){
            if(r.hasRating(myRated.get(i))){
            //if(rRated.contains(myRated.get(i))){
                bothRated.add(myRated.get(i));
            }
        }
        
        for(String s: myRated){
            myScale.put(s, me.getRating(s) - 5);
        }
        
        for(String s: rRated){
            rScale.put(s, r.getRating(s) - 5);
        }

        for(String s:bothRated){
            result += myScale.get(s) * rScale.get(s);
        }
        
        return result;
    }
    
    private ArrayList<Rating> getSimilarities(String id){
        Rater baseRater = RaterDatabase.getRater(id);
        ArrayList<Rater> otherRater = RaterDatabase.getRaters();
        otherRater.remove(baseRater);
        ArrayList<Rating> result = new ArrayList<Rating>();
        
        
        for(Rater r: otherRater){
            if(dotProduct(baseRater,r) > 0){
                result.add(new Rating(r.getID(), dotProduct(baseRater,r)));
            }
        }

        //Collections.reverse(result);
        Collections.sort(result,Collections.reverseOrder());
        
        return result;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){
        ArrayList<Rating> result = new ArrayList<Rating>();
        
        
        ArrayList<Rating> similarRaters = getSimilarities(id);
        int numCandidate = 0;
        if(numSimilarRaters > similarRaters.size()){
            numCandidate = similarRaters.size();
        }else{
            numCandidate = numSimilarRaters;
        }
        
        

        String movieID;
        HashSet<String> ratedMovie = new HashSet<String>();
        for(Rating r: similarRaters){
            ArrayList<String> tempRated = RaterDatabase.getRater(r.getItem()).getItemsRated();
            for(String s: tempRated){
                ratedMovie.add(s);
            }
        }
        
        List<Rating> usedRaters = similarRaters.subList(0, numCandidate);

        for(String s : ratedMovie){
            double score = getWeightAverageByID(s, minimalRaters,usedRaters);
            result.add(new Rating(s,score));
        }
        
        
        Collections.sort(result,Collections.reverseOrder());

        
        return result;
        

    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> result = new ArrayList<Rating>();
        ArrayList<String> filtedMovie = MovieDatabase.filterBy(filterCriteria);
        
        
        ArrayList<Rating> similarRaters = getSimilarities(id);
        int numCandidate = 0;
        if(numSimilarRaters > similarRaters.size()){
            numCandidate = similarRaters.size();
        }else{
            numCandidate = numSimilarRaters;
        }
        
        String movieID;
        HashSet<String> ratedMovie = new HashSet<String>();
        for(Rating r: similarRaters){
            ArrayList<String> tempRated = RaterDatabase.getRater(r.getItem()).getItemsRated();
            for(String s: tempRated){
                ratedMovie.add(s);
            }
        }
        
        List<Rating> usedRaters = similarRaters.subList(0, numCandidate);

        for(String s : ratedMovie){
            double score = getWeightAverageByID(s, minimalRaters,usedRaters);
            result.add(new Rating(s,score));
        }
        
        for(int i = 0; i < result.size(); i ++){
            if(result.get(i).getValue() <= 0.0){
                result.remove(i);
            }
        }
        
        ArrayList<Rating> finalResult = new ArrayList<Rating>();
        
        for(Rating r: result){
            if(filtedMovie.contains(r.getItem())){
                finalResult.add(r);
            }
        
        }
        
        Collections.sort(finalResult,Collections.reverseOrder());
        return finalResult;
    
    }
}