package controller;

import model.LoginDAO;
import view.FRM_Home;

/**
 *
 * @author Joelyson David
 */
public class HomeControl {

    private LoginDAO loginDao = new LoginDAO();
    private FRM_Home frmHome;
    private static long idCliente;
    private static String nome;

    public HomeControl(FRM_Home frmHome) {
        this.frmHome = frmHome;
        setIdCliente(loginDao.getIdCliente());
        setNome(loginDao.getNome());
    }
    public long getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(long idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
