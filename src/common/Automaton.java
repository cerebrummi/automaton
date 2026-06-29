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

   public void firstInitStep(int startFreezeCPNn, int startFreezePSFn)
   {
      registerN.plusOne();
      registerE.setFirstSymbol();
      tapeCPn.addFirstSymbol();
      
      initPSFn();
      initFrozenWindowCPn(startFreezeCPNn);
      initFrozenWindowPSFn(startFreezePSFn);
      initMobiusFunction();
      initSemiprimeFunction();
      
      System.out.println(registerN.getN() + " "+ tapePSFn.toFullString());
   }

   private void initPSFn()
   {
      tapePSFnActive = true;
      tapePSFn.addSymbol(Symbol.C);
      registerPSFout.setSymbol(Symbol.F);
   }

   private void initFrozenWindowCPn(int start)
   {
      frozenWindowCPnActive = true;
      startFrozenWindowCPn = start;
   }

   private void initFrozenWindowPSFn(int start)
   {
      frozenWindowPSFnActive = true;
      startFrozenWindowPSFn = start;
   }

   private void initMobiusFunction()
   {
      mobiusFunctionActive = true;
   }
   
   private void initSemiprimeFunction()
   {
      semiprimeFunctionActive = true;
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

      if (frozenWindowCPnActive && registerN.getN() > startFrozenWindowCPn)
      {
         tapeCPn.shift_S_star();
      }
      else
      {
         tapeCPn.shift_S();
      }

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

         if (frozenWindowPSFnActive
               && registerN.getN() > startFrozenWindowPSFn)
         {
            tapePSFn.shift_S_star();
         }
         else
         {
            tapePSFn.shift_S();
         }
         

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
         
         System.out.println(registerN.getN() + " "+ tapePSFn.toFullString());

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
   
   public String getCsvHeader()
   {
      String header = "n," + registerE.getCsvHeader() + "prime_counter," + tapeCPn.getCsvHeader() 
            + (tapePSFnActive? "register_psf_out,prime_square_free_counter,tape_psf_n_start,":"")
            + (mobiusFunctionActive? "mobius_function_mu,mertens_function,":"")
            + (semiprimeFunctionActive? "semiprime,semiprime_counter":"");
      
      return header;
   }

   @Override
   public String toString()
   {     
      return registerN.toString()
            + "," + registerE.toString()
            + "," + primeCounter
            + "," + tapeCPn.toString()
            + (tapePSFnActive?
                    "," + registerPSFout.toString()
                  + "," + primeSquareFreeCounter
                  + "," + tapePSFn.toString():"")
            + (mobiusFunctionActive? 
                  "," + String.valueOf(mobiusFunctionCurrentValue_Mu)
                + "," + String.valueOf(mertensFunctionCurrentValue):"")
            + (semiprimeFunctionActive?
                    "," + String.valueOf(isSemiPrime) +"," + semiPrimeCounter:""); 
   }

}
