import java.io.Serializable;
public abstract class Pessoa implements Serializable{
    protected String nome;
    protected String id;
    protected String senha;

    public Pessoa(String nome, String id, String senha){
        this.nome = nome;
        this.id = id;
        this.senha = senha;
    }

    public String GetNome(){return nome;};
    public String GetID(){return id;};
    public String GetSenha(){return senha;}

}