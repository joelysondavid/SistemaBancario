package model;

import framework.DaoConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Alisson R
 */
public class ContaDAO {
    
    // variaveis para integração do banco com a classe Clientes
    private Connection conn = null;
    private PreparedStatement stm;
    private ResultSet rs;
    private DaoConexao dao;

    // construtor com a conexão vindo do framework/DaoConexao
    public ContaDAO() {
        // instancia uma nova conexao
        dao = new DaoConexao();
        try {
            // nosso Connnection recebe a conexão
            conn = dao.getConexao();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar conexão com banco de dados: " + ex, "Erro de Conexão!", JOptionPane.ERROR_MESSAGE);
        }
    }
}
