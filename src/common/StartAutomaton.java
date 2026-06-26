package common;

public class StartAutomaton
{
  final static int NUMBER_OF_STEPS = 18;
  
   public static void main(String[] args)
   {
      Automaton automaton = new Automaton();
      automaton.init();
      // automaton.initPSFn();
      System.out.println(automaton.toString());
      
      for( int i = 0 ; i < NUMBER_OF_STEPS - 1; i++)
      {
         System.out.println("========== step start ==========");
         automaton.step();
         System.out.println(automaton.toString());
         System.out.println("========== step end ===========");
      }
   }
}

