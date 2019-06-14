package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.Conta;
import model.ContaDAO;
import model.LoginDAO;
import model.Extrato;
import view.FRM_Conta;
import view.FRM_Saldo;

/**
 *
 * @author Joelyson David
 */
public class ContaControl {

    private ContaDAO contaDao = new ContaDAO();
    private FRM_Conta frmConta;
    private Conta conta;
    private LoginDAO loginDao = new LoginDAO();
    private FRM_Saldo frmSaldo;

    // no construtor chamamos o formulario que vamos trabalhar
    public ContaControl(FRM_Conta frmConta) {
        this.frmConta = frmConta;
        // this.frmSaldo = frmSaldo;
    }

    // método insert para inserir conta
    public void insertConta() throws SQLException {
        // cria um objeto conta com os dados que estão no formulario
        conta = new Conta(Integer.parseInt(frmConta.getTxtCPF().getText()), Double.parseDouble(frmConta.getTxtSaldo().getText()), frmConta.getTxtSenha().getText());
        // 
        contaDao.insertConta(conta);
    }
}
