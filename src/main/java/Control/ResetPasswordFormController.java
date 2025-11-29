package Control;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javafx.scene.paint.Color;
import java.io.IOException;
import java.sql.SQLException;

public class ResetPasswordFormController {
    public TextField txtEmail;
    public PasswordField txtNewPassword;
    public Button btnSubmit;
    public Label lblBack;
    public PasswordField txtConfirmPassword;
    public Label lblCondition;

    public void validateEmail(KeyEvent keyEvent) {
    }

    public void validateNewPassword(KeyEvent keyEvent) {
        if (txtNewPassword.getLength()<3) {
            lblCondition.setText("Weak");
            lblCondition.setTextFill(Color.RED);

        } else if (txtNewPassword.getLength()<6) {
            lblCondition.setText("Medium");
            lblCondition.setTextFill(Color.ORANGE);

        } else {
            lblCondition.setText("Strong");
            lblCondition.setTextFill(Color.GREEN);

        }
    }

    public void actionSubmit(ActionEvent actionEvent) {
        try {
            if(txtNewPassword.getText().equals(txtConfirmPassword.getText())){
                if(UserController.resetPassword(txtEmail.getText().trim(),txtConfirmPassword.getText().trim())){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information");
                    alert.setHeaderText(null);
                    alert.setContentText("Password Changed");
                    alert.showAndWait();
                }
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Passwords do not match ✘");
                alert.showAndWait();
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }


    Stage primaryStage=new Stage();
    public void clickedBack(MouseEvent mouseEvent) {
        Stage stage=(Stage)lblBack.getScene().getWindow();
        stage.close();

        try {
            primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/MainLoginForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        primaryStage.show();
    }

    public void validateConfirmPassword(KeyEvent keyEvent) {
    }
}
