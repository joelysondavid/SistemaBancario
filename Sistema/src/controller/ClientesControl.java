/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.SQLException;
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
}
