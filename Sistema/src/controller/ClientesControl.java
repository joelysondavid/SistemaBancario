package controller;

import framework.DaoConexao;
import java.math.BigInteger;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
    public void insertCliente() throws SQLException {
        if (cpfValido() == true) {
            // instancia um novo objeto
            cliente = new Clientes(frmClientes.getTxtNome().getText(), frmClientes.getTxtEndereco().getText(),
                    frmClientes.getTxtEmail().getText(), Long.parseLong(frmClientes.getTxtCPF_CNPJ().getText().trim()),
                    frmClientes.getTxtTelefone().getText().trim());

            // chama o metodo para inserir cliente
            clienteDAO.insertCliente(cliente);
            // chama o método mostar clientes para atualizar a tabela
            // mostrarClientes(); // recarrega a tabela
            frmClientes.limparCampos(); // chama o método para limpar os campos
       } else {
            JOptionPane.showMessageDialog(null, "CPF inválido, tente novamente!", "Aviso!", JOptionPane.WARNING_MESSAGE);
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

        if (frmLogin.chave == 'E') {
            Clientes cliente = clienteDAO.procurarCliente(loginDao.getIdCliente());
            frmClientes.getTxtNome().setText(cliente.getNomeCliente());
            frmClientes.getTxtEmail().setText(cliente.getEmail());
            frmClientes.getTxtCPF_CNPJ().setText("" + cliente.getDocumento());
            frmClientes.getTxtTelefone().setText("" + cliente.getTelefone());
            frmClientes.getTxtEndereco().setText(cliente.getEnderecoCliente());
        } else if (frmLogin.chave == 'C') {
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
        // caso o cpf seja com os 11 digitos iguais ele é falso
        if ((!cpf.equals("11111111111") || !cpf.equals("22222222222")
                || !cpf.equals("33333333333") || !cpf.equals("44444444444")
                || !cpf.equals("55555555555") || !cpf.equals("66666666666")
                || !cpf.equals("77777777777") || !cpf.equals("88888888888")
                || !cpf.equals("99999999999") || !cpf.equals("00000000000")) && cpf.length() == 11) {
            // variaveis que iremos utilizar
            int n; // para comparar com o resto
            int result = 0; // resultado da primeira multiplicação com as somas
            int rest; // resto da divisão
            int num = 0; // digito para
            int mult = 10; // peso das multiplicações que serão realizadas no for abaixo
            for (int i = 0; i < 9; i++) {
                // num recebe valor numero do caracter da string da posição de (i) - 48 para transformar o caracter em inteiro
                num = cpf.charAt(i) - 48;
                result += num * mult; // recebe e incrementa o resultado do numero gerado a cima multiplicado pelo peso da multiplicação
                mult--; // decrementa a variavel mult
            }
            // pega o resto da divisão do resultado da somatoria dos digitos por 11
            rest = (result * 10) % 11;
            n = cpf.charAt(9) - 48;
            if (rest == n) {
                // caso o resultado seja 2 significa que o cpf passou na validação do primeiro digito
                // A validação do segundo dígito é semelhante à primeira, porém vamos 
                // considerar os 9 primeiros dígitos, mais o primeiro dígito verificador, 
                // e vamos multiplicar esses 10 números pela sequencia decrescente de 11 a 2
                mult = 11;
                result = 0; // seta variável result como 0
                for (int i = 0; i < 10; i++) {
                    // num recebe valor numero do caracter da string da posição de (i) - 48 para transformar o caracter em inteiro
                    num = cpf.charAt(i) - 48;
                    result += num * mult; // recebe e incrementa o resultado do numero gerado a cima multiplicado pelo peso da multiplicação
                    mult--; // decrementa a variavel mult
                }

                rest = (result * 10) % 11;
                n = cpf.charAt(10) - 48;
                if (rest == n) {
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        return false;
    }
}
