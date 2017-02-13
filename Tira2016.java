/*
 * TIRA 2016 course work
 * Samuli Anola-Pukkila
 * 421612
 * Anola-Pukkila.Samuli.A@student.uta.fi 
 *
 * Class tira2016
 *
 * Contains the main method, interacts with FileMod, MinHeap and Command classes
 * to initialize and modify a priority queue from an input text file containing commands, 
 * and then writes down the results in an output text file.
 *
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Tira2016{
    
    // Class constants for error messages.
    private static final String ERROR = "Virheellinen syöte.\r\n";
    private static final String EMPTY = "Jono on tyhjä.\r\n";
    
    /*
     *===============================================================================================
     */
    
    /*
     * Method checkCommand
     * 
     * Checks if the command line drawn from an input file is valid and
     * returns a boolean value accordingly.
     *
     */
    public static boolean checkCommand(Command cmd){
        char c;
        
        // Checks for initial error caused by class FileMod tagging
        // the command line for having incorrect amount of arguments.
        if(cmd.isError()){
            return false;
        }
        
        // Checks if the first argument is of proper length (1), and
        // asssigns it to a char variable. 
        if(cmd.getCommand().length() == 1){
            c = cmd.getCommand().charAt(0);
        }
        else{
            return false;
        }
        
        // If the command is 'i' (as in insert), tries to parse the 
        // second argument to an Integer variable. Also checks if the
        // Integer is >0.
        if(c == 'i'){
            try{
                int key = Integer.parseInt(cmd.getKey());
                if(key <= 0){
                    return false;
                }
            }
            catch(NumberFormatException e){
                return false;
            }
            // Checks that the third argument, which is a String, is not null.
            if(cmd.getData() == null){
                return false;
            }
        }
        // If the first argument is not 'i', checks for the other options and
        // ensures that no other arguments were given.
        else if((c != 'r' && c != 'p' && c != 'm' && c != 'q' && c != 's') || cmd.getKey() != null || cmd.getData() != null){
            return false;
        }
        
        // If the command passed the criteria, return true.
        return true;
    }
    
    /*
     *===============================================================================================
     */
    
    /*
     * Method execute
     * 
     * Initializes the heap, executes the commands read from an Arraylist
     * and returns the complete String to be written on the output text file.
     *
     */
    public static String execute(ArrayList commands){
        // Initialize heap.
        MinHeap queue = new MinHeap();
        // Initialize a list of Integers to keep track of already used keys.
        List<Integer> keys = new ArrayList<>();
        // Initialize the StringBuilder which will contain the output.
        StringBuilder output = new StringBuilder();
        
        // Iterate trough the arraylist, which contains the commands.
        OUTER:
        for (Object x : commands) {
            if (x instanceof Command) {
                Command cmd = (Command)x;
                // Checks the validity of given command line.
                if (!checkCommand(cmd)) {
                    output.append(ERROR);
                }
                // If the command line is valid, start interpreting it.
                else {
                    char c = cmd.getCommand().charAt(0);
                    switch (c) {
                        
                        // Insert
                        case 'i':
                            int key = 0;
                            // This souldn't catch anything.
                            try{
                                key = Integer.parseInt(cmd.getKey());
                            }
                            catch(NumberFormatException e){
                                System.out.println(e);
                            }
                            String data = cmd.getData();
                            // Check for duplicate keys.
                            if(key != 0 && !keys.contains(key)){
                                keys.add(key);
                                queue.insertItem(key, data);
                                output.append("(").append(key).append(",").append(data).append(") lis.\r\n");
                            }
                            else{
                                output.append("Avain ").append(key).append(" on jo jonossa.\r\n");
                            }   break;
                            
                        // Remove
                        case 'r':
                            if(!queue.isEmpty()){
                                BTNode ret = queue.removeMin();
                                Iterator<Integer> i = keys.iterator();
                                while(i.hasNext()){
                                    Integer o = i.next();
                                    if(o == ret.getKey()){
                                        i.remove();
                                    }
                                }
                                output.append("(").append(ret.getKey()).append(",").append(ret.getData()).append(") poistettu.\r\n");
                            }
                            else{
                                output.append(EMPTY);
                            }   break;
                            
                        // Size
                        case 's':
                            if(!queue.isEmpty()){
                                output.append("Jonon koko on: ").append(queue.size()).append(".\r\n");
                            }
                            else{
                                output.append(EMPTY);
                            }   break;
                            
                        // MinKey
                        case 'm':
                            if(!queue.isEmpty()){
                                output.append("Pienin alkio on (").append(queue.minKey()).append(",").append(queue.minElem()).append(").\r\n");
                            }
                            else{
                                output.append(EMPTY);
                            }   break;
                            
                        // Print in preorder
                        case 'p':
                            if(!queue.isEmpty()){
                                output.append(queue.preorderPrint());
                            }
                            else{
                                output.append(EMPTY);
                            }   break;
                            
                        // quit
                        case 'q':
                            output.append("Ohjelma lopetettu.");
                            break OUTER;
                            
                        default:
                            break;
                    }
                }
            }
        }
        // Returns the complete string.
        return output.toString();
    }
    
    /*
     *===============================================================================================
     */
    
    /*
     * The main method
     * 
     * If proper command line arguments are given, reads the file trough
     * class FileMod and calls method execute with given commands.
     *
     */
    public static void main(String[] args){
        FileMod file = new FileMod();
        
        if(args.length == 2){
            String inputFileName = args[0];
            String outputFileName = args[1];
            ArrayList commands = file.readInput(inputFileName);
            if(commands != null){
                file.writeOutput(outputFileName, execute(commands));
            }
            else{
                file.writeOutput(outputFileName, "Tiedostoa ei löydetty.");
            }
        }
        else{
            System.out.println("Virheelliset komentoriviargumentit.");
        }
    }
    
}