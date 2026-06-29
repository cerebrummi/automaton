package extension;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;

import enums.Symbol;

/**
 * prime square free
 */
public class PSFn
{
   private LinkedList<Symbol> list = new LinkedList<>();

   public void addSymbol(Symbol symbol)
   {
      list.add(symbol);
   }

   public Symbol getFirst()
   {
      return list.getFirst();
   }

   public void shift_S()
   {
      Symbol element = list.pollFirst();
      list.add(element);
   }
   
   public void shift_S_star()
   {
      list.pollFirst();
   }

   public void expansion_X(int n)
   {
      final int nSquare = n * n;

      final Symbol[] array = (Symbol[]) Array.newInstance(Symbol.class,
            list.size());
      Symbol[] copy = list.toArray(array);

      for (int i = 0; i < nSquare - 1; i++)
      {
         for (Symbol element : copy)
         {
            list.add(element);
         }
      }
   }

   public void filter_F(int currentStepN)
   {
      final int nSquare = currentStepN * currentStepN;
      final int firstHit = nSquare - currentStepN;

      final int size = list.size();

      if (firstHit < size && Symbol.C.equals(list.get(firstHit - 1))) // stop fistHit when window frozen:  firstHit < size
      {
         list.set(firstHit - 1, Symbol.S);
      }

      for (int i = firstHit - 1; i < size; i += nSquare)
      {
         if (Symbol.C.equals(list.get(i)))
         {
            list.set(i, Symbol.S);
         }
      }
   }

   @Override
   public String toString()
   {
      return list.getFirst().name();
   }
   
   public String toFullString()
   {
      return Arrays.deepToString(list.toArray());
   }
}
