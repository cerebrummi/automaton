package common;

public class StartAutomaton
{
  final static int NUMBER_OF_STEPS = 5;
  
   public static void main(String[] args)
   {
      Automaton automaton = new Automaton();
      System.out.println(automaton.toString());
      
      for( int i = 0 ; i < NUMBER_OF_STEPS; i++)
      {
         System.out.println("========== step start ==========");
         automaton.step();
         System.out.println(automaton.toString());
         System.out.println("========== step end ===========");
      }
   }
}

