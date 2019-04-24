/** Semester.java
  * Represents a semester, including course capacity, whether it is fall,
  * semester year, semester name, and list of courses.
  * 
  * Primarily developed by Sam Mincheva.
  * 
  * CS 230 Fall 2014 Final Project
  * 
  * @author Jeanette Yue, Sam Mincheva
  */

import java.util.*; //for LinkedList

public class Semester {
  
  private int capacity, year;
  private boolean isFall;
  private String name;
  private LinkedList<Course> courses;
  private static final int DEFAULT_CAPACITY = 4;
  
  //-------------------------------------------------------------------------------
  /** Initializes a new Semester object with the specified values for season and
    * year, and a default capacity of 4 courses.
    * 
    * @param fall true if semester is fall, false otherwise
    * @param year the year of the semester
    */
  public Semester(boolean fall, int year) {
    this.name = ((fall) ? "Fall " : "Spring ") + year;
    this.year = year;
    this.capacity = DEFAULT_CAPACITY;
    this.isFall = fall;
    this.courses = new LinkedList<Course>();
  }
  
  //-------------------------------------------------------------------------------
  /** Sets this semester's course capacity to the specified value.
    * 
    * @param cap the desired capacity
    */
  public void setCapacity(int cap) {
    this.capacity = cap;
  }
  
  //-------------------------------------------------------------------------------
  /** Retrieves this semester's course capacity.
    * 
    * @return the course capacity of this semester
    */
  public int getCapacity() {
    return this.capacity;
  }
  
  //-------------------------------------------------------------------------------
  /** Retrieves this semester's year.
    * 
    * @return the year of this semester
    */
  public int getYear() {
    return this.year;
  }
  
  //-------------------------------------------------------------------------------
  /** Sets the isFall variable of this semesters to the specified value.
    * 
    * @param fall the new value for isFall
    */
  public void setIsFall(boolean fall) {
    this.isFall = fall;
  }
  
  //-------------------------------------------------------------------------------
  /** Retrieves this semester's isFall variable.
    * 
    * @return true if this is a fall semester, false otherwise
    */
  public boolean getIsFall() {
    return this.isFall;
  }
  
  //-------------------------------------------------------------------------------
  /** Adds the specified course to this semester's list of courses. 
    * Does nothing if the semester is full.
    * 
    * @param c the course to be added
    */
  public void addCourse(Course c) {
    if (!this.isFull()) {
      courses.add(c);
    }
  }
  
  //-------------------------------------------------------------------------------
  /** Empties a semester's list of courses by assigning it to a new, empty list.
    */
  public void empty() {
    courses = new LinkedList<Course>();
  }
  
  //-------------------------------------------------------------------------------
  /** Checks if the semester is full, i.e. if its current course load is less
    * than the course capacity.
    * 
    * @return true if semester is full, false otherwise
    */
  public boolean isFull() {
    return courses.size() == this.capacity;
  }
  
  //-------------------------------------------------------------------------------
  /** Retrieves the number of remaining course slots in this semester.
    * 
    * @return number of empty course slots
    */
  public int getSlotsRemaining() {
    return this.capacity - this.courses.size();
  }
  
  //-------------------------------------------------------------------------------
  /** Retrieves this semester's name, in the format: "Season Year"
    * 
    * @return the name of this semester
    */
  public String getName(){
    return name;
  }
  
  //-------------------------------------------------------------------------------
  /** Returns the list of courses in this semester.
    * 
    * @return a linkedlist containing this semester's courses
    */
  public LinkedList<Course> getCourses() {
    return courses;
  }
  
  //-------------------------------------------------------------------------------
  /** Returns a string representation of this semester, including name and course
    * capacity.
    * 
    * @return string representation of this semester
    */
  public String toString(){
    return getNameString() + "Courses: " + capacity;
  }
  
  //-------------------------------------------------------------------------------
  /** Returns a string containing the semesters name and list of courses currently
    * in that semester.
    * Used when exporting to a .txt file.
    * 
    * @return string containing this semester's name and courses
    */
  public String printSemester() {
    String s = name + ": ";
    for (int i = 0; i < courses.size(); i++) {
      if (i < courses.size() - 1) 
        s += courses.get(i).getName() + ", ";
      else s += courses.get(i).getName();
    }
    return s;
  }
  
  //-------------------------------------------------------------------------------
  /** Returns a formatted string with this semester's season and year.
    * Helper method for toString().
    */
  private String getNameString() {
    String s = (isFall) ? "Fall" : "Spring";
    while (s.length() < 7) s += " ";
    s += year + " ";
    return s;
  }
  
}