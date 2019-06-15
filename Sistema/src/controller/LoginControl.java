package controller;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.LoginDAO;
import view.FRM_Login;
import view.FRM_Home;

/**
 *
 * @author Joelyson David
 */
public class LoginControl {

    private LoginDAO loginDao = new LoginDAO();
    ;
    private FRM_Login frmLogin;
    FRM_Home frmHome;

    public LoginControl(FRM_Login frmLogin) {
        this.frmLogin = frmLogin;
    }

    public void validaLogin() throws SQLException {
        if (loginDao.validaLogin(Long.parseLong(frmLogin.getTxtConta().getText()), frmLogin.getTxtSenha().getText()) == true) {
            frmHome = new FRM_Home();
            frmHome.setVisible(true);
            frmHome.setEnabled(true);
            frmLogin.setVisible(false);
            frmLogin.setEnabled(false);
        } else {
            JOptionPane.showMessageDialog(frmLogin, "Conta e senha não estão corretos!", "Erro!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
