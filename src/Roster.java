/**
 * Roster
 *
 * @author Emma Wolff
 * @version 29 Feb 2020
 */

import java.util.Scanner;
 public class Roster {
  /**
 * to use for comparison
 */
  protected int classSize = getMaxStudent();
  protected static String add = new String("a");
  protected static String remove = new String("r");
  protected static int remove_student_index;
  protected static String display = new String("d");
  protected static String quit = new String("q");
  protected static LinkedList<String> students = new LinkedList<String>();

  /**
   * Ask the user to specify the maximum size for the class roster
   * 
   * @return classSize to use for comparison 
   * 
   *
   */
  public int getMaxStudent() {     
    //final int classSize;
    Scanner user_classSize = new Scanner(System.in); // Create a Scanner object
    System.out.println("What is the maximum size for your roster?");
    final int classSize = user_classSize.nextInt(); // Read user input
    return classSize;
  }

  /**
   * Repeatedly get a command from the user if the command is legal, carry out
   * that command using the roster. If the command is illegal, get a new command.
   * 
   * 
   */
  public void getCommand() {           
    Scanner user_Command = new Scanner(System.in); // Create a Scanner object
    System.out.println("Enter [a/r/d/q]: (a)dd, (r)emove, (d)isplay, (q)uit");
    String user_command_input = user_Command.nextLine();
    /**
     * a to add a student to the class; this should lead to another prompt to enter
     * the student name (The program expects data in Lastname, Firstname format (no
     * execptions)) (As soon as the roster reaches the specified maximum size, a
     * message should be printed saying the roster is full, and the program should
     * quit, without any other input asked for.)
     */
    if (user_command_input.equals(add)) {                       
      /// see if roster is full before adding
      if (classSize <= students.size()) {
        // if full print message and quit (with no further input)
        System.out.println("Sorry the roster is full!");
        System.exit(0);
       //get user input on who to add to the class
      } else {
        Scanner user_add_student = new Scanner(System.in); // Create a Scanner object
        System.out.println("Enter the name of the student to add to the roster in Last Name, First Name format:");
        String add_student = user_add_student.nextLine();
        addCaller(add_student);
      }
    } else if (user_command_input.equals(remove)) {
      Scanner user_remove_student = new Scanner(System.in); // Create a Scanner object
      System.out.println("Enter the name of the student to remove from the roster in Last Name, First Name format:");
      String remove_student = user_remove_student.nextLine();
      /**
       * r to remove a student from the class; this should lead to another prompt to
       * enter the student name
       */
      removeCaller(remove_student);
    } else if (user_command_input.equals(display)) {
      /**
       * d to display the roster All student names on the roster should be displayed
       * in the form “Lastname, Firstname”. The roster must be kept in alphabetical
       * order by last name.
       */
      System.out.println(students);
      // gets additional call
      getCommand();
    } else if (user_command_input.equals(quit)) {
      /**
       * q to quit the program -DONE
       */
      System.exit(0);
    }
    /**
     * Repeatedly get a command from the user if the command is legal, carry out
     * that command using the roster. If the command is illegal, get a new command.
     */
    else {
      System.out.println("Invalid command, try again");
      getCommand();
    }
  }

  /**
   * add user to students
   * 
   * @param Name for name to add
   * 
   *
   */
  public void addCaller(String Name) {
    int student_int = students.size();
    if (student_int == 0) {
      students.add(0, Name);
      if (classSize <= students.size()) {
        // if full print message and quit (with no further input)
        System.out.println("Sorry the roster is full!");
        System.exit(0);
      }
      getCommand();
    }
    // otherwise
    else {
      // sort the list alphabetically & insert the name after the
      // appropriate node
      sortingAlpha(students, Name);
    }
  }

  /**
   * removeCaller
   * 
   * @param students    for list
   * @param add_student to sort
   * 
   *
   */
  public void removeCaller(String remove_student) {
    // make it actually remove this student
    // get the index I need to delete
    // make for loop to sort through ndoes until data point matches
    int i = 0;
    while (i < classSize) {
      String indexCheck = students.get(i);
      if (indexCheck.equals(remove_student)) {
        students.delete(i);
        break;
      }
      i++;
    }
  }

  /**
   * sortingAlpha
   * 
   * @param students    for list
   * @param add_student to sort
   * 
   *
   */
  public void sortingAlpha(LinkedList<String> students, String add_student) {
    // boolean shows if the list is sorted
    boolean sorted = false;
    //set first node for comparison
    int student_int = students.size();
    String firstNodeString = students.get(0);
   // LinkedListNode<String> firstNode = students.get(0);
    //if node needs to be added to first position
    if (add_student.compareToIgnoreCase(firstNodeString) < 0) {
      // create a new node
      // set the data of the node to be the name
      students.add(0 ,add_student);
      // insert the new node at the first node position
      // set sorted to true
      if (classSize <= students.size()) {
        // if full print message and quit (with no further input)
        System.out.println("Sorry the roster is full!");
        System.exit(0);
      }
      sorted = true;
      //getCommand();
    }
    // if the name goes at the end of the list, is second item
    else if (student_int == 1 & add_student.compareToIgnoreCase(firstNodeString) > 0) {
      // insert the new data at the last node position
      students.add(student_int, add_student);
      // set sorted to true
      if (classSize <= students.size()) {
        // if full print message and quit (with no further input)
        System.out.println("Sorry the roster is full!");
        System.exit(0);
      }
      sorted = true;
    }
      // while it's not sorted
      while (!sorted) {
        // if the name goes after the current node
        //comparing name with first node
        for(int x = 0; x < student_int; x++){
          if (add_student.compareToIgnoreCase(students.get(x)) < 0){
            int newIndex = x;
            students.add(newIndex, add_student);
            if (classSize <= students.size()) {
              // if full print message and quit (with no further input)
              System.out.println("Sorry the roster is full!");
              System.exit(0);
            }
            sorted = true;
          }
        }
      }
      getCommand();
    }

  public static void main(String[] args) {
    Roster myRoster = new Roster();
    myRoster.getCommand();
}
}