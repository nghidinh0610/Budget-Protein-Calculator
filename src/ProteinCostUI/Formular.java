package ProteinCostUI;


import java.util.List;

public class Formular {
    public String calculate (List<String> selectedMeats, double budget, double protein) {
        // Prices in $/lbs
        double priceBeef = 5.17;
        double priceChicken = 5.19;
        double pricePork = 2.59;
        double priceSalmon = 12.24;

        // Protein content g/lbs
        double proteinBeef = 117;
        double proteinChicken = 139;
        double proteinPork = 123;
        double proteinSalmon = 92;
      
       
		//Unknown numbers for formula
        double m1 = 0, n1 = 0, m2 = 0, n2 = 0;    
        double x1, x2;
              
        
        //Assign type of meat to formula
        for (int i = 0; i < 2; i++) {
            String meat = selectedMeats.get(i);
            switch (meat) {
                case "Beef":
                    if (i == 0) {
                        m1 = proteinBeef;
                        n1 = priceBeef;
                    } else {
                        m2 = proteinBeef;
                        n2 = priceBeef;
                    }
                    break;
                case "Chicken":
                    if (i == 0) {
                        m1 = proteinChicken;
                        n1 = priceChicken;
                    } else {
                        m2 = proteinChicken;
                        n2 = priceChicken;
                    }
                    break;
                case "Pork":
                    if (i == 0) {
                        m1 = proteinPork;
                        n1 = pricePork;
                    } else {
                        m2 = proteinPork;
                        n2 = pricePork;
                    }
                    break;
                case "Salmon":
                    if (i == 0) {
                        m1 = proteinSalmon;
                        n1 = priceSalmon;
                    } else {
                        m2 = proteinSalmon;
                        n2 = priceSalmon;
                    }
                    break;
            }
        }

        //Formula: x1 and x2 are the weights of meat 1 and meat 2, which is the result from dual equation
        // m1*x1 + m2*x2 = protein
        // n1*x1 + n2*x2 = budget
        x2 = (protein * n1 - budget * m1) / (m2 * n1 - n2 * m1);
        x1 = (budget - n2 * x2) / n1;

     //Call some variable
        String result = "";
        double higherPrice = Math.max(n1, n2);
        double maxBudgetWeight = budget / higherPrice;

        double moreExpMeat = (higherPrice == n1) ? m1 : m2;
        double maxProteinWeight = protein / moreExpMeat;
        double maxBudgetDif = (maxBudgetWeight - maxProteinWeight) * higherPrice;
            
        double lowerPrice = Math.min(n1, n2);
        double minBudgetWeight = budget / lowerPrice;

        double lessExpMeat = (lowerPrice == n1) ? m1 : m2;
        double minProteinWeight = protein / lessExpMeat;
        double minBudgetDif = (minProteinWeight - minBudgetWeight) * lowerPrice;
        
        //Possible result
        if (x1 > 0 && x2 > 0) {
            result = String.format("<html>To satisfy both protein goal and budget, you need %.2f lbs of %s and %.2f lbs of %s.</html>", x1, selectedMeats.get(0), x2, selectedMeats.get(1));
        } 
        
        //If one of the result = 0, then only need 1 type of meat
        else if (x1 == 0 && x2 > 0) {
            result = String.format("<html>To satisfy both protein goal and budget, you need %.2f lbs of %s.</html>", x2, selectedMeats.get(1));
        }
        else if (x1 > 0 && x2 == 0) {
            result = String.format("<html>To satisfy both protein goal and budget, you need %.2f lbs of %s.</html>", x1, selectedMeats.get(0));
        } 
        
        //Invalid if both are 0
        else if (x1 == 0 && x2 == 0) {
            result = "Invalid input.";
          }  
        
    	else if (x1 < 0 || x2 < 0) {
            if (maxBudgetWeight > maxProteinWeight) {
            	result = String.format("<html>You need %.2f lb of %s and you are over $%.2f in budget.</html>", maxProteinWeight,selectedMeats.get((higherPrice == n1) ? 0 : 1), maxBudgetDif);
            } 
            else if (minBudgetWeight < minProteinWeight) {
            	result = String.format("<html>You  are under $%.2f in budget to get minimum %.2flb of %s.</html>", minBudgetDif,minProteinWeight, selectedMeats.get((lowerPrice == n1) ? 0 : 1));
            }
            else {
            	result = "Invalid input";
            }
    	}
        else {
            result = "Uncalculated. Please try again";
            }
    	
    
        return result;
    }
}

