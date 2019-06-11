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

    // método para inserir conta
    public long insertConta(Conta conta) throws SQLException {
        // comando para insert de conta
        String comando = "INSERT INTO CONTA (ID_Cliente, Saldo, Senha) VALUES (?, ?, ?)";
        // chama o método prepared para preparar nossa string
        stm = conn.prepareStatement(comando, PreparedStatement.RETURN_GENERATED_KEYS);
        // faz o preparo para inserção
        stm.setInt(1, conta.getCodigoCliente());
        stm.setDouble(2, conta.getSaldo());
        stm.setString(3, conta.getSenha());
        // executa o comando
        stm.executeUpdate();
        // chama o método rs para receber a chave gerada
        rs = stm.getGeneratedKeys();
        while(rs.next()) {
            conta.setNumConta(rs.getLong(1));
        }
        // exibe mensagem de cliente cadastrado
        JOptionPane.showMessageDialog(null, "Conta cadastrada com sucesso!", "Sucesso!", JOptionPane.INFORMATION_MESSAGE);
        // retorna o numero da conta
        return conta.getNumConta();
    }
}
