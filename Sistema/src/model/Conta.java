package model;

/**
 *
 * @author Alisson R
 */
public class Conta {

    private int codigoCliente;
    private long numConta;
    private int numCartoes;
    private double saldo;
    private String senha;
    //construtor vazio
    public Conta() {
    }

    // construtor com parametros
    public Conta(int codigoCliente, double saldo, String senha) {
        this.codigoCliente = codigoCliente;
        this.saldo = saldo;
        this.senha = senha;
    }

    // getters and setters
    public int getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(int codigoCliente) {
        this.codigoCliente = codigoCliente;
    }

    public long getNumConta() {
        return numConta;
    }

    public void setNumConta(long numConta) {
        this.numConta = numConta;
    }

    public int getNumCartoes() {
        return numCartoes;
    }

    public void setNumCartoes(int numCartoes) {
        this.numCartoes = numCartoes;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
