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
        clienteDAO.insertCliente(cliente);
    }

    // deletar cliente
    public void deleteCliete() throws SQLException {
        int codigo = Integer.parseInt(JOptionPane.showInputDialog(null, "Informe o código do cliente para deletar: ", "Deletar", JOptionPane.QUESTION_MESSAGE));

        // chamar o método apagar cliente
        clienteDAO.deleteCliente(codigo);

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
    public void procuraCliente() throws SQLException {
        // string recebendo o nome do cliente 
        String nome = frmClientes.getTxtProcurar().getText();
        // chama o método para procurar o nome do cliente
        clienteDAO.procurarCliente(nome);
    }
    
    // mostrar todo os clientes
    public ArrayList<Clientes> mostrarClientes() throws SQLException {
        // objeto clientes obtendo os clientes da base
        ArrayList<Clientes> clientes = clienteDAO.mostraClientes();
        // 
        DefaultTableModel dtmClientes = (DefaultTableModel) frmClientes.getTbClientes().getModel();
        
        for(Clientes cliente: clientes) {
            dtmClientes.addRow(new Object[]{cliente.getCodigoCliente(), cliente.getNomeCliente(), cliente.getEnderecoCliente(), cliente.getEmail(), cliente.getDocumento(), cliente.getTelefone()});
        }
        
        return clientes;
    }
}
