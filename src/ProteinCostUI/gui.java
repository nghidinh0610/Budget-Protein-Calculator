package ProteinCostUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.Color;

public class gui implements ActionListener {
	
	private static JPanel panel = new JPanel();
    private static JLabel greetingLabel;
    private static JLabel nameLabel;
    private static JTextField nameText;
    private static JButton enter1Button;
    private static JButton backButton1;
    private static JButton enter2Button;
    private static JLabel successLabel;

    private static JLabel proteinLabel;
    public static JTextField proteinText;
    private static JLabel budgetLabel;
    public static JTextField budgetText;
    private static JLabel budgetProteinLabel;
    private static JLabel InvalidbudgetProteinLabel;

    private static JButton beefButton;
    private static JButton chickenButton;
    private static JButton salmonButton;
    private static JButton porkButton;
    
    private int selectedMeatCount = 0;
    private static JLabel meatSelectionLabel;
    private static JButton confirmButton;
    private static JLabel resultLabel;
    
    public List<String> selectedMeats = new ArrayList<>();
	
    public static void main(String[] args) {
        // Create a GUI
        JFrame frame = new JFrame();
        panel.setLayout(null);

        // Create a Frame
        frame.setSize(500, 700);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create labels
        greetingLabel = new JLabel("Enter your first and last name");
        greetingLabel.setBounds(10, 20, 400, 25);
        panel.add(greetingLabel);

        nameLabel = new JLabel("Name");
        nameLabel.setBounds(10, 40, 60, 25);
        panel.add(nameLabel);

        nameText = new JTextField(30);
        nameText.setBounds(80, 40, 160, 25);
        panel.add(nameText);

        successLabel = new JLabel("");
        successLabel.setBounds(10, 110, 300, 40);
        panel.add(successLabel);
        
        //Prep for exceptions
        InvalidbudgetProteinLabel = new JLabel("Please enter valid numbers for budget and protein goal.");
        InvalidbudgetProteinLabel.setBounds(10, 270, 500, 40);
        panel.add(InvalidbudgetProteinLabel);
        InvalidbudgetProteinLabel.setVisible(false);

        frame.add(panel);
        frame.setVisible(true);

        enter1Button = new JButton("Enter");
        enter1Button.setBounds(10, 70, 80, 25);
        panel.add(enter1Button);
        enter1Button.addActionListener(new gui());
    }

    // Greeting with a name
    @Override
    public void actionPerformed(ActionEvent e) {
        String name = nameText.getText();
        // Ask for budget and the amount of protein this week
        successLabel.setText("<html>Hello " + name + "!<br>Enter your budget and protein goal<html>");
        
        //Disable editable on the name label
        nameText.setEditable(false);
        enter1Button.setEnabled(false);
        
        //Enter budget and goal
        budgetLabel = new JLabel("Budget");
        budgetLabel.setBounds(10, 150, 60, 25);
        panel.add(budgetLabel);

        budgetText = new JTextField(30);
        budgetText.setBounds(80, 150, 160, 25);
        panel.add(budgetText);

        proteinLabel = new JLabel("Protein");
        proteinLabel.setBounds(10, 190, 60, 25);
        panel.add(proteinLabel);

        proteinText = new JTextField(30);
        proteinText.setBounds(80, 190, 160, 25);
        panel.add(proteinText);

        budgetProteinLabel = new JLabel("");
        budgetProteinLabel.setBounds(10, 270, 500, 40);
        panel.add(budgetProteinLabel);

        
        // Back button to go back to greeting page
        backButton1 = new JButton("Back");
        backButton1.setBounds(10, 230, 80, 25);
        backButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Reset the GUI
                successLabel.setText("");
                panel.remove(budgetLabel);
                panel.remove(budgetText);
                panel.remove(proteinLabel);
                panel.remove(proteinText);
                panel.remove(backButton1);
                panel.remove(enter2Button);
                
                InvalidbudgetProteinLabel.setVisible(false);
                
                nameText.setEditable(true);
                enter1Button.setEnabled(true);
                
                panel.revalidate();
                panel.repaint();
            }
        });
        panel.add(backButton1);

        
        //Enter button for budget and protein input to go to meat selection
        enter2Button = new JButton("Enter");
        enter2Button.setBounds(110, 230, 80, 25);
        panel.add(enter2Button);
       
        enter2Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	enter2ButtonAction();
            }
        });
        
        panel.revalidate();
        panel.repaint();  
    }             
   private void enter2ButtonAction() {
        try {
           InvalidbudgetProteinLabel.setVisible(false); // Hide the error label initially
           double budget = Double.parseDouble(budgetText.getText());
           double protein = Double.parseDouble(proteinText.getText());
                
                
                // Ask for what type of meat they want (max 2)
           budgetProteinLabel.setText("<html>Your budget is $" + budget + " and your protein goal is " + protein + " grams<html>" + "<br><center>Choose your type of meat<html>");
           panel.add(budgetProteinLabel);
                
           MeatSelection();
                                
                              
           backButton1.setEnabled(false);
           budgetText.setEditable(false);
           proteinText.setEditable(false);
           enter2Button.setEnabled(false);
                
                
            
         // Handle invalid input
         } catch (NumberFormatException ex) {
      	      InvalidbudgetProteinLabel.setVisible(true);
                panel.revalidate();
                panel.repaint(); 
         }  catch (NullPointerException ex) {
      	      InvalidbudgetProteinLabel.setVisible(true);
                panel.revalidate();
                panel.repaint();
         }
     
     }       
            
        //Meat selection method
        private void MeatSelection() {
        	beefButton = new JButton("Beef");
            beefButton.setBounds(10, 320, 80, 60);
            panel.add(beefButton);

            porkButton = new JButton("Pork");
            porkButton.setBounds(110, 320, 80, 60);
            panel.add(porkButton);

            chickenButton = new JButton("Chicken");
            chickenButton.setBounds(210, 320, 120, 60);
            panel.add(chickenButton);

            salmonButton = new JButton("Salmon");
            salmonButton.setBounds(350, 320, 120, 60);
            panel.add(salmonButton);
            
         //Choose the meat
            beefButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleMeatButton(beefButton);
                }
            });

            porkButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleMeatButton(porkButton);
                }
            });

            chickenButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleMeatButton(chickenButton);
                }
            });

            salmonButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    handleMeatButton(salmonButton);
                }
            });
            

          //Sumary for type of meat chose
          meatSelectionLabel = new JLabel("You chose: ");
          meatSelectionLabel.setBounds(10, 390, 500, 40);
          panel.add(meatSelectionLabel);
          
          // Back and Confirm buttons for meat selection
          JButton backButtonMeat = new JButton("Back");
          backButtonMeat.setBounds(10, 440, 80, 25);
          panel.add(backButtonMeat);
          backButtonMeat.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) {
            	  BackbuttonMeatAction();
              	  panel.remove(backButtonMeat);
              }
          });
          
          panel.revalidate();
          panel.repaint();
          
           confirmButton = new JButton("Confirm");
           confirmButton.setBounds(110, 440, 100, 25);
           panel.add(confirmButton);
          
           confirmButton.addActionListener(new ActionListener() {
              @Override
              public void actionPerformed(ActionEvent e) { 
               	showCalculationResult();
               	
               	
               	beefButton.setEnabled(false);
               	porkButton.setEnabled(false);
               	chickenButton.setEnabled(false);
               	salmonButton.setEnabled(false);
             }
          });
          
           
           
        }
        
    private void BackbuttonMeatAction() {    
    	panel.remove(beefButton);
    	panel.remove(porkButton);
    	panel.remove(chickenButton);
    	panel.remove(salmonButton);
    	panel.remove(meatSelectionLabel);                    		
    	panel.remove(confirmButton);
    	panel.remove(budgetProteinLabel);
    	if (resultLabel != null) {
            panel.remove(resultLabel);
        }
    	else {
    		panel.remove(resultLabel);
    	}
                    
    	backButton1.setEnabled(true);
    	budgetText.setEditable(true);
    	proteinText.setEditable(true);
    	enter2Button.setEnabled(true);
    	
    	selectedMeatCount = 0;
        selectedMeats.clear();
      
                        
    	panel.revalidate();
    	panel.repaint();
                   
    }
                
     
    
 // Method to handle meat button selection
    private void handleMeatButton(JButton button) {
        if (button.getBackground() == null || !button.getBackground().equals(Color.GREEN)) {
            // Meat button is not selected, select it
            if (selectedMeatCount < 2) {
                button.setBackground(Color.GREEN);
                selectedMeatCount++;
                updateMeatSelectionSummary(button.getText(), true);
                selectedMeats.add(button.getText());
            }
            
            panel.revalidate();
            panel.repaint();
        } else {
            // Meat button is already selected, deselect it
            button.setBackground(null);
            selectedMeatCount--;
            updateMeatSelectionSummary(button.getText(), false);
            selectedMeats.remove(button.getText());
        }
        
        panel.revalidate();
        panel.repaint();
    }
   
 
    
    // Update meat selection summary label
    private void updateMeatSelectionSummary(String meat, boolean isSelected) {
        String currentText = meatSelectionLabel.getText();
        if (isSelected) {
            if (currentText.equals("You chose: ")) {
                meatSelectionLabel.setText(currentText + meat);
            } else {
                meatSelectionLabel.setText(currentText + ", " + meat);
            }
        } else {
            // Remove the deselected meat from the summary
            currentText = currentText.replace(", " + meat, "").replace(meat + ", ", "").replace(meat, "");
            meatSelectionLabel.setText(currentText);
      }
    }  
        
   
    private void showCalculationResult() {
    	Formular formular = new Formular();
    	String result = formular.calculate(selectedMeats, Double.parseDouble(budgetText.getText()), Double.parseDouble(proteinText.getText()));
    	
    	resultLabel = new JLabel(result);
    	resultLabel.setBounds(10,480,500,40);
    	panel.add(resultLabel);
    	
    	 // Revalidate and repaint the panel to update UI
        panel.revalidate();
        panel.repaint();
    	
    }
    
}
    
    
