package validator;

import javafx.scene.control.TextField;

public class CustomValidators {

    public static void validateTextField(TextField textField){
        if(textField.getText().equals("")){
            throw new IllegalArgumentException("No information inserted");
        }
    }

}
