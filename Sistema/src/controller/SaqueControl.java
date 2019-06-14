package controller;

import java.sql.SQLException;
import javax.swing.JOptionPane;
import model.ContaDAO;
import model.LoginDAO;
import view.FRM_Saque;

/**
 *
 * @author Joelyson David
 */
public class SaqueControl {

    private LoginDAO loginDao = new LoginDAO();
    private ContaDAO contaDao = new ContaDAO();
    private FRM_Saque frmSaque;

    public SaqueControl(FRM_Saque frmSaque) {
        this.frmSaque = frmSaque;
    }

    // metodo para exibir saldo no campo de texto
    public void exibeDados() throws SQLException {
        double saldo = contaDao.saldoConta(loginDao.getIdConta());
        frmSaque.getTxtSaldo().setText("" + saldo);
    }

    // método para deposito
    public void sacar() throws SQLException {

        if (!frmSaque.getTxtValor().getText().equals("")) {
            double valor  = Double.parseDouble(frmSaque.getTxtValor().getText());
            if (valor > 0) {
                int op = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja realizar este saque de R$ " + valor + "?", "Confirmar Saque", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (op == 0) {
                    contaDao.sacar(loginDao.getIdConta(), valor);
                    frmSaque.getTxtValor().setText("");
                    exibeDados();
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Não é permitido a tentativa de saques com campo de valor vazio!", "Aviso!", JOptionPane.INFORMATION_MESSAGE);
        }

    }
}
