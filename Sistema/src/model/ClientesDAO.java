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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Joelyson David
 */
public class ClientesDAO {

    // varoaveos de para integração do banco com a classe Clientes
    private Connection conn = null;
    private PreparedStatement stm;
    private ResultSet rs;
    private DaoConexao dao;

    // consturtor com a conexão vindo do framework/DaoConexao
    public ClientesDAO() {
        // instancia uma nova conexao
        dao = new DaoConexao();
        try {
            // nosso Connnection recebe a conexão
            conn = dao.getConexao();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao tentar conexão com banco de dados: " + ex, "Erro de Conexão!", JOptionPane.ERROR_MESSAGE);
        }
    }

    // método de novo cliente
    public int insertCliente(Clientes cliente) throws SQLException {
        String comando = "INSERT INTO Clientes (NomeCliente, EnderecoCliente, Email, CPF_CNPJ, Telefone) VALUES (?, ?, ?, ?, ?)";
        // stm recebe a preparação da query com os dados passados com um metodo para retornar a chave gerada
        stm = conn.prepareStatement(comando, PreparedStatement.RETURN_GENERATED_KEYS);
        stm.setString(1, cliente.getNomeCliente());
        stm.setString(2, cliente.getEnderecoCliente());
        stm.setString(3, cliente.getEmail());
        stm.setInt(4, cliente.getDocumento());
        stm.setInt(5, cliente.getTelefone());
        // executa comando sql
        stm.executeUpdate();

        // usa rs para trabalhar com recebimento de dados
        rs = stm.getGeneratedKeys();
        // atualiza codigo com auto_increment do objeto instanciado
        if (rs.next()) {
            cliente.setCodigoCliente(rs.getInt(1));
        }
        dao.closeConnection(conn, stm, rs);
        // e por fim retorna o codigo do novo cliente
        return cliente.getCodigoCliente();
    }

}
