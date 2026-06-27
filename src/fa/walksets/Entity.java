package fa.walksets;

import enums.Symbol;

public class Entity
{
   private Symbol symbol;
   private int omegaLowerCaseHits;
   private boolean firstHit = true;

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

   @Override
   public String toString()
   {
      return symbol.name() + " " + omegaLowerCaseHits + "x";
   }
}