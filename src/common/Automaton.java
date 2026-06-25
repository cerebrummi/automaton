package common;

import enums.Symbol;
import fa.walksets.En;
import fa.walksets.Nn;
import fa.walksets.CPn;

/** 
 * 
 * @author Heeren, B. (2026). 
 * On The Nature Of Primes: A deterministic, endogenous, non-stationary S-adic Automaton 
 * for the sieve of Eratosthenes. 
 * Zenodo. https://doi.org/10.5281/zenodo.19503276
 * 
 */

public class Automaton
{
   // initialization
   private Nn registerN = new Nn(0);
   private En registerE = new En(null);
   private CPn tapeCPn = new CPn();

   public Automaton()
   {
      // starting state
      registerN.plusOne();
      registerE.setSymbol(Symbol.ONE);
      tapeCPn.addSymbol(Symbol.L);
   }

   public void step()
   {
      registerN.plusOne();

      if (Symbol.L.equals(tapeCPn.getFirst()))
      {
         registerE.setSymbol(Symbol.P);
      }
      else if (Symbol.M.equals(tapeCPn.getFirst()))
      {
         registerE.setSymbol(Symbol.M);
      }

      tapeCPn.shift_S();

      if (Symbol.P.equals(registerE.getSymbol()))
      {
         tapeCPn.expansion_X(registerN.getN());
         tapeCPn.filter_F(registerN.getN());
      }
   }
   
   @Override
   public String toString()
   {
      return "automaton\n"+registerN.toString()+"\n"+registerE.toString()+"\n"+tapeCPn.toString();
   }
}
