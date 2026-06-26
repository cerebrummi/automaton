package enums;

/** Symbols 
 * 
 * The symbol L = live means undetermined whether prime or composite number. The
 * symbol M means composite (multiple) number and the symbol P means prime number. The
 * symbol ONE means the number 1 which is not a prime number.
 * 
 * S = prime square found
 * C = candidate (still unknown)
 * F = free of prime square
 */

public enum Symbol
{
   ONE(0), L(1), M(1), P(1), 
   S(0), C(0), F(0);
   
   int omegaLowerCaseHitsStart;
   
   Symbol(int omegaLowerCaseHitsStart)
   {
      this.omegaLowerCaseHitsStart = omegaLowerCaseHitsStart;
   }

   public int getOmegaLowerCaseHitsStart()
   {
      return omegaLowerCaseHitsStart;
   }
   
   
}
