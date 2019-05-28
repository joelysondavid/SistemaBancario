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
<<<<<<< HEAD
import java.util.logging.Level;
import java.util.logging.Logger;
=======
import java.util.ArrayList;
>>>>>>> master
import javax.swing.JOptionPane;

/**
 *
 * @author Joelyson David
 */
public class ClientesDAO {

<<<<<<< HEAD
    // varoaveos de para integração do banco com a classe Clientes
=======
    // variaveis de para integração do banco com a classe Clientes
>>>>>>> master
    private Connection conn = null;
    private PreparedStatement stm;
    private ResultSet rs;
    private DaoConexao dao;

<<<<<<< HEAD
    // consturtor com a conexão vindo do framework/DaoConexao
=======
    // construtor com a conexão vindo do framework/DaoConexao
>>>>>>> master
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
<<<<<<< HEAD
=======
        JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!", "Cadastrado", JOptionPane.INFORMATION_MESSAGE);
>>>>>>> master
        dao.closeConnection(conn, stm, rs);
        // e por fim retorna o codigo do novo cliente
        return cliente.getCodigoCliente();
    }

<<<<<<< HEAD
=======
    // método delete cliente
    public void deleteCliente(int codigo) throws SQLException {
        // comando delete cliente
        String comando = "DELETE FROM Clientes WHERE ID_Cliente = ?";
        // preparação do comando delete
        stm = conn.prepareStatement(comando);
        stm.setInt(1, codigo);
        stm.executeUpdate();
        JOptionPane.showMessageDialog(null, "Cliente apagado com sucesso!", "Deletado", JOptionPane.INFORMATION_MESSAGE);
    }

    // método para alterar cliente
    public void updateCliente(Clientes cliente, int codigo) throws SQLException {
        // comando para alterar cliente
        String comando = "UPDATE Clientes SET NomeCliente=?, EnderecoCliente=?, Email=?, CPF_CNPJ=?, Telefone = ? WHERE ID_Cliente = ?;";
        // prepara o comando para os dados
        stm = conn.prepareStatement(comando);
        // seta os dados que serão inseridos no banco
        stm.setString(1, cliente.getNomeCliente());
        stm.setString(2, cliente.getEnderecoCliente());
        stm.setString(3, cliente.getEmail());
        stm.setInt(4, cliente.getDocumento());
        stm.setInt(5, cliente.getTelefone());
        stm.setInt(6, codigo);
        // executa o comando UPDATE
        stm.executeUpdate();
        // fecha as conexões
        dao.closeConnection(conn, stm, rs);
    }

    // método procurar nome do Cliente
    public Clientes procurarCliente(String nomeCliente) throws SQLException {
        // comando select para procurar pelo nome do cliente
        String comando = "SELECT * FROM Clientes WHERE NomeCliente = ?";
        // prepara o comando com a string
        stm = conn.prepareStatement(comando);
        // seta o nome do cliente no comando select
        stm.setString(1, nomeCliente);
        // executa o comando
        rs = stm.executeQuery();
        // mota o cliente conforme os dados do BD
        Clientes cliente = new Clientes(rs.getInt("ID_Cliente"), rs.getString("NomeCliente"), rs.getString("EnderecoCliente"), rs.getString("Email"), rs.getInt("CPF_CNPJ"), rs.getInt("Telefone"));
        // fecha as conexões
        dao.closeConnection(conn, stm, rs);
        // retorna o objeto Cliente
        return cliente;
    }

    // mostra todos os clientes
    public ArrayList<Clientes> mostraClientes() throws SQLException {
        // cria um array list de clientes
        ArrayList<Clientes> clientes = new ArrayList<>();
        // comando select para todos os clientes
        String comando = "SELECT * FROM Clientes";
        // prepara o comando select
        stm = conn.prepareStatement(comando);
        // método result set para trabalhar com recebimento de dados do banco
        rs = stm.executeQuery();
        while (rs.next()) {
            clientes.add(new Clientes(rs.getInt("ID_Cliente"), rs.getString("NomeCliente"),
                    rs.getString("EnderecoCliente"), rs.getString("Email"),
                    rs.getInt("CPF_CNPJ"), rs.getInt("Telefone")));
        }
        dao.closeConnection(conn, stm, rs);
        return clientes;
    }
>>>>>>> master
}
