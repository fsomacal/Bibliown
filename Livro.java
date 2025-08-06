public class Livro {
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private boolean emprestado = false;

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

