package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Conta;
import model.ContaDAO;
import model.LoginDAO;
import model.Extrato;
import view.FRM_Clientes;
import view.FRM_Conta;
import view.FRM_Login;
import view.FRM_Saldo;

/**
 *
 * @author Joelyson David
 */
public class ContaControl {

    private ContaDAO contaDao = new ContaDAO();
    private FRM_Conta frmConta;
    private FRM_Clientes frmCliente;
    private Conta conta;
    private LoginDAO loginDao = new LoginDAO();
    private FRM_Saldo frmSaldo;

    // no construtor chamamos o formulario que vamos trabalhar
    public ContaControl(FRM_Conta frmConta) {
        this.frmConta = frmConta;
        // this.frmSaldo = frmSaldo;
    }

    public ContaControl(FRM_Clientes frmCliente) {
        this.frmCliente = frmCliente;
    }

    // método insert para inserir conta
    public void insertConta() throws SQLException {
        String novaSenha = frmCliente.getTxtNovaSenha().getText();
        String confirmaSenha = frmCliente.getTxtConfirmaSenha().getText();
        if (!(novaSenha.equals("")) || !(confirmaSenha.equals(""))) {
            if (novaSenha.equals(confirmaSenha)) {
                // cria um objeto conta com os dados que estão no formulario
                conta = new Conta(loginDao.getIdCliente(), frmCliente.getTxtNovaSenha().getText());
                contaDao.insertConta(conta);
                FRM_Login frmLogin = new FRM_Login();
            frmLogin.setVisible(true);
            frmLogin.setEnabled(true);
            frmCliente.setVisible(false);
            frmCliente.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "Senhas incompativeis!\nTente novamente!", "Alerta!", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Os dois campos devem estar preenchidos", "Alerta!", JOptionPane.WARNING_MESSAGE);
        }

    }
}
