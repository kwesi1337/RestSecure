/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;


import entity.Quote;
import exceptions.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import static javax.ws.rs.core.Response.Status.*;

/**
 *
 * @author josephawwal
 */
public class Facade {
    
    
    private static Map<Integer, Quote> quotes = new HashMap(){
        
        {
            
            put(1, new Quote("Lorem ipsum dolor sit amet"));
            put(2, new Quote("Do not take life too seriously. You will not get out alive"));
            put(3, new Quote("Behind every great man"));
            
        }
    };
    
    private static final Random rand = new Random();
    
    public static void createQuote(Quote quote){
        
        quotes.put(quotes.size() + 1, quote);
        
    }
    
    public static Collection<Quote> getQuotes(){
        
        return quotes.values();
        
    }
    
    public static Quote getQuote(int id) throws QuoteException {
        
        if (quotes.containsKey(id)){
            
            return quotes.get(id);
        }
        throw new QuoteException("The quote wasn't found.", NOT_FOUND);
    }
    
    public static Quote getRandomQuote() throws QuoteException{
        
        if (quotes.size() > 0){
            
            int randIndex = rand.nextInt(quotes.size()) + 1;
            return quotes.get(randIndex);
            
            
        }
        
        throw new QuoteException("No quotes were found", NO_CONTENT);
    }
    
    public static Quote deleteQuote(int id) throws QuoteException{
        
        if (quotes.containsKey(id)){
            
            Quote q = quotes.get(id);
            quotes.remove(id);
            return q;
        }
         throw new QuoteException("The quote was not found",NOT_FOUND);
    }
    
    public static Quote updateQuote(Quote quote) throws QuoteException{
        
        if (quotes.containsKey(quote.getId())){
            
            quotes.put(quote.getId(), quote);
            return quote;
            
        }
        
        throw new QuoteException("The quote was not found",NOT_FOUND);
    }
}
