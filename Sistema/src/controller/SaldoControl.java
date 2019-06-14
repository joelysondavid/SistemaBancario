package controller;

import java.sql.SQLException;
import model.ContaDAO;
import model.LoginDAO;
import view.FRM_Login;
import view.FRM_Saldo;

/**
 *
 * @author Joelyson David
 */
public class SaldoControl {

    private FRM_Saldo frmSaldo;
    private FRM_Login frmLogin = new FRM_Login();
    private ContaDAO contaDao = new ContaDAO();
    private LoginDAO loginDao = new LoginDAO();

    public SaldoControl(FRM_Saldo frmSaldo) {
        this.frmSaldo = frmSaldo;
    }

    // metodo para exibir os dados
    public void exibeDados() throws SQLException {
        double saldo = contaDao.saldoConta(loginDao.getIdConta());
        frmSaldo.getTxtSaldo().setText(""+saldo);        
    }
}
