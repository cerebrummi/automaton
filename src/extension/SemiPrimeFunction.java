package extension;

import enums.Symbol;

public class SemiPrimeFunction
{
   private SemiPrimeFunction()
   {
      // nothing
   }
   
   public static boolean doSemiPrimeFunction(boolean firstHit, int omegaLowerCaseHits, Symbol symbol)
   {
      if(firstHit && omegaLowerCaseHits == 1)
      {
         return true;
      }

      if(omegaLowerCaseHits == 2 && Symbol.S.compareTo(symbol) != 0)
      {
         return true;
      }
      
      return false;
   }
}
