package fa.walksets;

import java.util.Arrays;
import java.util.HashSet;
import enums.Symbol;

public class Entity
{
   private Symbol symbol;
   private int omegaLowerCaseHits;
   private boolean firstHit = true;
   private HashSet<Integer> primesHit = new HashSet<>();
   private boolean isPrimesHitCloned = false;

   public Entity()
   {
      // first register En Symbol when registerEn is ONE
      this.symbol = Symbol.ONE;
      this.omegaLowerCaseHits = Symbol.ONE.getOmegaLowerCaseHitsStart();
      this.firstHit = false;
   }

   public Entity(Entity element)
   {
      // clone
      this.symbol = element.symbol;
      this.omegaLowerCaseHits = element.omegaLowerCaseHits;
      this.firstHit = false;
      this.primesHit = element.primesHit;
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

   @SuppressWarnings("unchecked")
   public void addPrimesHit(int prime)
   {
      if(!isPrimesHitCloned)
      {
         this.primesHit = (HashSet<Integer>) this.primesHit.clone();
         isPrimesHitCloned = true;
      }
      
      this.primesHit.add(prime);
   }

   public HashSet<Integer> getPrimesHit()
   {
      return primesHit;
   }

   public void setPrimesHit(HashSet<Integer> primesHit)
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
      
      Integer[] primes = primesHit.toArray(new Integer[0]);
      
      Arrays.sort(primes);

      for (int i = 0; i < primes.length; i++)
      {
         while (n % primes[i] == 0)
         {
            n = n / primes[i];
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
}