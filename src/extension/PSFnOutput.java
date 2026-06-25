package extension;

import enums.Symbol;

public class PSFnOutput
{
   private Symbol symbol;

   public Symbol getSymbol()
   {
      return symbol;
   }

   public void setSymbol(Symbol s)
   {
      this.symbol = s;
   }

   @Override
   public String toString()
   {
      return "registerPSF = < " + symbol + " >";
   }
}
