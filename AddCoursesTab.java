/** AddCoursesTab.java
  * Creates a panel that lets the user add and edit courses, see their list of
  * courses, and import and export it from a .txt file.
  * 
  * Primarily developed by Sam Mincheva.
  * 
  * CS 230 Fall 2014 Final Project
  * 
  * @author Jeanette Yue, Sam Mincheva
  */

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;


public class AddCoursesTab extends JPanel {
  //-------------------------------------------------------------------------------
  //instance variables
  private JLabel courseNameLabel, addCourseLabel, editAddCourseLabel, addTitleLabel, 
    editTitleLabel, displayTitleLabel, editCourseNameLabel, ioTitleLabel, prereqLabel, editPrereqLabel;
  private JTextField courseNameField, editCourseNameField, importField, exportField;
  private JCheckBox fallBox, springBox, editFallBox, editSpringBox;
  private JComboBox prereqBox, editPrereqBox, editCourseNameBox;
  private LinkedList<JCheckBox> prereqList, editPrereqList;
  private LinkedList<JLabel> addedCourses;
  private JButton addCourseButton, addPrereqButton, removePrereqButton, 
    editAddPrereqButton, editRemovePrereqButton, editUpdateButton, 
    editCancelButton, editDeleteButton, editSelectButton, exportButton, importButton;
  private JPanel displayPanel, displayTitlePanel, courseListPanel; 
  private JPanel managePanel, ioPanel, ioTitlePanel, importPanel, exportPanel;
  private JPanel addPanel, addTitlePanel, namePanel, semesterPanel, prereqPanel, addPrereqPanel, 
    removePrereqPanel, prereqListPanel, buttonPanel, savePanel;
  private JPanel editPanel, editTitlePanel, editCoursePanel, editSelectPanel, editNamePanel, 
    editSemesterPanel, editPrereqPanel, editAddPrereqPanel, editRemovePrereqPanel, editPrereqListPanel,
    editButtonPanel, editSavePanel;
  private LinkedList<Course> prerequisites, editPrerequisites;
  private Course editCourse;
  private Schedule schedule;
  
  
  //-------------------------------------------------------------------------------
  private final Color backgroundColor1 = new Color(250, 250, 255, 200);
  private final Color backgroundColor2 = new Color(250, 255, 255, 200);
  private final Color backgroundColor3 = new Color(250, 255, 250, 200);
  private final Color foregroundColor1 = new Color(24, 87, 82);
  private final Color foregroundColor2 = new Color(141, 112, 39);
  private final Color foregroundColor3 = new Color(130, 36, 58);
  private final Font labelFontSmall = new Font("Lucida Console", Font.BOLD, 14);
  private final Font labelFontBig = new Font("Lucida Console", Font.BOLD, 18);
  private final Font courseFont = new Font("Lucida Console", Font.BOLD, 16);
  private final Color border =   new Color(32, 64, 104);
  
  //-------------------------------------------------------------------------------
  /** Creates a new AddCoursesTab.
    */
  public AddCoursesTab(Schedule s) {
    
    schedule = s;
    
    addTitleLabel = new JLabel("Add a course:");
    addTitleLabel.setForeground(foregroundColor2);
    addTitleLabel.setFont(labelFontBig);
    addTitlePanel = new JPanel();
    addTitlePanel.setPreferredSize(new Dimension(250, 30));
    addTitlePanel.setBackground(backgroundColor2);
    addTitlePanel.add(addTitleLabel);
    
    courseNameLabel = new JLabel("Course name:");
    courseNameLabel.setForeground(foregroundColor2);
    courseNameLabel.setFont(labelFontSmall);
    
    courseNameField = new JTextField("CS 111", 8);
    courseNameField.setForeground(foregroundColor3);
    courseNameField.setFont(labelFontSmall);
    
    namePanel = new JPanel();
    namePanel.setBackground(backgroundColor2);
    namePanel.add(courseNameLabel);
    namePanel.add(courseNameField);
    
    fallBox = new JCheckBox("Fall");
    fallBox.setToolTipText("Is this course offered in the fall");
    fallBox.setForeground(foregroundColor2);
    fallBox.setFont(labelFontSmall);
    
    springBox = new JCheckBox("Spring");
    springBox.setToolTipText("Is this course offered in the spring");
    springBox.setForeground(foregroundColor2);
    springBox.setFont(labelFontSmall);
    
    semesterPanel = new JPanel();
    semesterPanel.setBackground(backgroundColor2);
    semesterPanel.add(fallBox);
    semesterPanel.add(springBox);
    
    prereqLabel = new JLabel("Prerequisites:");
    prereqLabel.setAlignmentX(CENTER_ALIGNMENT);
    prereqLabel.setFont(labelFontSmall);
    prereqLabel.setForeground(foregroundColor1);
    
    prereqBox = new JComboBox();
    prereqBox.addItem("N/A     ");
    prereqBox.setForeground(foregroundColor2);
    prereqBox.setFont(labelFontSmall);
    
    addPrereqButton = new JButton("Add");
    addPrereqButton.addActionListener(new ButtonListener());
    addPrereqButton.setToolTipText("Add to this course's list of prereqs");
    addPrereqButton.setForeground(foregroundColor2);
    addPrereqButton.setFont(labelFontSmall);
    
    addPrereqPanel = new JPanel();
    addPrereqPanel.setBackground(backgroundColor2);
    addPrereqPanel.add(prereqBox);
    addPrereqPanel.add(addPrereqButton);
    
    prereqList = new LinkedList<JCheckBox>();
    prereqListPanel = new JPanel();
    prereqListPanel.setBackground(backgroundColor2);
    prereqListPanel.setPreferredSize(new Dimension(250, 50));
    
    removePrereqButton = new JButton("Remove Prereq");
    removePrereqButton.addActionListener(new ButtonListener());
    removePrereqButton.setToolTipText("Remove from this course's list of prereqs");
    removePrereqButton.setForeground(foregroundColor2);
    removePrereqButton.setFont(labelFontSmall);
    
    removePrereqPanel = new JPanel();
    removePrereqPanel.setBackground(backgroundColor2);
    removePrereqPanel.setLayout(new BoxLayout(removePrereqPanel, BoxLayout.Y_AXIS));
    removePrereqPanel.add(prereqListPanel);
    removePrereqPanel.add(removePrereqButton);
    
    prereqPanel = new JPanel();
    prereqPanel.setBackground(backgroundColor2);
    prereqPanel.setLayout(new BoxLayout(prereqPanel, BoxLayout.Y_AXIS));
    prereqPanel.add(prereqLabel);
    prereqPanel.add(addPrereqPanel);
    prereqPanel.add(removePrereqPanel);
    
    prerequisites = new LinkedList<Course>();
    
    addCourseButton = new JButton("ADD COURSE");
    addCourseButton.addActionListener(new ButtonListener());
    addCourseButton.setToolTipText("Add this course to your list");
    addCourseButton.setAlignmentX(CENTER_ALIGNMENT);
    addCourseButton.setForeground(foregroundColor3);
    addCourseButton.setFont(labelFontBig);
    
    buttonPanel = new JPanel();
    buttonPanel.setBackground(backgroundColor2);
    buttonPanel.add(addCourseButton);
    
    addCourseLabel = new JLabel("");
    addCourseLabel.setPreferredSize(new Dimension(300, 20));
    
    savePanel = new JPanel();
    savePanel.setLayout(new BoxLayout(savePanel, BoxLayout.Y_AXIS));
    savePanel.setBackground(backgroundColor2);
    savePanel.add(buttonPanel);
    savePanel.add(addCourseLabel);
    
    addPanel = new JPanel();
    addPanel.setLayout(new BoxLayout(addPanel, BoxLayout.Y_AXIS));
    addPanel.setBorder(BorderFactory.createLineBorder(foregroundColor1, 3));
    addPanel.setPreferredSize(new Dimension(300, 250));
    addPanel.setBackground(backgroundColor2);
    
    addPanel.add(addTitlePanel);
    addPanel.add(namePanel);
    addPanel.add(semesterPanel);
    addPanel.add(prereqPanel);
    addPanel.add(savePanel);
    
    
    //-----------------------------------------------------------------------------
    //Creates the edit panel
    
    editTitleLabel = new JLabel("Edit a course:");
    editTitleLabel.setForeground(foregroundColor2);
    editTitleLabel.setFont(labelFontBig);
    
    editTitlePanel = new JPanel();
    editTitlePanel.setPreferredSize(new Dimension(250, 30));
    editTitlePanel.setBackground(backgroundColor3);
    editTitlePanel.add(editTitleLabel);
    
    editCourseNameBox = new JComboBox();
    editCourseNameBox.addItem("N/A     ");
    editCourseNameBox.setFont(labelFontSmall);
    editCourseNameBox.setForeground(foregroundColor2);
    
    editSelectButton = new JButton("Select");
    editSelectButton.addActionListener(new ButtonListener());
    editSelectButton.setFont(labelFontSmall);
    editSelectButton.setForeground(foregroundColor2);
    editSelectButton.setToolTipText("Select a course to edit");
    
    editSelectPanel = new JPanel();
    editSelectPanel.setBackground(backgroundColor3);
    editSelectPanel.add(editCourseNameBox);
    editSelectPanel.add(editSelectButton);
    
    editCourseNameLabel = new JLabel("Course name:");
    editCourseNameLabel.setForeground(foregroundColor2);
    editCourseNameLabel.setFont(labelFontSmall);
    
    editCourseNameField = new JTextField(8);
    editCourseNameField.setForeground(foregroundColor3);
    editCourseNameField.setFont(labelFontSmall);
    
    editNamePanel = new JPanel();
    editNamePanel.setBackground(backgroundColor3);
    editNamePanel.add(editCourseNameLabel);
    editNamePanel.add(editCourseNameField);
    
    editFallBox = new JCheckBox("Fall");
    editFallBox.setFont(labelFontSmall);
    editFallBox.setForeground(foregroundColor2);
    
    editSpringBox = new JCheckBox("Spring");
    editSpringBox.setFont(labelFontSmall);
    editSpringBox.setForeground(foregroundColor2);
    
    editSemesterPanel = new JPanel();
    editSemesterPanel.setBackground(backgroundColor3);
    editSemesterPanel.add(editFallBox);
    editSemesterPanel.add(editSpringBox);
    
    editPrereqLabel = new JLabel("Prerequisites:");
    editPrereqLabel.setAlignmentX(CENTER_ALIGNMENT);
    editPrereqLabel.setFont(labelFontSmall);
    editPrereqLabel.setForeground(foregroundColor1);
    
    editPrereqPanel = new JPanel();
    editPrereqPanel.setBackground(backgroundColor3);
    
    JPanel editAddPrereqPanel = new JPanel();
    editAddPrereqPanel.setBackground(backgroundColor3);
    
    editAddPrereqButton = new JButton("Add");
    editAddPrereqButton.addActionListener(new ButtonListener());
    editAddPrereqButton.setToolTipText("Add to this course's list of prereqs");
    editAddPrereqButton.setFont(labelFontSmall);
    editAddPrereqButton.setForeground(foregroundColor2);
    
    editPrereqBox = new JComboBox(schedule.getCourseNames());
    editPrereqBox.setFont(labelFontSmall);
    editAddPrereqButton.setForeground(foregroundColor2);
    
    editAddPrereqPanel.add(editPrereqBox);
    editAddPrereqPanel.add(editAddPrereqButton);
    
    JPanel editRemovePrereqPanel = new JPanel();
    editRemovePrereqPanel.setBackground(backgroundColor3);
    editPrereqListPanel = new JPanel();
    editPrereqListPanel.setBackground(backgroundColor3);
    
    editPrereqList = new LinkedList<JCheckBox>();   
    
    editRemovePrereqButton = new JButton("Remove prereq");
    editRemovePrereqButton.addActionListener(new ButtonListener());
    editRemovePrereqButton.setToolTipText("Remove from this course's list of prereqs");
    editRemovePrereqButton.setFont(labelFontSmall);
    editRemovePrereqButton.setForeground(foregroundColor2);
    editRemovePrereqPanel.setLayout(new BoxLayout(editRemovePrereqPanel, BoxLayout.Y_AXIS));
    editRemovePrereqPanel.add(editPrereqListPanel);
    editRemovePrereqPanel.add(editRemovePrereqButton);
    
    editPrereqPanel.setLayout(new BoxLayout(editPrereqPanel, BoxLayout.Y_AXIS));
    editPrereqPanel.add(editPrereqLabel);
    editPrereqPanel.add(editAddPrereqPanel);
    editPrereqPanel.add(editRemovePrereqPanel);
    
    editPrerequisites = new LinkedList<Course>();
    
    editUpdateButton = new JButton("Save");
    editUpdateButton.addActionListener(new ButtonListener());
    editUpdateButton.setToolTipText("Update this course's info");
    editUpdateButton.setFont(labelFontSmall);
    editUpdateButton.setForeground(foregroundColor3);
    
    editCancelButton = new JButton("Cancel");
    editCancelButton.addActionListener(new ButtonListener());
    editCancelButton.setToolTipText("Cancel all changes");
    editCancelButton.setFont(labelFontSmall);
    editCancelButton.setForeground(foregroundColor3);
    
    editDeleteButton = new JButton("Delete");
    editDeleteButton.addActionListener(new ButtonListener());
    editDeleteButton.setToolTipText("Delete this course");
    editDeleteButton.setFont(labelFontSmall);
    editDeleteButton.setForeground(foregroundColor3);
    
    editButtonPanel = new JPanel();
    editButtonPanel.setBackground(backgroundColor3);
    editButtonPanel.add(editUpdateButton);
    editButtonPanel.add(editDeleteButton);
    editButtonPanel.add(editCancelButton);
    
    editAddCourseLabel = new JLabel("");
    editAddCourseLabel.setPreferredSize(new Dimension(300, 20));
    
    editSavePanel = new JPanel();
    editSavePanel.setBackground(backgroundColor3);
    editSavePanel.add(editButtonPanel);
    editSavePanel.add(editAddCourseLabel);
    editSavePanel.setLayout(new BoxLayout(editSavePanel, BoxLayout.Y_AXIS));
    
    editPanel = new JPanel();
    editPanel.setLayout(new BoxLayout(editPanel, BoxLayout.Y_AXIS));
    editPanel.add(editTitlePanel);
    editPanel.add(editSelectPanel);
    
    editCoursePanel = new JPanel();
    editCoursePanel.setPreferredSize(new Dimension(250, 200));
    editCoursePanel.add(editNamePanel);
    editCoursePanel.add(editSemesterPanel);
    editCoursePanel.add(editPrereqPanel);
    editCoursePanel.add(editSavePanel);
    
    editPanel.add(editCoursePanel);
    editPanel.setPreferredSize(new Dimension(300,300));
    editPanel.setBorder(BorderFactory.createLineBorder(foregroundColor2,3));
    editCoursePanel.setVisible(false);
    editCoursePanel.setLayout(new BoxLayout(editCoursePanel, BoxLayout.Y_AXIS));
    
    
    //-----------------------------------------------------------------------------
    //Creates the IO panel
    ioTitleLabel = new JLabel("<html>Import/Export Courses:<html>");
    ioTitleLabel.setForeground(foregroundColor2);
    ioTitleLabel.setFont(labelFontBig);
    ioTitlePanel = new JPanel();
    ioTitlePanel.setPreferredSize(new Dimension(250, 20));
    ioTitlePanel.add(ioTitleLabel);
    
    importField = new JTextField("MyCourses", 15);
    importField.setForeground(foregroundColor3);
    importField.setFont(labelFontSmall);
    
    importButton = new JButton("Import");
    importButton.addActionListener(new ButtonListener());
    importButton.setToolTipText("No need to add .txt");
    importButton.setFont(labelFontBig);
    importButton.setForeground(foregroundColor3);
    
    importPanel = new JPanel();
    importPanel.setPreferredSize(new Dimension(300, 20));
    importPanel.add(importField);
    importPanel.add(importButton);
    
    exportField = new JTextField(15);
    exportField.setForeground(foregroundColor3);
    exportField.setFont(labelFontSmall);
    
    exportButton = new JButton("Export");
    exportButton.addActionListener(new ButtonListener());
    exportButton.setToolTipText("No need to add .txt");
        exportButton.setFont(labelFontBig);
    exportButton.setForeground(foregroundColor3);
    
    exportPanel = new JPanel();
    exportPanel.setPreferredSize(new Dimension(250, 20));
    exportPanel.add(exportField);
    exportPanel.add(exportButton);
    
    ioPanel = new JPanel();
    ioPanel.add(ioTitlePanel);
    ioPanel.add(importPanel);
    ioPanel.add(exportPanel);
    ioPanel.setPreferredSize(new Dimension(300, 100));
    ioPanel.setLayout(new BoxLayout(ioPanel, BoxLayout.Y_AXIS));
    ioPanel.setBorder(BorderFactory.createLineBorder(foregroundColor3,3));
    
    managePanel = new JPanel();
    managePanel.add(addPanel);
    managePanel.add(editPanel);
    managePanel.add(ioPanel);
    managePanel.setPreferredSize(new Dimension(300, 700));
    managePanel.setLayout(new BoxLayout(managePanel, BoxLayout.Y_AXIS));
    
    //-----------------------------------------------------------------------------
    //Creates the display semesters panel
    courseListPanel = new JPanel();
    courseListPanel.setBackground(backgroundColor1);
    courseListPanel.setLayout(new BoxLayout(courseListPanel, BoxLayout.Y_AXIS));
    
    addedCourses = new LinkedList<JLabel>();
    for (int i = 0; i < addedCourses.size(); i++) {
      courseListPanel.add(addedCourses.get(i));
    }
    
    displayTitleLabel = new JLabel("Your course list is empty.");
    displayTitleLabel.setFont(labelFontBig);
    displayTitleLabel.setForeground(foregroundColor3);
    
    displayTitlePanel = new JPanel();
    displayTitlePanel.setBackground(new Color(255, 255, 255));
    displayTitlePanel.add(displayTitleLabel);
    
    JScrollPane scroller = new JScrollPane();
    scroller.setPreferredSize(new Dimension(300,700));
    scroller.setViewportView(courseListPanel);
    scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
    scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
   
    displayPanel = new JPanel();
    displayPanel.setPreferredSize(new Dimension(200,700));
    displayPanel.setLayout(new BorderLayout());
    displayPanel.add(displayTitlePanel, BorderLayout.NORTH);
    displayPanel.add(scroller, BorderLayout.CENTER);
    displayPanel.setBorder(BorderFactory.createLineBorder(border,3));
    
    //-----------------------------------------------------------------------------
    //adds all panels together
    setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
    add(managePanel);
    add(displayPanel); 
    setBackground(new Color(255, 255, 255));
  }
  
  //-------------------------------------------------------------------------------
  /** Resets the edit panel.
    */
  private void resetEditPanel() {
    editFallBox.setSelected(false);
    editSpringBox.setSelected(false);
    editPrereqBox.removeAllItems();
    editCourseNameBox.removeAllItems();
    editCourseNameBox.addItem("...     ");
    editPrereqBox.addItem("...     ");
    Vector<String> names = schedule.getCourseNames();
    for (int i = 0; i < names.size(); i++) {
      editPrereqBox.addItem(names.get(i));
      editCourseNameBox.addItem(names.get(i));
    }
    for (int i = editPrereqList.size() - 1; i >= 0; i--) {
      editPrereqListPanel.remove(editPrereqList.remove(i));
    }
    editPrerequisites = new LinkedList<Course>();
    editCourseNameBox.setSelectedIndex(0);
    editCoursePanel.setVisible(false);
    editSelectPanel.setVisible(true);
    editPanel.updateUI();
    editCourse = null;
    resetDisplayPanel();
    resetAddPanel();
  }
  
  //-------------------------------------------------------------------------------
  /** Resets the display panel.
    */
  private void resetDisplayPanel() {
    LinkedList<Course> allCourses = schedule.getCourses();
    
    for (int i = addedCourses.size() - 1; i >= 0; i--) {
      courseListPanel.remove(addedCourses.remove(i));
    }
    
    for (int i = 0; i < allCourses.size(); i++) {
      addedCourses.add(i, new JLabel(allCourses.get(i).toString()));
      addedCourses.get(i).setFont(courseFont);
      addedCourses.get(i).setForeground(foregroundColor2);
      courseListPanel.add(addedCourses.get(i));
    }
    
    if (addedCourses.size() > 0)  displayTitleLabel.setText("Your course list:");
    else displayTitleLabel.setText("Your course list is empty.");
    courseListPanel.updateUI();
  }
  
  //-------------------------------------------------------------------------------
  /** Resets the Add panel.
    */
  private void resetAddPanel() {
    springBox.setSelected(false);
    fallBox.setSelected(false);
    courseNameField.setText("");
    prereqBox.removeAllItems();
    prereqBox.addItem("...     ");
    Vector<String> names = schedule.getCourseNames();
    for (int i = 0; i < names.size(); i++) {
      prereqBox.addItem(names.get(i));
    }
    for (int i = prereqList.size() - 1; i >= 0; i--) {
      prereqListPanel.remove(prereqList.remove(i));
    }
    prerequisites = new LinkedList<Course>();
    addCourseLabel.setText("");
    addPanel.updateUI();
  }
  
  //-------------------------------------------------------------------------------
  /** Private ButtonListener class.
    */
  private class ButtonListener implements ActionListener{

    //-----------------------------------------------------------------------------
    /** Defines actionPerformed() in ActionListener.
      */
    public void actionPerformed(ActionEvent e){
      Object source = e.getSource();
      
      if (source == editSelectButton) {
        if (editCourseNameBox.getItemCount() > 1 && editCourseNameBox.getSelectedIndex() > 0) {
          editCoursePanel.setVisible(true);
          editSelectPanel.setVisible(false);
          
          String courseName = (String) editCourseNameBox.getSelectedItem();
          editCourse = schedule.findCourse(courseName);
          editFallBox.setSelected(editCourse.getIsFall());
          editSpringBox.setSelected(editCourse.getIsSpring());
          editCourseNameField.setText(editCourse.getName());
          editPrereqBox.removeItem(courseName);
          
          editPrerequisites = (LinkedList<Course>)editCourse.getPrerequisites().clone();
          
          for (Course c : editPrerequisites) {
            editPrereqBox.removeItem(c.getName());
            editPrereqList.add(new JCheckBox(c.getName()));
            editPrereqListPanel.add(editPrereqList.peekLast());
          }
          resetAddPanel();
        }
        
      }
      
      if (source == addCourseButton) { 
        if (courseNameField.getText().equals("")) {
          addCourseLabel.setText("Missing course name");
        } else if (schedule.findCourse(courseNameField.getText()) != null) {
          addCourseLabel.setText("Course name already in list");
        }  else if (!fallBox.isSelected() && !springBox.isSelected()) {
          addCourseLabel.setText("Missing semester info");
        } 
        else {
          
          Course c = new Course(courseNameField.getText(), fallBox.isSelected(), 
                                springBox.isSelected(), prerequisites);
          schedule.addCourse(c);
          
          addedCourses.add(new JLabel(c.toString()));
          resetAddPanel();
          resetEditPanel();
          resetDisplayPanel();
        }
      }
      if (source == editUpdateButton) {
        String courseName = editCourseNameField.getText();
        if (courseName.equals("")) {
          editAddCourseLabel.setText("Missing course name");
        } else if (!editCourse.getName().equals(courseName) && schedule.findCourse(courseName) != null) {
          editAddCourseLabel.setText("Course name already in list");
        }  else if (!editFallBox.isSelected() && !editSpringBox.isSelected()) {
          editAddCourseLabel.setText("Missing semester info");
        } else {
          schedule.changeCourse(editCourse, editCourseNameField.getText(), editFallBox.isSelected(), 
                                        editSpringBox.isSelected(), editPrerequisites);
          resetDisplayPanel();
          resetEditPanel();
        }
      }
      
      if (source == editCancelButton) { 
        resetEditPanel();
        resetDisplayPanel();
        resetAddPanel();
      }
      
      if (source == editDeleteButton) {
        int delete = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete this course? " +
                                                   "This will also delete it from other courses' list of prerequisites.");
        if (delete == JOptionPane.YES_OPTION) schedule.removeCourse(editCourse);
        resetEditPanel();
        resetDisplayPanel();
        resetAddPanel();
      }
      
      if (source == editAddPrereqButton) {
        if (prereqBox.getItemCount() > 0 && editPrereqBox.getSelectedIndex() > 0) {
          String courseName = (String) editPrereqBox.getSelectedItem();
          editPrereqBox.removeItem(editPrereqBox.getSelectedItem());
          Course course = schedule.findCourse(courseName);
          editPrerequisites.add(course);
          editPrereqList.add(new JCheckBox(course.getName()));
          editPrereqListPanel.add(editPrereqList.peekLast());
          editPrereqBox.setSelectedIndex(0);
        }
      }
      
      if (source == addPrereqButton) {
        if (prereqBox.getItemCount() > 0 && prereqBox.getSelectedIndex() > 0) {
          String courseName = (String) prereqBox.getSelectedItem();
          prereqBox.removeItem(prereqBox.getSelectedItem());
          Course course = schedule.findCourse(courseName);
          prerequisites.add(course);
          prereqList.add(new JCheckBox(course.getName()));
          prereqList.peekLast().setForeground(foregroundColor1);
          prereqListPanel.add(prereqList.peekLast());
          prereqBox.setSelectedIndex(0);
        }
      }
      
      if (source == editRemovePrereqButton) {
        for (int i = editPrereqList.size() -1 ; i >= 0; i--) {
          if (editPrereqList.get(i).isSelected()) {
            JCheckBox box = editPrereqList.remove(i); //remove from checkbox list
            editPrereqListPanel.remove(box); //remove from panel
            editPrereqListPanel.updateUI();
            editPrereqBox.addItem(box.getText()); //add to combo box
            editPrerequisites.remove(schedule.findCourse(box.getText())); //remove from prereq list
          }
        }
      }
      
      if (source == removePrereqButton) {
        for (int i = prereqList.size() -1 ; i >= 0; i--) {
          if (prereqList.get(i).isSelected()) {
            JCheckBox box = prereqList.remove(i); //remove from checkbox list
            prereqListPanel.remove(box); //remove from panel
            prereqBox.addItem(box.getText()); //add to combo box
            prerequisites.remove(schedule.findCourse(box.getText())); //remove from prereq list
            prereqListPanel.updateUI();
          }
        }
      }
      if (source == importButton) {
        try {
          schedule.importCourses(importField.getText() + ".txt");
          resetDisplayPanel();
          resetAddPanel();
          resetEditPanel();
        } catch (IOException exception) {
          JOptionPane.showMessageDialog(null, "Something went wrong. Unable to import to " + exportField.getText() + ".txt!");
        }
      }
      if (source == exportButton) {
        try {
          schedule.exportCourses(exportField.getText() + ".txt");
          JOptionPane.showMessageDialog(null, "Successfuly exported course list to " + exportField.getText() + ".txt!");
        } catch (IOException exception) {
          JOptionPane.showMessageDialog(null, "Something went wrong. Unable to export to " + exportField.getText() + ".txt!");
        }
      }
    }    
  }
}
