public class Cliente extends Pessoa {
    public Cliente(String id, String nome, String senha) {
        super(nome, id, senha, 1); 
    }

    public String getId() { return id; }
    public String getNome() { return nome; }

    @Override
    public String toString() {
        return "Cliente{id='" + id + "', nome='" + nome + "'}";
    }
}