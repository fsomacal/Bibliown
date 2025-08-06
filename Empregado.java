public class Empregado extends Pessoa {
    public Empregado(String nome, String id, String senha, int funcao, double salario) {
        super(nome, id, senha, funcao);
        
    }

    @Override
    public String toString() {
        return "Empregado{" +
                "nome='" + GetNome() + '\'' +
                ", id='" + GetID() + '\'' +
                ", funcao=" + Getfuncao() 
                ;
    }
    
}
