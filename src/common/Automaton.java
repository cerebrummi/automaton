package common;

import enums.Symbol;
import extension.MobiusFunction;
import extension.PSFn;
import extension.PSFnOutput;
import extension.SemiPrimeFunction;
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
   private En registerE = new En(null, registerN);
   private CPn tapeCPn = new CPn();

   private PSFn tapePSFn = new PSFn();
   private PSFnOutput registerPSFout = new PSFnOutput();
   private boolean tapePSFnActive = false;

   private int startFrozenWindowCPn;
   private boolean frozenWindowCPnActive = false;

   private int startFrozenWindowPSFn;
   private boolean frozenWindowPSFnActive = false;

   private boolean mobiusFunctionActive = false;
   private int mobiusFunctionCurrentValue_Mu = 1;
   private int mertensFunctionCurrentValue = 1;
   
   private boolean semiprimeFunctionActive = false;
   private boolean isSemiPrime;
   
   private int primeCounter = 0;
   private int semiPrimeCounter = 0;
   private int primeSquareFreeCounter = 1;

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

   public void initFrozenWindowCPn(int start)
   {
      frozenWindowCPnActive = true;
      startFrozenWindowCPn = start;
   }

   public void initFrozenWindowPSFn(int start)
   {
      frozenWindowPSFnActive = true;
      startFrozenWindowPSFn = start;
   }

   public void initMobiusFunction()
   {
      if (tapePSFnActive == true)
      {
         mobiusFunctionActive = true;
      }
      else
      {
         System.err.println("Mobius Function needs PSFn");
         System.exit(1);
      }
   }
   
   public void initSemiprimeFunction()
   {
      if (tapePSFnActive == true)
      {
         semiprimeFunctionActive = true;
      }
      else
      {
         System.err.println("SemiPrime Function needs PSFn");
         System.exit(1);
      }
   }

   public void step()
   {
      registerN.plusOne();

      // register update
      if (Symbol.L.equals(tapeCPn.getFirst().getSymbol()))
      {
         registerE.getEntity().setSymbol(Symbol.P);
         primeCounter++;
      }
      else if (Symbol.M.equals(tapeCPn.getFirst().getSymbol()))
      {
         registerE.getEntity().setSymbol(Symbol.M);
      }
      registerE.getEntity()
            .setOmegaLowerCaseHits(tapeCPn.getFirst().getOmegaLowerCaseHits());
      registerE.getEntity()
            .setPrimesHit(tapeCPn.getFirst().getPrimesHit());
      registerE.getEntity().setFirstHit(tapeCPn.getFirst().isFirstHit());

      tapeCPn.shift_S();

      if (Symbol.P.equals(registerE.getEntity().getSymbol()))
      {
         if (frozenWindowCPnActive && registerN.getN() > startFrozenWindowCPn)
         {
            // no expansion X
         }
         else
         {
            tapeCPn.expansion_X(registerN.getN());
         }
         tapeCPn.filter_F(registerN.getN());
      }

      if (tapePSFnActive)
      {
         // register update
         if (Symbol.C.equals(tapePSFn.getFirst()))
         {
            registerPSFout.setSymbol(Symbol.F);
            primeSquareFreeCounter++;
         }
         else if (Symbol.S.equals(tapePSFn.getFirst()))
         {
            registerPSFout.setSymbol(Symbol.S);
         }

         tapePSFn.shift_S();

         if (Symbol.F.equals(registerPSFout.getSymbol()))
         {
            if (frozenWindowPSFnActive
                  && registerN.getN() > startFrozenWindowPSFn)
            {
               // no expansion X
            }
            else
            {
               tapePSFn.expansion_X(registerN.getN());
            }
            tapePSFn.filter_F(registerN.getN());
         }

         if (mobiusFunctionActive)
         {
            mobiusFunctionCurrentValue_Mu = MobiusFunction.doMobiusFunction(
                  registerPSFout.getSymbol(),
                  registerE.getEntity().getOmegaLowerCaseHits());
            
            mertensFunctionCurrentValue += mobiusFunctionCurrentValue_Mu;
         }
         
         if (semiprimeFunctionActive)
         {
            isSemiPrime = SemiPrimeFunction.doSemiPrimeFunction(
                  registerE.getEntity().calculateOmegaUpperCaseHits(registerN.getN()));
            if(isSemiPrime)
            {
               semiPrimeCounter++;
            }
         }
      }
   }

   @Override
   public String toString()
   {
      return "automaton\n" + registerN.toString() 
            + "\n" + registerE.toString()
            + "\nPrime counter = " + primeCounter 
            + "\n" + tapeCPn.toString().substring(0, 13)
            + (tapePSFnActive
                  ? "\n" + registerPSFout.toString() 
                  + "\nPrimeSquareFree counter = " + primeSquareFreeCounter
                  + "\n" + tapePSFn.toString().substring(0, 12)
                  : "")
            + (mobiusFunctionActive
                  ? "\nMobius Function = "
                        + String.valueOf(mobiusFunctionCurrentValue_Mu)
                        + "\nMertens Function = "
                        + String.valueOf(mertensFunctionCurrentValue)
                  : "")
            + (semiprimeFunctionActive
                  ? "\nSemiprime = " + String.valueOf(isSemiPrime) 
                  + "\nSemiprime counter = " + semiPrimeCounter
                  : "");
   }

}
