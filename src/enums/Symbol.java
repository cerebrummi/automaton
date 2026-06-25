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
   ONE, L, M, P, 
   S, C, F;
}
