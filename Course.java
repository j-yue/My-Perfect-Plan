/** Course.java
  * Represents a course object, including course name, semester offered,
  * and list of prerequisites.
  * 
  * Primarily developed by Sam Mincheva.
  * 
  * CS 230 Fall 2014 Final Project
  * 
  * @implements Comparable<Course>
  * @author Jeanette Yue, Sam Mincheva
  */

import java.util.*; //for LinkedList

public class Course implements Comparable<Course> {
  
  private String name;
  private boolean isFall, isSpring;
  private LinkedList<Course> prerequisites;
  private LinkedList<String> prerequisiteNames; //used for import reading
  
  //-------------------------------------------------------------------------------
  /** Initializes a new Course object with the specified name and values for
    * fall and spring availability.
    * 
    * @param name the name of the new course object
    * @param fall true if course is offered in fall, false otherwise
    * @param spring true if course is offered in spring, false otherwise
    */
  public Course(String name, boolean fall, boolean spring) {
    this.name = name;
    this.isFall = fall;
    this.isSpring = spring;
    this.prerequisites = new LinkedList<Course>();
    this.prerequisiteNames = new LinkedList<String>();
  }
  
  
  //-------------------------------------------------------------------------------
  /** Initializes a new Course object with the specified name and values for
    * fall and spring availability, as well as list of prerequisites.
    * 
    * @param name the name of the new course object
    * @param fall true if course is offered in fall, false otherwise
    * @param spring true if course is offered in spring, false otherwise
    * @param prereqs a list of this course's prerequisite courses
    */
  public Course(String name, boolean fall, boolean spring, LinkedList<Course>
                prereqs) {
    this(name, fall, spring);
    this.prerequisites = prereqs;
  }
  
  //-------------------------------------------------------------------------------
  /** Sets this  course's name to the specified string.
    * 
    * @param name the new name for this course
    */
  public void setName(String name) {
    this.name = name;
  }
  
  //-------------------------------------------------------------------------------
  /** Sets this  course's fall value to the specified value.
    * 
    * @param fall the new fall value for this course
    */
  public void setIsFall(boolean fall) {
    this.isFall = fall;
  }
  
  //-------------------------------------------------------------------------------
  /** Sets this  course's spring value to the specified value.
    * 
    * @param spring the new spring value for this course
    */
  public void setIsSpring(boolean spring) {
    this.isSpring = spring;
  }
  
  //-------------------------------------------------------------------------------
  /** Adds the specified course to the list of prerequisites for this course.
    * 
    * @param c the course to be added as a prerequisite
    */
  public void addPrerequisite(Course c) {
    if (!prerequisites.contains(c)) {
    this.prerequisites.add(c);
    }
  }
  
  //-------------------------------------------------------------------------------
  /** Returns the name of this course.
    * 
    * @return this course object's name
    */
  public String getName() {
    return this.name;
  }
  
  //-------------------------------------------------------------------------------
  /** Checks whether or not this course is offered in the fall.
    * 
    * @return true if course is offered in the fall, false otherwise
    */
  public boolean getIsFall() {
    return this.isFall;
  }
  
  //-------------------------------------------------------------------------------
  /** Checks whether or not this course is offered in the spring.
    * 
    * @return true if course is offered in the spring, false otherwise
    */
  public boolean getIsSpring() {
    return this.isSpring;
  }
  
  //-------------------------------------------------------------------------------
  /** Returns a list of this Course's prerequisites.
    * 
    * @return this course's prerequisites
    */
  public LinkedList<Course> getPrerequisites() {
    return this.prerequisites;
  }
  
  //-------------------------------------------------------------------------------
  /** Sets the list of this Course's prerequisites.
    * 
    * @param prereq the new list of prerequisites
    */
  public void setPrerequisites(LinkedList<Course> prereqs) {
    this.prerequisites = prereqs;
  }
  
  //-------------------------------------------------------------------------------
  /** Removes a specified course from this course's list of prerequisites.
    * 
    * @param c the course to be removed
    * @throws NoSuchElementException if the course is not there
    */
  public void removePrerequisite(Course c) {
    prerequisites.remove(c);
  }
  
  //-------------------------------------------------------------------------------
  /** Adds the specified course name to the list of this course's prerequisite
    * names.
    * 
    * @param s the course name
    */
  public void addPrerequisiteName(String s) {
    this.prerequisiteNames.add(s);
  }
  
  //-------------------------------------------------------------------------------
  /** Returns a list of all prerequsiste course's names.
    * 
    * @return list of this course's prerequisite course's names
    */
  public LinkedList<String> getPrerequisiteNames() {
    return this.prerequisiteNames;
  }
  
  //-------------------------------------------------------------------------------
  /** Checks whether this course object is the same as the specified other. In this
    * implementation, two courses are the same if they have the same name.
    * 
    * @return true if the two courses are the same, false otherwise
    */
  public boolean equals(Course another) {
    return this.name.equals(another.name);
  }
   
  //-------------------------------------------------------------------------------
  /** Compares this course to another. In this implementation, the difference 
    * between two courses is the difference in their names in ASCII order.
    * 
    * @return the difference between the two courses
    */
  public int compareTo(Course another) {
    return this.name.compareTo(another.name);
  }
  
  //-------------------------------------------------------------------------------
  /** Returns a string representation of this course object.
    * 
    * @return course string
    */
  public String toString(){
    String result = getNameString() + " " + getSemesterString() + " " + 
      getPrereqString();
    return result;
  }
  
  //-------------------------------------------------------------------------------
  /** Returns a formatted string with this course's name.
    * Helper for toString().
    */
  private String getNameString() {
    String s = " " + name;
    while (s.length() < 10) s += " ";
    return s;
  }
  
  //-------------------------------------------------------------------------------
  /** Returns a formatted string with this course's semester availability.
    * Helper for toString().
    */
  private String getSemesterString() {
    String s = "";
    if (this.isFall) s += "Fall";
    if (this.isSpring) s += ((this.isFall) ? ", " : "") + "Spring";
    while (s.length() < 13) s += " ";
    return s;
  }
  
  //-------------------------------------------------------------------------------
  /** Returns a formatted string with this course's list of prerequisites.
    * Helper for toString().
    */
  private String getPrereqString() {
    String s = "Prerequisites: ";
    if (prerequisites.isEmpty()) s += "None";
    else {
      for (int i = 0; i < prerequisites.size(); i++) {
        if (i < prerequisites.size() - 1) 
          s += prerequisites.get(i).getName() + ", ";
        else s += prerequisites.get(i).getName();
      }
    }
    return s; 
  }
}