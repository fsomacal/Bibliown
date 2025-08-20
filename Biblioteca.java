import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Biblioteca implements Serializable {
    private static final long serialVersionUID = 1L;

    private String nome;
    private ArrayList<Livro> livros;
    private ArrayList<Cliente> clientes;
    private ArrayList<Empregado> empregados;

    public Biblioteca(String nome) {
        this.nome = nome;
        this.livros = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.empregados = new ArrayList<>();
    }

    public String getNome(){ return nome; }

    public void salvarEm(String caminho) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(caminho))) {
            oos.writeObject(this);
        }
    }
    public static Biblioteca carregarDe(String caminho) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(caminho))) {
            return (Biblioteca) ois.readObject();
        }
    }

    public void adicionarLivro(Livro livro) { livros.add(livro); }
    public boolean removerLivroPorId(String idLivro) {
        return livros.removeIf(l -> l.getIdLivro().equals(idLivro));
    }
    public Optional<Livro> buscarLivroPorId(String idLivro) {
        return livros.stream().filter(l -> l.getIdLivro().equals(idLivro)).findFirst();
    }
    public List<Livro> listarTodosLivros(){ return new ArrayList<>(livros); }
    public List<Livro> listarDisponiveis(){ return livros.stream().filter(l -> !l.isEmprestado()).collect(Collectors.toList()); }
    public List<Livro> listarEmprestados(){ return livros.stream().filter(Livro::isEmprestado).collect(Collectors.toList()); }

    public void adicionarCliente(Cliente c){ clientes.add(c); }
    public void adicionarEmpregado(Empregado e){ empregados.add(e); }
    public Optional<Cliente> buscarCliente(String id){ return clientes.stream().filter(c -> c.GetID().equals(id)).findFirst(); }
    public Optional<Empregado> buscarEmpregado(String id){ return empregados.stream().filter(e -> e.GetID().equals(id)).findFirst(); }

    public Optional<Pessoa> autenticar(String id, String senha){
        for (Empregado e : empregados) {
            if (e.GetID().equals(id) && e.GetSenha().equals(senha)) return Optional.of(e);
        }
        for (Cliente c : clientes) {
            if (c.GetID().equals(id) && c.GetSenha().equals(senha)) return Optional.of(c);
        }
        return Optional.empty();
    }

    public boolean emprestarLivro(String idLivro, String idCliente, int dias) {
        Optional<Livro> ol = buscarLivroPorId(idLivro);
        Optional<Cliente> oc = buscarCliente(idCliente);
        if (ol.isEmpty()) { System.out.println("Livro não encontrado."); return false; }
        if (oc.isEmpty()) { System.out.println("Cliente não encontrado."); return false; }
        Livro l = ol.get();
        if (l.isEmprestado()) { System.out.println("Livro já emprestado."); return false; }
        l.emprestar(idCliente, dias);
        return true;
    }

    public boolean devolverLivro(String idLivro) {
        Optional<Livro> ol = buscarLivroPorId(idLivro);
        if (ol.isEmpty()) { System.out.println("Livro não encontrado."); return false; }
        Livro l = ol.get();
        if (!l.isEmprestado()) { System.out.println("Livro não está emprestado."); return false; }
        l.devolver();
        return true;
    }

    public void listarClientes() {
        if (clientes.isEmpty()) {
            System.out.println("Nenhum cliente cadastrado.");
        } else {
            for (Cliente c : clientes) {
                System.out.println(c);
            }
        }
    }
    
    public void listarEmpregados() {
        if (empregados.isEmpty()) {
            System.out.println("Nenhum empregado cadastrado.");
        } else {
            for (Empregado e : empregados) {
                System.out.println(e);
            }
        }
    }
    
    public List<Livro> buscarPorTitulo(String termo){
        return livros.stream().filter(l -> l.getTitulo().toLowerCase().contains(termo.toLowerCase())).collect(Collectors.toList());
    }
    public List<Livro> buscarPorAutor(String termo){
        return livros.stream().filter(l -> l.getAutor().toLowerCase().contains(termo.toLowerCase())).collect(Collectors.toList());
    }
    public List<Livro> buscarPorGenero(String genero){
        return livros.stream().filter(l -> l.getGenero().equalsIgnoreCase(genero)).collect(Collectors.toList());
    }

    public List<Livro> ordenarPorTitulo(){ return livros.stream().sorted(Comparator.comparing(Livro::getTitulo)).collect(Collectors.toList()); }
    public List<Livro> ordenarPorAutor(){ return livros.stream().sorted(Comparator.comparing(Livro::getAutor)).collect(Collectors.toList()); }
    public List<Livro> ordenarPorAno(){ return livros.stream().sorted(Comparator.comparingInt(Livro::getAnoPublicacao)).collect(Collectors.toList()); }
    public List<Livro> ordenarPorGenero(){ return livros.stream().sorted(Comparator.comparing(Livro::getGenero)).collect(Collectors.toList()); }
}