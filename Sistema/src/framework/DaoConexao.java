/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package framework;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Joelyson David
 */
public class DaoConexao {

    // Dados para a conexao
    // String com Driver
    String driver = "com.mysql.jdbc.Driver";
    // String com caminho usuario e senha
    String url = "jdbc:mysql://localhost:3306/Banco";
    String usr = "root";
    String psw = "";

    // metodo para conexão com o banco
    public Connection getConexao() throws SQLException {

        Connection conn = null;

        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, usr, psw);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Falha na conexão com a base de dados!" + ex, "Erro Conexão", JOptionPane.ERROR_MESSAGE);
        }
        return conn;
    }

    // método para fechar as conexões
    public void closeConnection(Connection conn, Statement stmt, ResultSet rs) throws SQLException {

        if (stmt != null) {
            stmt.close();
        }

        if (rs != null) {
            rs.close();
        }

        conn.close();
    }
}
