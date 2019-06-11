/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import framework.DaoConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Joelyson David
 */
public class LoginDAO {

    // cria objetos para conexão
    private Connection conn = null;
    private PreparedStatement stm;
    private ResultSet rs;
    private DaoConexao dao;

    public LoginDAO() {
        dao = new DaoConexao();
        try {
            conn = dao.getConexao();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar conexão com banco de dados!", "Aviso!", JOptionPane.WARNING_MESSAGE);
        }

    }

    public boolean validaLogin(long conta, String senha) throws SQLException {
        // string com comando para select para verificar o login
        String comando = "SELECT * FROM CONTA WHERE ID_CONTA = ? AND SENHA = ?;";
        // prepara o comando
        stm = conn.prepareStatement(comando);
        // seta os valores que serão informados
        stm.setLong(1, conta);
        stm.setString(2, senha);
        // executa o comando com result set para trabalhar com retorno de dados do banco
        rs = stm.executeQuery();
        if (rs.next()) {    
            return true;
        } else {
            return false;
        }
    }
}
