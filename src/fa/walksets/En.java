package fa.walksets;

import enums.Symbol;

public class En
{
   private Symbol e;
   
   public En (Symbol symbol)
   {
      e = symbol;
   }

   public Symbol getSymbol()
   {
      return e;
   }

   @Override
   public String toString()
   {
      return "registerE = < " + e + " >";
   }

   public void setSymbol(Symbol symbol)
   {
      e = symbol;
   }
}
