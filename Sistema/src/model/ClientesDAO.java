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
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author Joelyson David
 */
public class ClientesDAO {

    // variaveis de para integração do banco com a classe Clientes
    private Connection conn = null;
    private PreparedStatement stm;
    private ResultSet rs;
    private DaoConexao dao;
    static int codigoCli = 0;

    // construtor com a conexão vindo do framework/DaoConexao
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
        Date data = new Date();
        SimpleDateFormat formatar = new SimpleDateFormat("yyyy/MM/dd");
        String hoje = formatar.format(data);

        String comando = "INSERT INTO Clientes (NomeCliente, EnderecoCliente, Email, CPF_CNPJ, Telefone, ClienteDesde) VALUES (?, ?, ?, ?, ?, ?)";

        // stm recebe a preparação da query com os dados passados com um metodo para retornar a chave gerada
        stm = conn.prepareStatement(comando, PreparedStatement.RETURN_GENERATED_KEYS);
        stm.setString(1, cliente.getNomeCliente());
        stm.setString(2, cliente.getEnderecoCliente());
        stm.setString(3, cliente.getEmail());
        stm.setLong(4, cliente.getDocumento());
        stm.setString(5, cliente.getTelefone());
        stm.setString(6, hoje);
        // executa comando sql
        stm.executeUpdate();

        // usa rs para trabalhar com recebimento de dados
        rs = stm.getGeneratedKeys();
        // atualiza codigo com auto_increment do objeto instanciado
        if (rs.next()) {
            cliente.setCodigoCliente(rs.getInt(1));
            codigoCli = rs.getInt(1);
        }
        JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!", "Cadastrado", JOptionPane.INFORMATION_MESSAGE);
        // e por fim retorna o codigo do novo cliente
        return cliente.getCodigoCliente();

    }

    // método delete cliente
    public void deleteCliente(int codCliente, long codConta) throws SQLException {
        // String deleta extrato
        String comandoExtrato = "DELETE FROM EXTRATO WHERE ID_CONTA = ?;";
        // prepara a string com o comando
        stm = conn.prepareStatement(comandoExtrato);
        // seta o valor
        stm.setLong(1, codConta);
        // executa o comando
        stm.executeUpdate();

        // String delete conta
        String comandoConta = "DELETE FROM CONTA WHERE ID_CLIENTE = ?;";
        // prepara a string com o comando
        stm = conn.prepareStatement(comandoConta);
        // seta o valor
        stm.setInt(1, codCliente);
        // executa o comando
        stm.executeUpdate();
        // comando delete cliente
        String comandoCliente = "DELETE FROM Clientes WHERE ID_Cliente = ?";
        // preparação do comando delete
        stm = conn.prepareStatement(comandoCliente);
        stm.setInt(1, codCliente);
        stm.executeUpdate();
        JOptionPane.showMessageDialog(null, "Cliente apagado com sucesso!", "Deletado", JOptionPane.INFORMATION_MESSAGE);
    }

    // método para alterar cliente
    public void updateCliente(Clientes cliente, int codigo) throws SQLException {
        LoginDAO loginDao = new LoginDAO();
        // comando para alterar cliente
        String comando = "UPDATE Clientes SET NomeCliente=?, EnderecoCliente=?, Email=?, Telefone = ? WHERE ID_Cliente = ?;";
        // prepara o comando para os dados
        stm = conn.prepareStatement(comando);
        // seta os dados que serão inseridos no banco
        stm.setString(1, cliente.getNomeCliente());
        stm.setString(2, cliente.getEnderecoCliente());
        stm.setString(3, cliente.getEmail());
        stm.setString(4, cliente.getTelefone());
        stm.setLong(5, codigo);
        // executa o comando UPDATE
        loginDao.setNome(cliente.getNomeCliente());
        stm.executeUpdate();
    }

    // método procurar nome do Cliente
    public ArrayList<Clientes> procurarCliente(String nomeCliente) throws SQLException {
        // cria um array list de clientes
        ArrayList<Clientes> clientes = new ArrayList<>();
        // comando select para procurar pelo nome do cliente
        String comando = "SELECT * FROM Clientes WHERE NomeCliente LIKE ?;";
        // prepara o comando com a string
        stm = conn.prepareStatement(comando);
        stm.setString(1, "%" + nomeCliente + "%");
        // método result set para trabalhar com recebimento de dados do banco
        rs = stm.executeQuery();
        while (rs.next()) {
            clientes.add(new Clientes(rs.getInt("ID_Cliente"), rs.getString("NomeCliente"),
                    rs.getString("EnderecoCliente"), rs.getString("Email"),
                    rs.getLong("CPF_CNPJ"), rs.getString("Telefone")));
        }
        // método para caso não encontre cliente cadastrado com aquele nome
        // retorna o objeto Cliente
        return clientes;
    }

    // sobrescrever método procurar cliente
    public Clientes procurarCliente(int codigo) throws SQLException {
        // cria objeto cliente
        Clientes cliente;
        // String com o comenado select para buscar cnpj do cliente
        String comando = "SELECT * FROM CLIENTES WHERE ID_CLIENTE = ?;";
        // prepara nosso comando sql
        stm = conn.prepareStatement(comando);
        // seta o valor que iremos passar para nosso campo "?"
        stm.setInt(1, codigo);
        // execura o comando
        rs = stm.executeQuery();
        // chama rs para receber os dados do banco
        if (rs.next()) {
            cliente = new Clientes(rs.getInt("ID_Cliente"), rs.getString("NomeCliente"),
                    rs.getString("EnderecoCliente"), rs.getString("Email"),
                    rs.getLong("CPF_CNPJ"), rs.getString("Telefone"));
        } else {
            return null;
        }
        return cliente;
    }

    // procurar cliente retirba boolean
    // sobrescrever método procurar cliente
    public boolean verificaCliente(long CPF_CNPJ) throws SQLException {
        // cria objeto cliente
        Clientes cliente;
        // String com o comenado select para buscar cnpj do cliente
        String comando = "SELECT * FROM CLIENTES WHERE CPF_CNPJ = ?;";
        // prepara nosso comando sql
        stm = conn.prepareStatement(comando);
        // seta o valor que iremos passar para nosso campo "?"
        stm.setLong(1, CPF_CNPJ);
        // execura o comando
        rs = stm.executeQuery();
        // chama rs para receber os dados do banco
        if (rs.next()) { // caso encontre um clietne com o CPF_CNPJ informado retorna true
            return true;
        } else {
            return false;
        }
    }
    
    public boolean verificaEmail(String email) throws SQLException {
        // String com o comenado select para buscar cnpj do cliente
        String comando = "SELECT * FROM CLIENTES WHERE EMAIL = ?;";
        // prepara nosso comando sql
        stm = conn.prepareStatement(comando);
        // seta o valor que iremos passar para nosso campo "?"
        stm.setString(1, email);
        // execura o comando
        rs = stm.executeQuery();
        // chama rs para receber os dados do banco
        if (rs.next()) { // caso encontre um clietne com o CPF_CNPJ informado retorna true
            return true;
        } else {
            return false;
        }
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
                    rs.getString("EnderecoCliente"), rs.getString("Email"), rs.getLong("CPF_CNPJ"), rs.getString("Telefone")));
        }
        return clientes;
    }
}
