package edu.pitt.blackjackParty;
/**
 * @author Adam O'Bryan
 * CS445 - Spring 2016
 */
import java.util.Random;

public class RandIndexQueue <T> implements MyQ<T>, Indexable<T>, Shufflable{

    T[] theBag;
    //T[] discardPile;
    private int NumBagItems = 0;

    
    // CONSTRUCTOR   
    public RandIndexQueue (int size) {
        theBag = (T[])new Object[size];
        //discardPile = (T[])new Object[size];
        NumBagItems = 0;
    }

    
    
    public boolean full(){
        return theBag.length <= NumBagItems; 
    }
    
    
    public boolean addItem(T item){
        if(!full()){
            
            theBag[NumBagItems] = item;
            NumBagItems++;
            return true;
        }
        else 
        return false;  
    }
  
    
    public boolean empty(){
        return NumBagItems<1;
    }
    
    
    public T removeItem(){
        T itemBeingRemoved = null;
        if(!empty())
        {
            itemBeingRemoved = theBag[0]; 
            NumBagItems--;
            //shiftSpotsFromAbove
            for(int x = 0; x<NumBagItems;x++)
            {
                theBag[x]=theBag[x+1];
            }
        }
        return itemBeingRemoved;
    }
   
    
    public boolean inBoundsChecker(int index){
        if(index<0 && index<=NumBagItems-1)
            return true;
      
        else //(IndexOutOfBoundsException){
            return false;
            //throw new IndexOutOfBoundsException(); 
    }
    
    
    public void replaceSpot(int bagIndex){
            theBag[bagIndex] = theBag[NumBagItems-1];
            theBag[NumBagItems-1] = null;
            NumBagItems--;
    }
   
    
    public void shiftSpotsFromAbove(int bagIndex){
        if(!empty())
        {
            for(int x = bagIndex;x<NumBagItems-1;x++)
            {
                theBag[x] = theBag[x+1];
            }
        }
        theBag[NumBagItems-1] = null;    
    }

    
    public int size(){
        return (int) NumBagItems;
    }
    
    
    public void clear(){
        for (int x=0; x<theBag.length; x++)
        theBag[x]=null;
        NumBagItems = 0;
    }
    
  
    public T get(int i){
        return theBag[i];
    }
    
    
    public void set(int i, T item){
        //check if inBounds TODO
       theBag[i] = item;
    }

    
    public void shuffle(){
        int NumItemsCopy = NumBagItems;
        Object[] bag2 = new Object [NumBagItems];
        Random randomInt = new Random();
        for(int x=0;x<NumBagItems;x++)
        {
            int gotoSpot = randomInt.nextInt(NumItemsCopy);
            T bagItem = removeFrom(gotoSpot);
            bag2[x] = bagItem;
            NumItemsCopy--;
        }
        for(int x=0;x<NumBagItems;x++)
        {
            theBag[x] = (T) bag2[x];
        }  
    }
    
    
    private T removeFrom(int Index){
      //this method does not lower the item count!!!
        T itemBeingRemoved = null;
        if(!empty()){
            itemBeingRemoved = theBag[Index];
            //shiftSpotsFromAbove 
            for(int x = Index; x<NumBagItems-1;x++)
            {
                theBag[x]=theBag[x+1];
            }
            theBag[NumBagItems-1]= null;
        }
        return itemBeingRemoved;  
    }   
  
    
    public String toString(){
        String bagString = "Contents:";
        for(int x=0;x<NumBagItems;x++)
        {
            bagString += " "+theBag[x];
        }
        return bagString;
    }
// end normal index queue
    
    public T endPick(){
       // System.out.println("endpick");   ///****
        T theCard = theBag[NumBagItems-1];
        theBag[NumBagItems-1]=null;
        NumBagItems--;
        return theCard;
    }
    
    
    
    
}//END MAIN CLASS
    
