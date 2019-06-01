package model;

/**
 *
 * Caique Francisco - Atualizado 23/05
 */
public class Clientes {

    private int codigoCliente;
    private String nomeCliente;
    private String enderecoCliente;
    private String email;
    private long documento;
    private long telefone;

    public Clientes(String nomeCliente, String enderecoCliente, String email, long documento, long telefone) {
        this.nomeCliente = nomeCliente;
        this.enderecoCliente = enderecoCliente;
        this.documento = documento;
        this.email = email;
        this.telefone = telefone;
    }

    public Clientes() {

    }

    Clientes(int codigoCliente, String nomeCliente, String enderecoCliente, String email, long documento, long telefone) {
        this.codigoCliente = codigoCliente;
        this.nomeCliente = nomeCliente;
        this.enderecoCliente = enderecoCliente;
        this.documento = documento;
        this.email = email;
        this.telefone = telefone;
    }

    public void cadastrarCliente() {

    }

    public void atualizarCliente(int codigoCliente) {

    }

    public void deletarClientes(int codigoCliente) {

    }

    /*
    public Clientes obterCliente(int codigoCliente){
        return um cliente;
    }
    
    public Clientes obterClientes(){
        return todos clientes;
    }*/
    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public String getEnderecoCliente() {
        return enderecoCliente;
    }

    public void setEnderecoCliente(String enderecoCliente) {
        this.enderecoCliente = enderecoCliente;
    }

    public long getDocumento() {
        return documento;
    }

    public void setDocumento(long documento) {
        this.documento = documento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getTelefone() {
        return telefone;
    }

    public void setTelefone(long telefone) {
        this.telefone = telefone;
    }

}
