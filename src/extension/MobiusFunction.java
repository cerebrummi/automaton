package extension;

import enums.Symbol;

public class MobiusFunction
{
   private MobiusFunction()
   {
      // nothing
   }
   
   public static int doMobiusFunction(Symbol symbol, int omegaLowerCaseHits)
   {
      if(Symbol.S.equals(symbol))
      {
         return 0;
      }
      else
      {
         if(omegaLowerCaseHits % 2 == 0)
         {
            return 1;
         }
         else
         {
            return-1;
         }
      }
   }
}
