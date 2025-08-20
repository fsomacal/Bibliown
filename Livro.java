import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Livro implements Serializable {
    private static final long serialVersionUID = 1L;

    private String titulo;
    private String autor;
    private String genero;
    private int anoPublicacao;
    private String idLivro;

    boolean emprestado; 
    private String emprestadoParaId; 
    private Date dataEmprestimo;
    private Date dataDevolucaoPrevista;

    public Livro(String idLivro, String titulo, String autor, String genero, int anoPublicacao) {
        this.idLivro = idLivro;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.anoPublicacao = anoPublicacao;
        this.emprestado = false;
    }

    public String getIdLivro(){ return idLivro; }
    public String getTitulo(){ return titulo; }
    public String getAutor(){ return autor; }
    public String getGenero(){ return genero; }
    public int getAnoPublicacao(){ return anoPublicacao; }
    public boolean isEmprestado(){ return emprestado; }
    public String getEmprestadoParaId(){ return emprestadoParaId; }
    public Date getDataEmprestimo(){ return dataEmprestimo; }
    public Date getDataDevolucaoPrevista(){ return dataDevolucaoPrevista; }

    public void emprestar(String clienteId, int dias) {
        if (emprestado) {
            System.out.println("Erro: Livro já está emprestado.");
            return;
        }
        this.emprestado = true;
        this.emprestadoParaId = clienteId;
        this.dataEmprestimo = new Date();
        this.dataDevolucaoPrevista = new Date(System.currentTimeMillis() + (long)dias * 24L * 60L * 60L * 1000L);
    }

    public void devolver() {
        if (!emprestado) {
            System.out.println("Erro: Livro não está emprestado.");
            return;
        }
        this.emprestado = false;
        this.emprestadoParaId = null;
        this.dataEmprestimo = null;
        this.dataDevolucaoPrevista = null;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String status;
        if (emprestado) {
            status = "EMPRESTADO para " + emprestadoParaId + " até " + sdf.format(dataDevolucaoPrevista);
        } else {
            status = "DISPONÍVEL";
        }
        return "[" + idLivro + "] " + titulo + " — " + autor + " (" + anoPublicacao + ", " + genero + ") | " + status;
    }
}