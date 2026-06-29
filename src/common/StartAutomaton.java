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
   // these numbers are save and fast: 10_000, 16, 6
  static final int NUMBER_OF_STEPS = 5;
  static final int FROZEN_WINDOW_AFTER_CP = 16;
  static final int FROZEN_WINDOW_AFTER_PSF = 6;
  
   public static void main(String[] args)
   {
      Automaton automaton = new Automaton();
      
      StringJoiner joiner = new StringJoiner("\n");
      joiner.add(automaton.getCsvHeader());
      
      automaton.firstInitStep(FROZEN_WINDOW_AFTER_CP, FROZEN_WINDOW_AFTER_PSF);
      joiner.add(automaton.toString());
      
      for( int i = 0 ; i < NUMBER_OF_STEPS - 1; i++)
      {
         automaton.step();
         joiner.add(automaton.toString());
      }
      
      File file = new File("automaton_expansion.csv");
      
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

