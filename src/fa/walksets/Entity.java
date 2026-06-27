package fa.walksets;

import java.util.LinkedList;

import enums.Symbol;

public class Entity
{
   private Symbol symbol;
   private int omegaLowerCaseHits;
   private boolean firstHit = true;
   private LinkedList<Integer> primesHit = new LinkedList<>();

   public Entity()
   {
      // first register En Symbol when registerEn is ONE
      this.symbol = Symbol.ONE;
      this.omegaLowerCaseHits = Symbol.ONE.getOmegaLowerCaseHitsStart();
      this.firstHit = false;
   }

   @SuppressWarnings("unchecked")
   public Entity(Entity element)
   {
      // clone
      this.symbol = element.symbol;
      this.omegaLowerCaseHits = element.omegaLowerCaseHits;
      this.firstHit = false;
      this.primesHit = (LinkedList<Integer>) element.primesHit.clone();
   }

   public Entity(Symbol symbol)
   {
      // first CPn Symbol when registerEn is ONE
      this.symbol = Symbol.L;
      this.omegaLowerCaseHits = Symbol.L.getOmegaLowerCaseHitsStart();
      this.firstHit = false;
   }

   public Symbol getSymbol()
   {
      return symbol;
   }

   public void setSymbol(Symbol symbol)
   {
      this.symbol = symbol;
      this.omegaLowerCaseHits = symbol.getOmegaLowerCaseHitsStart();
   }

   public Integer getOmegaLowerCaseHits()
   {
      return omegaLowerCaseHits;
   }

   public void setOmegaLowerCaseHits(Integer omegaLowerCaseHits)
   {
      this.omegaLowerCaseHits = omegaLowerCaseHits;
   }

   public boolean isFirstHit()
   {
      return firstHit;
   }

   public void setFirstHit(boolean firstHit)
   {
      this.firstHit = firstHit;
   }

   public void addPrimesHit(int prime)
   {
      this.primesHit.add(prime);
   }

   public LinkedList<Integer> getPrimesHit()
   {
      return primesHit;
   }

   public void setPrimesHit(LinkedList<Integer> primesHit)
   {
      this.primesHit = primesHit;
   }

   public int calculateOmegaUpperCaseHits(int n)
   {
      if (Symbol.P.equals(symbol))
      {
         return 1;
      }

      int copyN = n;
      int result = 0;

      for (int i = 0; i < primesHit.size(); i++)
      {
         while (n % primesHit.get(i) == 0)
         {
            n = n / primesHit.get(i);
            result++;
         }
         n = copyN;
      }
      return result;
   }
   
   public int calculateLiouville(int omegaUpperCaseHits)
   { 
      if((omegaUpperCaseHits % 2) == 0)
      {
         return 1;
      }
      return -1;
   }

   @Override
   public String toString()
   {
      return symbol.name() + " " + omegaLowerCaseHits + "x";
   }


}