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

    private FRM_Login frmLogin;

    public LoginControl(FRM_Login frmLogin) {
        this.frmLogin = frmLogin;
    }

    public boolean validaLogin() throws SQLException {
        FRM_Home frmHome = new FRM_Home();
        if (loginDao.validaLogin(Long.parseLong(frmLogin.getTxtConta().getText()), frmLogin.getTxtSenha().getText()) == true) {
            frmHome = new FRM_Home();
            frmHome.setVisible(true);
            frmHome.setEnabled(true);
            frmLogin.chave = 'E';
            frmLogin.setVisible(false);
            frmLogin.setEnabled(false);
            return true;
        } else {
            JOptionPane.showMessageDialog(frmLogin, "Conta e senha incorretos!", "Erro!", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
}
