/**
 * EnhancedRoster Class
 *
 * @author Emma Wolff
 * @version 29 Feb 2020
 * 
 * 
 * 
 */

 /**
 * The implemented error-checking functionality should
 *  still work on the waitlist, i.e., your program should 
 * guard against removing a name that is not on the roster
 *  or waitlist, and adding a name that is already on the waitlist.
 *  DONE
 */

import java.util.Scanner;
 public class EnhancedRoster extends Roster{
  protected static LinkedList<String> waitlist = new LinkedList<String>();
  /**
 * /**
 * user tries to remove a name 
 * that is not on the roster
 * @param name for student
 * not an override
 */

  public void removeName(String name){
    int i = 0;
    boolean foundName = false;
    while(i < classSize){
      String indexCheck = students.get(i);
      if(indexCheck.equals(name)){
        removeCaller(students, name);
        foundName = true;
        break;
      }
      i++;
    }
    int z = 0;
    //waitlist check
    while(z < waitlist.size()){
      String indexCheck = waitlist.get(z);
      if(indexCheck.equals(name)){
        removeCaller(waitlist, name);
        foundName = true;
        break;
      }
      z++;
    }
    if(foundName == false){
      System.out.println("User not in roster or waitlist!");
      getCommand();
    }
  } 
  

 /**
 * user tries to add a name to the roster that is already there 
 * -- a name should not be on the roster twice
 * @param name for student
 * 
 */
@Override
public void addCaller(String name){
  int doubleCheck = 11;
  int student_int = students.size();
  int i = 0;
  if(student_int == 0){
    students.add(0, name);
    if (classSize <= students.size()) {
      // if full print message and quit (with no further input)
      waitListManager(name);
    }
    getCommand();
} 
// otherwise
  else {
      while(i < student_int){
      String indexCheck = students.get(i);
      if(indexCheck.equals(name)){
        doubleCheck = 12;
        System.out.println("Name cannot be added twice!");
        break;
      }
      i++;
    }
    if (doubleCheck == 11){
    sortingAlpha(students, name); 
    } else if (doubleCheck == 12){
      getCommand();
      }
    }
  // sort the list alphabetically & insert the name after the
  // appropriate node
}

 /**
 * user tries to remove a name from an empty roster
 *
 * @override remove caller
 * @param removeFromThisOne for list maniupulated 
 * @param remove_student for student
 * 
 */
public void removeCaller(LinkedList<String> removeFromThisOne, String remove_student){
  if(removeFromThisOne.size() == 0){
    System.out.println("Cannot remove from empty roster!");
  } else {
// make it actually remove this student
//get the index I need to delete
//make for loop to sort through ndoes until data point matches
    int i = 0;
    while(i < classSize){
      String indexCheck = removeFromThisOne.get(i);
      if(indexCheck.equals(remove_student)){
        removeFromThisOne.delete(i);
        if(removeFromThisOne == students && waitlist != null){
          waitListBump();
        }
        break;
      }
    i++;
    }
  }
  getCommand();
  }


  /**
 * Once the roster is full (holding the specified maximum number of students)
 * any students added subsequently will be added to a second list,
 *  the “wait list”. The wait list should not be kept in alphabetic order
 * The program should no longer end when the roster is full. 
 * The display command should display both the roster and wait list.
 *Not an override
 @param Name  for student
 */
public void waitListManager(String Name){
  System.out.println("Sorry the roster was full, but lets add " + Name + " to the waitlist!");
  int waitlist_size = waitlist.size();
  int z = 0;
    //waitlist check to ensure not duplicate
  while (z < waitlist.size()){
    String indexCheck = waitlist.get(z);
    if(indexCheck.equals(Name)){
      System.out.println("You cannot add a duplicate name to the waitlist.");
      break;
    }
    z++;
  }
  //then add to list
  if (waitlist_size == 0){
    waitlist.add(0, Name);
  } else {
    waitlist.add(waitlist_size, Name);
  }
  getCommand();
}
  /**
 * When a student is removed from a full roster, 
 * if there are students on the waitlist, 
 * the first student on the waitlist should automatically 
 * be added to the roster, placed in correct alphabetical order, 
 * and removed from the waitlist. 
 *not an override
 */
public void waitListBump(){
  String newStudent = waitlist.get(0);
  waitlist.delete(0);
  addCaller(newStudent);
}




/**
 * get command 
 *
 * @override to add name caller
 * 
 */
@Override
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
      Scanner user_add_student = new Scanner(System.in); // Create a Scanner object
      System.out.println("Sorry the roster is full, but you can add this name to the waitlist in Last Name, First Name format:");
      String add_student = user_add_student.nextLine();
      waitListManager(add_student);
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
    if(students.size() == 0){
      System.out.println("Cannot remove from empty list");
      getCommand();
    } else {
      removeName(remove_student);
    }
  } else if (user_command_input.equals(display)) {            
    /**
     * d to display the roster All student names on the roster should be displayed
     * in the form “Lastname, Firstname”. The roster must be kept in alphabetical
     * order by last name.
     */
    System.out.println("The roster is as follows:" + students);
    System.out.println("The waitlist is as follows:" + waitlist);
    //gets additional call
    getCommand();
  } else if (user_command_input.equals(quit)) {                     
    /**
     * q to quit the program
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
   * Override from Roster
   * @param students
   * @param add_student
   */
@Override
public void sortingAlpha(LinkedList<String> students, String add_student){ 
  // boolean shows if the list is sorted
  boolean sorted = false;
  //set first node for comparison
  int student_int = students.size();
  String firstNodeString = students.get(0);
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
      waitListManager(add_student);
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
      System.out.println("Sorry the roster is full, any additonal students will go to waitlist.");
    }
    sorted = true;
  }

    while (!sorted) {
      if (classSize == students.size()) {
        waitListManager(add_student);
      }
      // if the name goes after the current node
      //comparing name with first node
      final int beforeCheck = students.size();
      for(int x = 0; x < student_int; x++){
        if (add_student.compareToIgnoreCase(students.get(x)) < 0){
          int newIndex = x;
          students.add(newIndex, add_student);
          if (classSize == students.size()) {
            // if full print message
            System.out.println("The roster is now full!");
            sorted = true;
          }
          }
        }
        if(beforeCheck == students.size()){
          students.add(beforeCheck, add_student);
          sorted = true;
        }

      }
      getCommand();
    }

public static void main(String[] args) {
  EnhancedRoster myEnhancedRoster = new EnhancedRoster();
  myEnhancedRoster.getCommand();
}
}