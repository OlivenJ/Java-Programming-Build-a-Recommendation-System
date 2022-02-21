import java.util.*;

/**
 * Write a description of MovieRunnerSimilarRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerSimilarRatings {
    public void printAverageRatings(){
        FourthRatings fr = new FourthRatings("data/ratings.csv"); //movie first rating second
        MovieDatabase.initialize("ratedmoviesfull");
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        
        System.out.println("Read data for "+RaterDatabase.size() + " raters.");
        System.out.println("Read data for " + movies.size() + " movies.");
        
        
        
        ArrayList<Rating> result = fr.getAverageRatings(35);
        
        
        
        int j = 1;
        while(j < result.size()){
    
            for(int i = 0; i < result.size()-1; i ++){
                Rating rate1 = result.get(i);
                Rating rate2 = result.get(i+1);
        
                if(rate1.getValue() > rate2.getValue()){
                    result.set(i+1, rate1);
                    result.set(i, rate2);
                }
    
            }
            j ++;
        }
        

        ArrayList<Rating> nonZeroResult = new ArrayList<Rating>();
        
        for(int i = 0; i < result.size(); i ++){
            if(result.get(i).getValue() > 0.0){
                //System.out.println(MovieDatabase.getTitle(result.get(i).getItem()) + " Rating: " + result.get(i).getValue());
                //result.remove(i);
                nonZeroResult.add(result.get(i));
            }
        }
        
        System.out.println(nonZeroResult.size());
    
    }
    
    public void printAverageRatingByYearAfterAndGenre(){
        FourthRatings fr = new FourthRatings("data/ratings.csv"); //movie first rating second
        MovieDatabase.initialize("ratedmoviesfull");
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        
        System.out.println("Read data for "+RaterDatabase.size() + " raters.");
        System.out.println("Read data for " + movies.size() + " movies.");
        
        Filter genreFilter = new GenreFilter("Drama");
        Filter yearFilter = new YearAfterFilter(1990);
        
        AllFilters filterList = new AllFilters();
        
        
        filterList.addFilter(genreFilter);
        filterList.addFilter(yearFilter);
        
        ArrayList<Rating> result = fr.getAverageRatingsByFilter(8, filterList);
        System.out.println("Found "+result.size() + " movies.");
        
        int j = 1;
        while(j < result.size()){
    
            for(int i = 0; i < result.size()-1; i ++){
                Rating rate1 = result.get(i);
                Rating rate2 = result.get(i+1);
        
                if(rate1.getValue() > rate2.getValue()){
                    result.set(i+1, rate1);
                    result.set(i, rate2);
                }
    
            }
            j ++;
        }
        
    
        ArrayList<Rating> nonZeroResult = new ArrayList<Rating>();
        for(int i = 0; i < result.size(); i ++){
            if(result.get(i).getValue() > 0.0){
                //        System.out.println(MovieDatabase.getTitle(result.get(i).getItem()) +" " + MovieDatabase.getYear(result.get(i).getItem()) +  " Rating: " + result.get(i).getValue());
                //        System.out.println("\t" + MovieDatabase.getGenres(result.get(i).getItem()));
                //result.remove(i);
                nonZeroResult.add(result.get(i));
            }
        }
        
        List<Rating> printResult = nonZeroResult.subList(0,11);
        System.out.println(nonZeroResult.size());
    }
    
    public void printSimilarRatings(){
        FourthRatings fr = new FourthRatings("data/ratings.csv"); // specify the rate file
        MovieDatabase.initialize("ratedmoviesfull"); // specify the movie file
        
        String raterID = "71";
        int numSimilar = 20;
        int minimuRater = 5;
        
        ArrayList<Rating> result = fr.getSimilarRatings(raterID, numSimilar, minimuRater);
        int counter = 0;
        ArrayList<Rating> finalResult = new ArrayList<Rating>();
        
        for(Rating r: result){
            String movieID = r.getItem();
                if(r.getValue() > 0.0){
                    //System.out.println(MovieDatabase.getTitle(movieID) + " Ratings: " + r.getValue() );
                    //counter ++;
                    finalResult.add(r);
                }
        }   
        
        
        
        //List<Rating> printResult[finalResult.size()];
        
        if(finalResult.size()<11){
            for(Rating r: finalResult){
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " ,Ratings: " + r.getValue() );
        }
        }
        else {
            for(int i = 0; i < 11; i++){
            Rating r = finalResult.get(i);
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " ,Ratings: " + r.getValue() );
            }
        }

        //System.out.println(finalResult.size());
    }
    
    public void printSimilarRatingsByGenre(){
        FourthRatings fr = new FourthRatings("data/ratings.csv"); // specify the rate file
        MovieDatabase.initialize("ratedmoviesfull"); // specify the movie file
        
        String raterID = "964";
        int numSimilar = 10;
        int minimuRater = 5;
        Filter f = new GenreFilter("Mystery");
        
        ArrayList<Rating> result = fr.getSimilarRatingsByFilter(raterID, numSimilar, minimuRater, f);
        int counter = 0;
        ArrayList<Rating> finalResult = new ArrayList<Rating>();
        
        for(Rating r: result){
            String movieID = r.getItem();
                if(r.getValue() > 0.0){
                    //System.out.println(MovieDatabase.getTitle(movieID) + " Ratings: " + r.getValue() );
                    //counter ++;
                    finalResult.add(r);
                }
        }   
        
        
        
           
        if(finalResult.size()<11){
                for(Rating r: finalResult){
                System.out.println(MovieDatabase.getTitle(r.getItem()) + " ,Ratings: " + r.getValue() );
            }
            }
            else {
                for(int i = 0; i < 11; i++){
                Rating r = finalResult.get(i);
                System.out.println(MovieDatabase.getTitle(r.getItem()) + " ,Ratings: " + r.getValue() );
                }
            }
    }
    
        public void printSimilarRatingsByDirector(){
        FourthRatings fr = new FourthRatings("data/ratings.csv"); // specify the rate file
        MovieDatabase.initialize("ratedmoviesfull"); // specify the movie file
        
        String raterID = "120";
        int numSimilar = 10;
        int minimuRater = 2;
        Filter f = new DirectorFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        
        ArrayList<Rating> result = fr.getSimilarRatingsByFilter(raterID, numSimilar, minimuRater, f);
        int counter = 0;
        ArrayList<Rating> finalResult = new ArrayList<Rating>();
        
        for(Rating r: result){
            String movieID = r.getItem();
                if(r.getValue() > 0.0){
                    //System.out.println(MovieDatabase.getTitle(movieID) + " Ratings: " + r.getValue() );
                    //counter ++;
                    finalResult.add(r);
                }
        }   
        
        if(finalResult.size()<11){
            for(Rating r: finalResult){
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " ,Ratings: " + r.getValue() );
        }
        }
        else {
            for(int i = 0; i < 11; i++){
            Rating r = finalResult.get(i);
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " ,Ratings: " + r.getValue() );
            }
        }
    }
    
    public void printSimilarRatingsByGenreAndMinutes(){
        FourthRatings fr = new FourthRatings("data/ratings.csv"); // specify the rate file
        MovieDatabase.initialize("ratedmoviesfull"); // specify the movie file
        
        String raterID = "168";
        int numSimilar = 10;
        int minimuRater = 3;
        Filter genref = new GenreFilter("Drama");
        Filter minutef = new MinutesFilter(80, 160);
        
        AllFilters filterList = new AllFilters();
        filterList.addFilter(genref);
        filterList.addFilter(minutef);
        
        //filterList.addFilter(genref, minutef);

        
        ArrayList<Rating> result = fr.getSimilarRatingsByFilter(raterID, numSimilar, minimuRater, filterList);
        int counter = 0;
        ArrayList<Rating> finalResult = new ArrayList<Rating>();
        
        for(Rating r: result){
            String movieID = r.getItem();
                if(r.getValue() > 0.0){
                    //System.out.println(MovieDatabase.getTitle(movieID) + " Ratings: " + r.getValue() );
                    //counter ++;
                    finalResult.add(r);
                }
        }   
        if(finalResult.size()<11){
            for(Rating r: finalResult){
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " ,Ratings: " + r.getValue() );
        }
        }
        else {
            for(int i = 0; i < 11; i++){
            Rating r = finalResult.get(i);
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " ,Ratings: " + r.getValue() );
            }
        }
    }
    
    public void printSimilarRatingsByYearAfterAndMinutes(){
        FourthRatings fr = new FourthRatings("data/ratings.csv"); // specify the rate file
        MovieDatabase.initialize("ratedmoviesfull"); // specify the movie file
        
        String raterID = "314";
        int numSimilar = 10;
        int minimuRater = 5;
        Filter yearf = new YearAfterFilter(1975);
        Filter minutef = new MinutesFilter(70, 200);
        
        AllFilters filterList = new AllFilters();
        filterList.addFilter(yearf);
        filterList.addFilter(minutef);
        
        ArrayList<Rating> result = fr.getSimilarRatingsByFilter(raterID, numSimilar, minimuRater, filterList);
        int counter = 0;
        ArrayList<Rating> finalResult = new ArrayList<Rating>();
        
        for(Rating r: result){
            String movieID = r.getItem();
                if(r.getValue() > 0.0){
                    //System.out.println(MovieDatabase.getTitle(movieID) + " Ratings: " + r.getValue() );
                    //counter ++;
                    finalResult.add(r);
                }
        }   
        
        if(finalResult.size()<11){
            for(Rating r: finalResult){
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " ,Ratings: " + r.getValue() );
        }
        }
        else {
            for(int i = 0; i < 11; i++){
            Rating r = finalResult.get(i);
            System.out.println(MovieDatabase.getTitle(r.getItem()) + " ,Ratings: " + r.getValue() );
            }
        }
    }
}
