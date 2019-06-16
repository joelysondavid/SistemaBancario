package controller;

import framework.DaoConexao;
import java.math.BigInteger;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Clientes;
import model.ClientesDAO;
import model.LoginDAO;
import view.FRM_Clientes;
import view.FRM_Login;

/**
 *
 * @author Joelyson David
 */
public class ClientesControl {

    // objetos pra trabalhar com a view e a nossa modelo
    private ClientesDAO clienteDAO = new ClientesDAO();
    private FRM_Clientes frmClientes;
    private LoginDAO loginDao = new LoginDAO();
    private Clientes cliente;

    // define o formulario que vamos trabalho através do parametro informado
    public ClientesControl(FRM_Clientes frmClientes) {
        this.frmClientes = frmClientes;
    }

    // incluir novo cliente
    public boolean insertCliente() throws SQLException {
        if (cpfValido() == true) {
            if (!clienteDAO.verificaEmail(frmClientes.getTxtEmail().getText())) {
                // instancia um novo objeto
                cliente = new Clientes(frmClientes.getTxtNome().getText(), frmClientes.getTxtEndereco().getText(),
                        frmClientes.getTxtEmail().getText(), Long.parseLong(frmClientes.getTxtCPF_CNPJ().getText().trim()),
                        frmClientes.getTxtTelefone().getText());
                // chama o metodo para inserir cliente
                clienteDAO.insertCliente(cliente);
                // chama o método mostar clientes para atualizar a tabela
                // mostrarClientes(); // recarrega a tabela
                frmClientes.limparCampos(); // chama o método para limpar os campos
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Email já está cadastrado, tente com outro email!", "Aviso!", JOptionPane.WARNING_MESSAGE);
                return false;
            }

        } else {
            JOptionPane.showMessageDialog(null, "CPF inválido, tente novamente!", "Aviso!", JOptionPane.WARNING_MESSAGE);
            return false;
        }
    }

// deletar cliente
    public void deleteCliete() throws SQLException {
        // codigo do Cliente
        int codigo = loginDao.getIdCliente();
        long codigoConta = loginDao.getIdConta();
        // pega o nome do cliente
        // validação para saber se tem certeza que deseja apagar        
        int confirma = JOptionPane.showConfirmDialog(null, "Tem certeza que deseja apagar?", "Confirma apagar o cliente?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        // se for confirmado chama o metodo para deletar o usuario do banco e atualiza a tabela
        if (confirma == 0) {
            // chamar o método apagar cliente
            clienteDAO.deleteCliente(codigo, codigoConta);
            // chama o método mostar clientes para atualizar a tabela
            FRM_Login frmLogin = new FRM_Login();
            frmLogin.setVisible(true);
            frmLogin.setEnabled(true);
            frmClientes.setVisible(false);
            frmClientes.setEnabled(false);
        }
    }

    // alterar cliente
    public void updateCliente() throws SQLException {
        // pega o código
        int codigo = loginDao.getIdCliente();
        int confirma = JOptionPane.showConfirmDialog(null, "Confirma a alteração?", "Confirmação", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirma == 0) {
            // passa os novos dados ao cliente
            cliente = new Clientes(frmClientes.getTxtNome().getText(), frmClientes.getTxtEndereco().getText(), frmClientes.getTxtEmail().getText(),
                    Long.parseLong(frmClientes.getTxtCPF_CNPJ().getText()), frmClientes.getTxtTelefone().getText());
            // chama o método para alteração do cliente no bd
            clienteDAO.updateCliente(cliente, codigo);
            JOptionPane.showMessageDialog(null, "Cliente atualizado com sucesso!", "Atualizado", JOptionPane.INFORMATION_MESSAGE);
        }

    }

    // procura Cliente nome
    /* public ArrayList<Clientes> procuraCliente() throws SQLException {
        // objeto clientes obtendo os clientes da base
        ArrayList<Clientes> clientes = clienteDAO.procurarCliente(frmClientes.getTxtProcurar().getText());
        // string recebendo o nome do cliente 
        //String nome = frmClientes.getTxtProcurar().getText();
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
     */
    public boolean clienteExiste() throws SQLException {
        // cria uma 'flag' para verificar se o cliente existe
        boolean verifica = clienteDAO.verificaCliente(Long.parseLong(frmClientes.getTxtCPF_CNPJ().getText()));
        if (verifica == false) {
            return false;
        } else {
            JOptionPane.showMessageDialog(null, "Já existe um cliente cadastrado com esse CPF/CNPJ!\n\tTenteNovamente!", "Aviso!", JOptionPane.WARNING_MESSAGE);
            return true;
        }

    }

    // mostrar todo os clientes
    /* public ArrayList<Clientes> mostrarClientes() throws SQLException {
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
    }*/
    // método seta cliente na tela
    public void mostraDados() throws SQLException {
        FRM_Login frmLogin = new FRM_Login();

        if (frmLogin.getChave() == 'E') {
            Clientes cliente = clienteDAO.procurarCliente(loginDao.getIdCliente());
            frmClientes.getTxtNome().setText(cliente.getNomeCliente());
            frmClientes.getTxtEmail().setText(cliente.getEmail());
            frmClientes.getTxtCPF_CNPJ().setText("" + cliente.getDocumento());
            frmClientes.getTxtTelefone().setText("" + cliente.getTelefone());
            frmClientes.getTxtEndereco().setText(cliente.getEnderecoCliente());
        } else if (frmLogin.getChave() == 'C') {
            frmClientes.getTxtNome().setText("");
            frmClientes.getTxtEmail().setText("");
            frmClientes.getTxtCPF_CNPJ().setText("");
            frmClientes.getTxtTelefone().setText("");
            frmClientes.getTxtEndereco().setText("");
        }

    }

    // método para validar CPF
    public boolean cpfValido(/*String cpf*/) {
        String cpf = frmClientes.getTxtCPF_CNPJ().getText();
        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (cpf.equals("00000000000")
                || cpf.equals("11111111111")
                || cpf.equals("22222222222") || cpf.equals("33333333333")
                || cpf.equals("44444444444") || cpf.equals("55555555555")
                || cpf.equals("66666666666") || cpf.equals("77777777777")
                || cpf.equals("88888888888") || cpf.equals("99999999999")
                || (cpf.length() != 11)) {
            return (false);
        }

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i = 0; i < 9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0         
                // (48 eh a posicao de '0' na tabela ASCII)         
                num = (int) (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig10 = '0';
            } else {
                dig10 = (char) (r + 48); // converte no respectivo caractere numerico
            }
            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for (i = 0; i < 10; i++) {
                num = (int) (cpf.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11)) {
                dig11 = '0';
            } else {
                dig11 = (char) (r + 48);
            }

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == cpf.charAt(9)) && (dig11 == cpf.charAt(10))) {
                return (true);
            } else {
                return (false);
            }
        } catch (InputMismatchException erro) {
            return (false);
        }
    }
}
