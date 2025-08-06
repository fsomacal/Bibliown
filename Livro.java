import java.text.SimpleDateFormat;
import java.util.Date;


public class Livro {
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private float IDLivro;
    public boolean emprestado = false;
    public String dataEmprestimo;
    public String dataDevolucao;

    public Livro(String titulo, String autor, int anoPublicacao) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", anoPublicacao=" + anoPublicacao +
                '}';
    }    

    public void emprestar(Livro livro) {
        if (!livro.emprestado) {
            emprestado = true;


            Date dataAtual = new Date();
            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
            String dataFormatada = formato.format(dataAtual);


            this.dataEmprestimo = dataFormatada;

            Date dataDevolucao = new Date(dataAtual.getTime() + (7 * 24 * 60 * 60 * 1000));
        } else {
            
            System.out.println("Erro: Livro já está emprestado.");
        }
    }
    public void devolver(Livro livro) {
        if (livro.emprestado) {
            emprestado = false;
        } else {
            System.out.println("Erro: Livro não está emprestado.");
        }
    }
}

