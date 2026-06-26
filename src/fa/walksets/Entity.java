package fa.walksets;

import enums.Symbol;

public class Entity
{
   private Symbol symbol;
   private int omegaLowerCaseHits;

   public Entity()
   {
      // first register En Symbol when registerEn is ONE
      this.symbol = Symbol.ONE;
      this.omegaLowerCaseHits = Symbol.ONE.getOmegaLowerCaseHitsStart();
   }

   public Entity(Entity element)
   {
      // clone
      this.symbol = element.symbol;
      this.omegaLowerCaseHits = element.omegaLowerCaseHits;
   }

   public Entity(Symbol symbol)
   {
      // first CPn Symbol when registerEn is ONE
      this.symbol = Symbol.L;
      this.omegaLowerCaseHits = Symbol.L.getOmegaLowerCaseHitsStart();
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

   @Override
   public String toString()
   {
      return symbol.name() + " " + omegaLowerCaseHits + "x";
   }
}