/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.ContaDAO;
import model.Extrato;
import model.LoginDAO;
import view.FRM_Saldo;

/**
 *
 * @author Joelyson David
 */
public class ExtratoControl {
    
    private ContaDAO contaDao = new ContaDAO();
    private LoginDAO loginDao = new LoginDAO();
    private FRM_Saldo frmSaldo;
    
    public ExtratoControl(FRM_Saldo frmSaldo) {
        this.frmSaldo = frmSaldo;
    }

    // método para pegar os dados dos extratos
    public ArrayList<Extrato> getExtrato() throws SQLException {
        // arraylist para receber os dados do banco
        ArrayList<Extrato> extrato = contaDao.getTrasacoes(loginDao.getIdConta());
        // default table model para armazenar os dados na tabela
        DefaultTableModel dtmExtrato = (DefaultTableModel) frmSaldo.getTblExtrato().getModel();
        // for para passar os dados
        dtmExtrato.setNumRows(0);
        for (Extrato extra : extrato) {
            dtmExtrato.addRow(new Object[]{extra.getIdTransacao(), extra.getValor(),extra.getTipo(), extra.getDataTransacao()});
        }
        return extrato;
    }
}
