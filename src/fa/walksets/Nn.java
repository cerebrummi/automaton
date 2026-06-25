package fa.walksets;

public class Nn
{
   private int n;

   public Nn(int n)
   {
      this.n = n;
   }

   public void plusOne()
   {
      n++;
   }

   @Override
   public String toString()
   {
      return "registerN = < " + n + " >";
   }

   public int getN()
   {
      return n;
   }
}
