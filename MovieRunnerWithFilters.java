import java.util.*;
/**
 * Write a description of MovieRunnerWithFilters here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MovieRunnerWithFilters {
    
    public void printAverageRatings(){
        ThirdRatings tr = new ThirdRatings("ratings"); //movie first rating second
        MovieDatabase.initialize("ratedmoviesfull");
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        
        System.out.println("Read data for "+tr.getRaterSize() + " raters.");
        System.out.println("Read data for " + movies.size() + " movies.");
        
        
        
        ArrayList<Rating> result = tr.getAverageRatings(35);
        
        
        
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
    
    
    public void printAverageRatingsByYear(){
        ThirdRatings tr = new ThirdRatings("ratings"); //movie first rating second
        MovieDatabase.initialize("ratedmoviesfull");
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        
        System.out.println("Read data for "+tr.getRaterSize() + " raters.");
        System.out.println("Read data for " + movies.size() + " movies.");
        
        Filter f = new YearAfterFilter(2000);
        
        
        ArrayList<Rating> result = tr.getAverageRatingsByFilter(20, f);
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
                //System.out.println(MovieDatabase.getTitle(result.get(i).getItem()) +" " + MovieDatabase.getYear(result.get(i).getItem()) +  " Rating: " + result.get(i).getValue());
                //result.remove(i);
                nonZeroResult.add(result.get(i));
            }
        }
        
        System.out.println(nonZeroResult.size());
    }
    
    
    public void printAverageRatingsByGenre(){
        ThirdRatings tr = new ThirdRatings("ratings"); //movie first rating second
        MovieDatabase.initialize("ratedmoviesfull");
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        
        System.out.println("Read data for "+tr.getRaterSize() + " raters.");
        System.out.println("Read data for " + movies.size() + " movies.");
        
        Filter f = new GenreFilter("Comedy");
        
        
        ArrayList<Rating> result = tr.getAverageRatingsByFilter(20, f);
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
                //System.out.println(MovieDatabase.getTitle(result.get(i).getItem()) + " Rating: " + result.get(i).getValue());
                //System.out.println("\t" + MovieDatabase.getGenres(result.get(i).getItem()));
                //result.remove(i);
                nonZeroResult.add(result.get(i));
            }
        }
        
        System.out.println(nonZeroResult.size());
    }
    
    
    public void printAverageRatingByMinutes(){
        ThirdRatings tr = new ThirdRatings("ratings"); //movie first rating second
        MovieDatabase.initialize("ratedmoviesfull");
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        
        System.out.println("Read data for "+tr.getRaterSize() + " raters.");
        System.out.println("Read data for " + movies.size() + " movies.");
        
        Filter f = new MinutesFilter(105, 135);
        
        
        ArrayList<Rating> result = tr.getAverageRatingsByFilter(5, f);
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
                //System.out.println(MovieDatabase.getTitle(result.get(i).getItem()) +" Times: " + MovieDatabase.getMinutes(result.get(i).getItem()) +  " Rating: " + result.get(i).getValue());
                //result.remove(i);
                nonZeroResult.add(result.get(i));
            }
        }
        
        System.out.println(nonZeroResult.size());
    }
    
    
    public void printAverageRatingByDirector(){
        ThirdRatings tr = new ThirdRatings("ratings"); //movie first rating second
        MovieDatabase.initialize("ratedmoviesfull");
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        
        System.out.println("Read data for "+tr.getRaterSize() + " raters.");
        System.out.println("Read data for " + movies.size() + " movies.");
        
        Filter f = new DirectorFilter("Clint Eastwood,Joel Coen,Martin Scorsese,Roman Polanski,Nora Ephron,Ridley Scott,Sydney Pollack");
        
        
        ArrayList<Rating> result = tr.getAverageRatingsByFilter(4, f);
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
                //System.out.println(MovieDatabase.getTitle(result.get(i).getItem()) + " Rating: " + result.get(i).getValue());
                //System.out.println("\t" + MovieDatabase.getDirector(result.get(i).getItem()));
                //result.remove(i);
                nonZeroResult.add(result.get(i));
            }
        }
        
        System.out.println(nonZeroResult.size());
    }
    
    
    public void printAverageRatingByYearAfterAndGenre(){
        ThirdRatings tr = new ThirdRatings("ratings"); //movie first rating second
        MovieDatabase.initialize("ratedmoviesfull");
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        
        System.out.println("Read data for "+tr.getRaterSize() + " raters.");
        System.out.println("Read data for " + movies.size() + " movies.");
        
        Filter genreFilter = new GenreFilter("Drama");
        Filter yearFilter = new YearAfterFilter(1990);
        
        AllFilters filterList = new AllFilters();
        
        
        filterList.addFilter(genreFilter);
        filterList.addFilter(yearFilter);
        
        ArrayList<Rating> result = tr.getAverageRatingsByFilter(8, filterList);
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
        
        System.out.println(nonZeroResult.size());
    }
    
    
    public void printAverageRatingByDirectorsAndMinutes(){
        ThirdRatings tr = new ThirdRatings("ratings"); //movie first rating second
        MovieDatabase.initialize("ratedmoviesfull");
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        
        System.out.println("Read data for "+tr.getRaterSize() + " raters.");
        System.out.println("Read data for " + movies.size() + " movies.");
        
        Filter directorFilter = new DirectorFilter("Clint Eastwood,Joel Coen,Tim Burton,Ron Howard,Nora Ephron,Sydney Pollack");
        Filter minutesFilter = new MinutesFilter(90, 180);
        
        AllFilters filterList = new AllFilters();
        
        
        filterList.addFilter(directorFilter);
        filterList.addFilter(minutesFilter);
        
        ArrayList<Rating> result = tr.getAverageRatingsByFilter(3, filterList);
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
                //        System.out.println(MovieDatabase.getTitle(result.get(i).getItem()) +" Times: " + MovieDatabase.getMinutes(result.get(i).getItem()) +  " Rating: " + result.get(i).getValue());
                //        System.out.println("\t" + MovieDatabase.getDirector(result.get(i).getItem()));
                //result.remove(i);
                nonZeroResult.add(result.get(i));
            }
        }
        
        
        System.out.println(nonZeroResult.size());
    }
}
