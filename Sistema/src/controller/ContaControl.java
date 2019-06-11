package controller;

import java.sql.SQLException;
import model.Conta;
import model.ContaDAO;
import view.FRM_Conta;

/**
 *
 * @author Joelyson David
 */
public class ContaControl {
    private ContaDAO contaDao = new ContaDAO();
    private FRM_Conta frmConta;
    private Conta conta;
    
    // no construtor chamamos o formulario que vamos trabalhar
    public ContaControl(FRM_Conta frmConta) {
        this.frmConta = frmConta;
    }
    
    // método insert para inserir conta
    public void insertConta() throws SQLException {
        // cria um objeto conta com os dados que estão no formulario
        conta = new Conta(Integer.parseInt(frmConta.getTxtCPF().getText()), Double.parseDouble(frmConta.getTxtSaldo().getText()), frmConta.getTxtSenha().getText());
        // 
        contaDao.insertConta(conta);
    }
}
