package Control;

import Model.User;
import Util.OtpUtils;
import Util.PasswordUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

public class NewAccountFormController {
    public TextField txtEmail;
    public Label lblEmail;
    public PasswordField txtPassword;
    public Button btnCreate;
    public Label lblLog;
    public Button btnGetOtp;
    public TextField txtOtp;
    public Label lblResend;
    public CheckBox checkLogged;
    public TextField txtOtp1;
    public TextField txtOtp2;
    public TextField txtOtp3;
    public TextField txtOtp4;
    public Label lblVerified;
    public Label lblStrength;

    PasswordUtils passwordUtils=new PasswordUtils();

   public void actionCreate(ActionEvent actionEvent) {
       String receivedOtp=txtOtp1.getText().trim()+txtOtp2.getText().trim()+txtOtp3.getText().trim()+txtOtp4.getText().trim();

        try {

            if (!txtEmail.getText().isEmpty() && !txtPassword.getText().isEmpty() && receivedOtp.equals(otp)) {
                User user=new User(txtEmail.getText().trim(),txtPassword.getText().trim());

                boolean isAdded=UserController.addUser(user);
                UserController.setAutoFill(txtEmail.getText().trim(),checkLogged.isSelected());
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information");
                alert.setHeaderText(null);
                alert.setContentText("Account Created!");

                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK) {
                  Stage stage=(Stage) btnCreate.getScene().getWindow();
                  stage.close();

                    try {
                        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/MainLoginForm.fxml"))));
                    } catch (IOException e) {


                    }
                    primaryStage.show();

                }
            }else{
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("Warning");
                alert.setHeaderText(null);
                alert.setContentText("Failed to Create Account");
                alert.showAndWait();

            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    Stage primaryStage=new Stage();
    public void clickedLog(MouseEvent mouseEvent) {
        Stage stage=(Stage)lblLog.getScene().getWindow();
        stage.close();

        try {
            primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("/View/MainLoginForm.fxml"))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        primaryStage.show();
    }

    String otp=OtpUtils.generateOtp();
    public void actionGetOtp(ActionEvent actionEvent) {
        if(txtEmail.getText().isEmpty()){
            Alert otpAlert = new Alert(Alert.AlertType.ERROR);
            otpAlert.setTitle("OTP Verification");
            otpAlert.setHeaderText("Email cannot be empty!");
            otpAlert.showAndWait();
        }else {
            Alert otpAlert = new Alert(Alert.AlertType.INFORMATION);
            otpAlert.setTitle("OTP Verification");
            otpAlert.setHeaderText("Your OTP is : "+otp);
            otpAlert.showAndWait();
        }

    }

    public void clickedResend(MouseEvent mouseEvent) {
        Alert otpAlert = new Alert(Alert.AlertType.INFORMATION);
        otpAlert.setTitle("OTP Verification");
        otpAlert.setHeaderText("Your OTP is : "+otp);
        otpAlert.showAndWait();
    }

    public void validateOtp(KeyEvent keyEvent) {
        String receivedOtp=txtOtp1.getText().trim()+txtOtp2.getText().trim()+txtOtp3.getText().trim()+txtOtp4.getText().trim();
        if(receivedOtp.equals(otp)){
          lblVerified.setText("Verified");
          txtEmail.setEditable(false);

        }else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning");
            alert.setHeaderText(null);
            alert.setContentText("Couldn't Verify");
            alert.showAndWait();
            txtOtp1.clear();
            txtOtp2.clear();
            txtOtp3.clear();
            txtOtp4.clear();

        }

    }

    public void strengthIndicatorAction(KeyEvent keyEvent) {
        if (txtPassword.getLength()<3) {
            lblStrength.setText("Weak");
            lblStrength.setTextFill(Color.RED);

        } else if (txtPassword.getLength()<6) {
            lblStrength.setText("Medium");
            lblStrength.setTextFill(Color.ORANGE);

        } else {
            lblStrength.setText("Strong");
            lblStrength.setTextFill(Color.GREEN);

        }

    }
}
