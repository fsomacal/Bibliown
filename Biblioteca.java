import java.util.ArrayList;

public class Biblioteca {
    private String nome;
    private ArrayList<Livro> livros;
    
    public Biblioteca(String nome) {
        this.nome = nome;
        this.livros = new ArrayList<>();
    }

    public void adicionarLivro(Livro livro) {
        livros.add(livro);
    }

    public void listarLivros() {
        for (Livro livro : livros) {
            System.out.println(livro);
        }
    }

    public String getNome() {
        return nome;
    }
    public void emprestarLivro(Livro livro) {
        if (livros.contains(livro) && !livro.emprestado) {
            livro.emprestar(livro);
        } else {
            System.out.println("Erro: Livro não encontrado na biblioteca.");
        }
    }
}
