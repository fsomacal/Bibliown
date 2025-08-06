import java.io.Serializable;
public abstract class Pessoa implements Serializable{
    protected String nome;
    protected String id;
    protected String senha;
    protected int funcao;
    //1- pessoa comum, 2- bibliotecario

    public Pessoa(String nome, String id, String senha, int funcao) {
        this.nome = nome;
        this.id = id;
        this.senha = senha;
        this.funcao = funcao;
    }

    public String GetNome(){return nome;};
    public String GetID(){return id;};
    public String GetSenha(){return senha;}
    public int Getfuncao(){return funcao;}
    
}