package model;

/**
 *
 * Caique Francisco - Atualizado 23/05
 */
public class Clientes {
    private int codigoCliente;
    private char nomeCliente;
    private char enderecoCliente;
    private int documento;
    private String email;
    private int telefone;

    public Clientes(int codigoCliente, char nomeCliente, char enderecoCliente, String email, int telefone) {
        this.codigoCliente = codigoCliente;
        this.nomeCliente = nomeCliente;
        this.enderecoCliente = enderecoCliente;
        this.email = email;
        this.telefone = telefone;
    }
    
    public Clientes(){
    
    }
    
    public void cadastrarCliente(){
        
    }
    
    public void atualizarCliente(int codigoCliente){
        
    }
    
    public void deletarClientes(int codigoCliente){
    
    }
    
    public Clientes obterCliente(int codigoCliente){
        return um cliente;
    }
    
    public Clientes obterClientes(){
        return todos clientes;
    }
    
    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public char getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(char nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public char getEnderecoCliente() {
        return enderecoCliente;
    }

    public void setEnderecoCliente(char enderecoCliente) {
        this.enderecoCliente = enderecoCliente;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }
    
}
