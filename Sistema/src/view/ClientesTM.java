package view;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import model.Clientes;

/**
 *
 * @author Joelyson David
 */
public class ClientesTM extends AbstractTableModel {

    // array dos clientes
    private ArrayList<Clientes> linhas;
    private String[] colunas = {"Código", "Nome", "Endereço", "Email", "CPF/CNPJ", "Telefone"};

    // construtor vazio
    public ClientesTM() {
        linhas = new ArrayList<Clientes>();
    }

    // construtor passando um array
    public ClientesTM(ArrayList<Clientes> clientes) {
        linhas = new ArrayList<>(clientes);
    }

    @Override
    public int getRowCount() {
        return linhas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int coluna) {
        return colunas[coluna]; // retorna o indice da coluna
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0:
                return linhas.get(linha).getCodigoCliente();
            case 1:
                return linhas.get(linha).getNomeCliente();
            case 2:
                return linhas.get(linha).getEnderecoCliente();
            case 3:
                return linhas.get(linha).getEmail();
            case 4:
                return linhas.get(linha).getDocumento();
            case 5:
                return linhas.get(linha).getTelefone();
        }
        return null;
    }

}
