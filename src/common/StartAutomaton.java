package common;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.StringJoiner;

public class StartAutomaton
{
  static final int NUMBER_OF_STEPS = 100;  
  static final int FROZEN_WINDOW_AFTER_CP = 16; // max 16
  static final int FROZEN_WINDOW_AFTER_PSF = 6; // max 6
  
   public static void main(String[] args)
   {
      Automaton automaton = new Automaton();
      
      automaton.initPSFn();
      automaton.initFrozenWindowCPn(FROZEN_WINDOW_AFTER_CP);
      automaton.initFrozenWindowPSFn(FROZEN_WINDOW_AFTER_PSF);
      automaton.initMobiusFunction();
      automaton.initSemiprimeFunction();
      
      StringJoiner joiner = new StringJoiner("\n");
      automaton.init(); // step
      joiner.add(automaton.toString());
      
      for( int i = 0 ; i < NUMBER_OF_STEPS - 1; i++)
      {
         joiner.add("========== step start ==========");
         automaton.step();
         joiner.add(automaton.toString());
         joiner.add("========== step end ===========");
      }
      
      File file = new File("automaton_expansion.txt");
      
      try(FileOutputStream stream = new FileOutputStream(file);
            OutputStreamWriter writer = new OutputStreamWriter(stream,
                  StandardCharsets.UTF_8);)
      {
         writer.write(joiner.toString());
         writer.flush();
         writer.close();
      }
      catch (UnsupportedEncodingException e)
      {
         e.printStackTrace();
      }
      catch (FileNotFoundException e)
      {
         e.printStackTrace();
      }
      catch (IOException e)
      {
         e.printStackTrace();
      }
   }
}

