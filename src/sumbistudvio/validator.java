package sumbistudvio;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class validator {
    
    public boolean isValidChoice(int choice) {
        return choice >= 1 && choice <= 4;
    }
    
    public boolean isValidAction(int action) {
        return action >= 1 && action <= 5;
    }
    
    public boolean isValidActionRec(int action) {
        return action >= 1 && action <= 6;
    }
    
    public boolean isValidName(String name) {
        return name.matches("[a-zA-Z\\s]+") && name.length() > 0;
    }

    public boolean isValidProgram(String program) {
        return program.matches("[A-Z]+") && program.length() > 0;
    }

    public boolean isValidSection(String section) {
        return section.matches("[0-9][A-Z]") && section.length() == 2;
    }

    public boolean isValidAddress(String address) {
        return address.matches("[a-zA-Z\\s]+") && address.length() > 0;
    }
    
    public boolean isValidDate(String date) {
        String datePattern = "MM-dd-yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(datePattern);
        sdf.setLenient(false);

        try {
            sdf.parse(date);
            return true;
        } catch (ParseException e) {
            return false;
        }
    }
    
}
