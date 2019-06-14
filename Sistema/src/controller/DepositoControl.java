package controller;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.ContaDAO;
import model.LoginDAO;
import view.FRM_Deposito;
import view.FRM_Saque;

/**
 *
 * @author Joelyson David
 */
public class DepositoControl {

    private LoginDAO loginDao = new LoginDAO();
    private ContaDAO contaDao = new ContaDAO();
    private FRM_Deposito frmDeposito;

    public DepositoControl(FRM_Deposito frmDeposito) {
        this.frmDeposito = frmDeposito;
    }

    // metodo para exibir saldo no campo de texto
    public void exibeDados() throws SQLException {
        double saldo = contaDao.saldoConta(loginDao.getIdConta());
        frmDeposito.getTxtSaldo().setText("" + saldo);
    }

    // método para deposito
    public void depositar() throws SQLException {

        if (!frmDeposito.getTxtValor().getText().equals("")) {
            double valor  = Double.parseDouble(frmDeposito.getTxtValor().getText());
            if (valor > 0) {
                int op = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja realizar este Deposito de R$ " + valor + "?", "Confirmar Saque", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (op == 0) {
                    contaDao.depositar(loginDao.getIdConta(), valor);
                    frmDeposito.getTxtValor().setText("");
                    exibeDados();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Não é permitido a tentativa de saques com campo de valor vazio!", "Aviso!", JOptionPane.INFORMATION_MESSAGE);
        }

    }
}
