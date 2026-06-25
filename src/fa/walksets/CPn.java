package fa.walksets;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;

import enums.Symbol;

public class CPn
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
   
   public void expansion_X(int n)
   {
      final Symbol[] array = (Symbol[]) Array.newInstance(Symbol.class, list.size());
      Symbol[] copy =  list.toArray(array);
      
      for(int i = 0; i < n - 1; i++)
      {
         for(Symbol element : copy)
         {
            list.add(element);
         }
      }
   }
   
   public void filter_F(int n)
   {
      /**
       * This reference implementation of change uses only counting (jumping), 
       * no division is necessary.
       */
      final int size = list.size();
      
      for(int i = n-1; i < size; i += n )
      {
         if(Symbol.L.equals(list.get(i)))
         {
            list.set(i, Symbol.M);
         }
      }
   }
   
   @Override
   public String toString()
   {
      return "CPn = < " + Arrays.toString(list.toArray()) + " >";
   }
}
