package br.senai.sp.jaguariuna.sccv.mBeans;

import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.senai.sp.jaguariuna.sccv.entities.Usuario;
import br.senai.sp.jaguariuna.sccv.entities.UsuarioAdministrador;
import br.senai.sp.jaguariuna.sccv.uDao.UsuarioDao;
import br.senai.sp.jaguariuna.sccv.utils.StringToMD5;

@ManagedBean(eager = true)
@SessionScoped
public class AlunoIndexMBean {

	private String modoSelecionado;
	private String funcaoCPF;
	private Usuario usuario;
	private UsuarioAdministrador usuarioAdministrador;
	private String cpfOuNif;
	private String senha;
	private UsuarioDao usuarioDao;

	public AlunoIndexMBean() {
		modoSelecionado = "user";
		funcaoCPF = "!TestaCPF(this.value) ? this.value = '' : this.value; avisoGrowl(TestaCPF(this.value));";
		usuarioDao = new UsuarioDao();
	}

	public void trocaModoSelecionado() {
		modoSelecionado = modoSelecionado.equals("user") ? "admin" : "user";
		funcaoCPF = modoSelecionado.equals("user")
				? "!TestaCPF(this.value) ? this.value = '' : this.value; avisoGrowl(TestaCPF(this.value));"
				: "";
	}

	public String getCpfOuNif() {
		return cpfOuNif;
	}

	public void setCpfOuNif(String cpfOuNif) {
		this.cpfOuNif = cpfOuNif;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getModoSelecionado() {
		return modoSelecionado;
	}

	public void setModoSelecionado(String modoSelecionado) {
		this.modoSelecionado = modoSelecionado;
	}

	public String getFuncaoCPF() {
		return funcaoCPF;
	}

	public void setFuncaoCPF(String funcaoCPF) {
		this.funcaoCPF = funcaoCPF;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public UsuarioAdministrador getUsuarioAdministrador() {
		return usuarioAdministrador;
	}

	public void setUsuarioAdministrador(UsuarioAdministrador usuarioAdministrador) {
		this.usuarioAdministrador = usuarioAdministrador;
	}

	public String fazerLogin() {
		if (modoSelecionado.equals("user")) {
			try {
				Usuario usuarioLocal = usuarioDao.buscaUsuarioPorCpf(cpfOuNif);
				if (usuarioLocal != null) {
					if (usuarioLocal.getSenha().equals(StringToMD5.convertStringToMd5(senha))) {
					
							usuario = usuarioLocal;
							return "home?faces-redirect=true";
						}
					 else {
						FacesContext.getCurrentInstance().addMessage(null,
								new FacesMessage("Usuario ou senha incorretos !"));
					}
				} else {
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario n�o encontrado !"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.toString()));
			}
		} else if (modoSelecionado.equals("admin")) {

		}
		return null;
	}

}
