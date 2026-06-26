package common;

import enums.Symbol;
import extension.PSFn;
import extension.PSFnOutput;
import fa.walksets.En;
import fa.walksets.Nn;
import fa.walksets.CPn;

/**
 * 
 * @author Heeren, B. (2026). On The Nature Of Primes: A deterministic,
 *         endogenous, non-stationary S-adic Automaton for the sieve of
 *         Eratosthenes. Zenodo. https://doi.org/10.5281/zenodo.19503276
 * 
 */

public class Automaton
{
   private Nn registerN = new Nn(0);
   private En registerE = new En(null);
   private CPn tapeCPn = new CPn();

   private PSFn tapePSFn = new PSFn();
   private PSFnOutput registerPSFout = new PSFnOutput();
   private boolean tapePSFnActive = false;
   private int lastPrime = 1;

   public void init()
   {
      registerN.plusOne();
      registerE.setFirstSymbol();
      tapeCPn.addFirstSymbol();
   }

   public void initPSFn()
   {
      tapePSFnActive = true;
      tapePSFn.addSymbol(Symbol.C);
      registerPSFout.setSymbol(Symbol.F);
   }

   public void step()
   {
      registerN.plusOne();

      // register update
      if (Symbol.L.equals(tapeCPn.getFirst().getSymbol()))
      {
         registerE.getEntity().setSymbol(Symbol.P);
      }
      else if (Symbol.M.equals(tapeCPn.getFirst().getSymbol()))
      {
         registerE.getEntity().setSymbol(Symbol.M);
      }
      registerE.getEntity().setOmegaLowerCaseHits(tapeCPn.getFirst().getOmegaLowerCaseHits());

      tapeCPn.shift_S();

      if (Symbol.P.equals(registerE.getEntity().getSymbol()))
      {
         tapeCPn.expansion_X(registerN.getN());
         tapeCPn.filter_F(registerN.getN());
      }

      if (tapePSFnActive)
      {
         // register update
         if (Symbol.C.equals(tapePSFn.getFirst()))
         {
            registerPSFout.setSymbol(Symbol.F);
         }
         else if (Symbol.S.equals(tapePSFn.getFirst()))
         {
            registerPSFout.setSymbol(Symbol.S);
         }

         tapePSFn.shift_S();

         if (Symbol.F.equals(registerPSFout.getSymbol()))
         {
            tapePSFn.expansion_X(registerN.getN());
            tapePSFn.filter_F(registerN.getN(), lastPrime);
         }
      }

      if (tapePSFnActive && Symbol.P.equals(registerE.getEntity().getSymbol()))
      {
         lastPrime = registerN.getN();
      }
   }

   @Override
   public String toString()
   {      
      return "automaton\n" + registerN.toString() + "\n" + registerE.toString()
            + "\n" + tapeCPn.toString().substring(0, 12)
            + (tapePSFnActive
                  ? "\n" + registerPSFout.toString() + "\n"
                        + tapePSFn.toString()
                  : "");
   }
}
