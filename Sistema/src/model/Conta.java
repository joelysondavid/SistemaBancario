package model;

/**
 *
 * @author Alisson R
 */
public class Conta {

    private int codigoCliente;
    private int numConta;
    private int numCartoes;
    private int saldo;
    private char senha;
<<<<<<< HEAD
    
    
    
    
    
    
    
    
    
    
    
=======

    //construtor vazio
    public Conta() {
    }

    // construtor com parametros
    public Conta(int codigoCliente, int numConta, int numCartoes, int saldo, char senha) {
        this.codigoCliente = codigoCliente;
        this.numConta = numConta;
        this.numCartoes = numCartoes;
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

    public int getNumConta() {
        return numConta;
    }

    public void setNumConta(int numConta) {
        this.numConta = numConta;
    }

    public int getNumCartoes() {
        return numCartoes;
    }

    public void setNumCartoes(int numCartoes) {
        this.numCartoes = numCartoes;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public char getSenha() {
        return senha;
    }

    public void setSenha(char senha) {
        this.senha = senha;
    }
>>>>>>> AlissonReis
}
