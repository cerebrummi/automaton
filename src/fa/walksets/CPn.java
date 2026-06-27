package fa.walksets;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.LinkedList;

import enums.Symbol;

public class CPn
{
   private LinkedList<Entity> list = new LinkedList<>();

   public void addFirstSymbol()
   {
      list.add(new Entity(Symbol.L));
   }

   public Entity getFirst()
   {
      return list.getFirst();
   }

   public void shift_S()
   {
      Entity element = list.pollFirst();
      list.add(element);
   }

   public void expansion_X(int n)
   {
      final Entity[] array = (Entity[]) Array.newInstance(Entity.class,
            list.size());
      Entity[] copy = list.toArray(array);
      
      for (int i = 0; i < n - 1; i++)
      {
         for (Entity element : copy)
         {
            list.add(new Entity(element));
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

      for (int i = n - 1; i < size; i += n)
      {
         if (Symbol.L.equals(list.get(i).getSymbol()))
         {
            list.get(i).setSymbol(Symbol.M);
            list.get(i).setFirstHit(true);
         }
         else
         {
            list.get(i).setOmegaLowerCaseHits(
                  list.get(i).getOmegaLowerCaseHits() + 1);
            list.get(i).setFirstHit(false);
         }
         list.get(i).addPrimesHit(n);
      }
   }

   @Override
   public String toString()
   {
      return "CPn = < " + Arrays.toString(list.toArray()) + " >";
   }
}
