package controller;

import framework.DaoConexao;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Clientes;
import model.ClientesDAO;
import view.FRM_Clientes;

/**
 *
 * @author Joelyson David
 */
public class ClientesControl {

    // objetos pra trabalhar com a view e a nossa modelo
    private ClientesDAO clienteDAO = new ClientesDAO();
    private FRM_Clientes frmClientes;
    private Clientes cliente;

    // define o formulario que vamos trabalho através do parametro informado
    public ClientesControl(FRM_Clientes frmClientes) {
        this.frmClientes = frmClientes;
    }

    // incluir novo cliente
    public void insertCliente() throws SQLException {

        // instancia um novo objeto
        cliente = new Clientes(frmClientes.getTxtNome().getText(), frmClientes.getTxtEndereco().getText(),
                frmClientes.getTxtEmail().getText(), Integer.parseInt(frmClientes.getTxtCPF_CNPJ().getText().trim()),
                Integer.parseInt(frmClientes.getTxtTelefone().getText().trim()));

        // chama o metodo para inserir cliente
        int flag = clienteDAO.insertCliente(cliente);
        // chama o método mostar clientes para atualizar a tabela
        mostrarClientes();

    }

    // deletar cliente
    public void deleteCliete() throws SQLException {
        int codigo = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe o código do cliente para deletar: ", "Deletar", JOptionPane.QUESTION_MESSAGE));

        // chamar o método apagar cliente
        clienteDAO.deleteCliente(codigo);
        // chama o método mostar clientes para atualizar a tabela
        mostrarClientes();

    }

    // alterar cliente
    public void updateCliente() throws SQLException {
        int codigo = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe o código do cliente para alteração: ", "Alterar", JOptionPane.QUESTION_MESSAGE));
        // passa os novos dados ao cliente
        cliente = new Clientes(frmClientes.getTxtNome().getText(), frmClientes.getTxtEndereco().getText(), frmClientes.getTxtEmail().getText(),
                Integer.parseInt(frmClientes.getTxtCPF_CNPJ().getText()), Integer.parseInt(frmClientes.getTxtTelefone().getText()));
        // chama o método para alteração do cliente no bd
        clienteDAO.updateCliente(cliente, codigo);
        JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!", "Atualizado", JOptionPane.INFORMATION_MESSAGE);
    }

    // procura Cliente nome
    public ArrayList<Clientes> procuraCliente() throws SQLException {
        // objeto clientes obtendo os clientes da base
        ArrayList<Clientes> clientes = clienteDAO.procurarCliente(frmClientes.getTxtProcurar().getText());
        // string recebendo o nome do cliente 
        String nome = frmClientes.getTxtProcurar().getText();
        // objeto clientes obtendo os clientes da base
        // cria um objeto DefaultTableModel que recebe como valor nossa Jtable
        DefaultTableModel dtmClientes = (DefaultTableModel) frmClientes.getTbClientes().getModel();
        // seta nossa dtm para que atualize sem duplicar os dados que ja existem
        dtmClientes.setNumRows(0);
        for (Clientes cliente : clientes) {
            dtmClientes.addRow(new Object[]{cliente.getCodigoCliente(), cliente.getNomeCliente(), cliente.getEnderecoCliente(), cliente.getEmail(), cliente.getDocumento(), cliente.getTelefone()});

        }
        return clientes;
    } // falta criar o segundo procurar clietne com cpfff

    public boolean verificaCliente() throws SQLException {
        // cria uma 'flag' para verificar se o cliente existe
        boolean verifica = clienteDAO.verificaCliente(Integer.parseInt(frmClientes.getTxtCPF_CNPJ().getText()));
        if (verifica == false) {
            return false;
        } else {
            JOptionPane.showMessageDialog(null, "Já existe um cliente cadastrado com esse CPF/CNPJ!\n\tTenteNovamente!", "Aviso!", JOptionPane.WARNING_MESSAGE);
            return true;
        }

    }

    // mostrar todo os clientes
    public ArrayList<Clientes> mostrarClientes() throws SQLException {
        // objeto clientes obtendo os clientes da base
        ArrayList<Clientes> clientes = clienteDAO.mostraClientes();
        // cria um objeto DefaultTableModel que recebe como valor nossa Jtable
        DefaultTableModel dtmClientes = (DefaultTableModel) frmClientes.getTbClientes().getModel();
        // seta nossa dtm para que atualize sem duplicar os dados que ja existem
        dtmClientes.setNumRows(0);
        for (Clientes cliente : clientes) {
            dtmClientes.addRow(new Object[]{cliente.getCodigoCliente(), cliente.getNomeCliente(), cliente.getEnderecoCliente(), cliente.getEmail(), cliente.getDocumento(), cliente.getTelefone()});

        }
        return clientes;
    }
}
