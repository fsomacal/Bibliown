public class Empregado extends Pessoa {
    public Empregado(String id, String nome, String senha) {
        super(nome, id, senha, 2);
    }

    public String getId() { return id; }
    public String getNome() { return nome; }

    @Override
public String toString() {
    return "Empregado - Nome: " + getNome() + " | ID: " + getId() + " | Função: " + funcao;
}

}