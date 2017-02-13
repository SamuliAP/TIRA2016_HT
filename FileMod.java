/*
 * Class FileMod
 *
 * Class for file modification and interpretion (reading from, and writing to a file).
 * Also for creating a command list from a text file.
 *
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class FileMod {
    
    /*
     * Method readInput
     *
     * Reads a .txt file, creates and returns
     * an array list of commands accordingly
     *
     */
    public ArrayList readInput(String source){
        String line;
        ArrayList<Command> commands = new ArrayList<>();
	try{
            BufferedReader br = new BufferedReader( new FileReader(source));
            while((line = br.readLine()) != null){
                
                String[] values=line.split(" ");
                Command l;
                switch (values.length) {
                    case 1:
                    {
                        l = new Command(values[0]);
                        break;
                    }
                    case 3:
                    {
                        l = new Command(values[0], values[1], values[2]);
                        break;
                    }
                    default:
                    {
                        l = new Command(true);
                        break;
                    }
                }
                commands.add(l);
            }
	} 
        catch(IOException e){
            return null;
	}
        
        return commands;
    }

    /*
     *==========================================================================
     */
    
    /*
     * Method writeOutput
     * 
     * Creates a new file (if necessary), 
     * and writes the given String to said file.
     * 
     */
    
    public void writeOutput(String source, String s){
        try(BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(source), "UTF-8"))) { 		
            bw.write(s);
        } 
        catch (IOException e){
            System.err.format("IOException: %s%n", e);
        }
    }
}
