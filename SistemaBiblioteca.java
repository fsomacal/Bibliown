import java.util.*;

public class SistemaBiblioteca {
    private static final String ARQUIVO = "biblioteca.bin";

    public static void main(String[] args) {
        Biblioteca bib = carregarOuCriar();

        Scanner sc = new Scanner(System.in);
        System.out.println("=== Sistema da Biblioteca ===");
        loop:
        while (true) {
            System.out.println("\n1) Cadastrar cliente");
            System.out.println("2) Cadastrar empregado");
            System.out.println("3) Adicionar livro");
            System.out.println("4) Listar livros (todos/disponíveis/emprestados)");
            System.out.println("5) Buscar livro (título/autor/gênero)");
            System.out.println("6) Ordenar livros (título/autor/ano/gênero)");
            System.out.println("7) Emprestar livro");
            System.out.println("8) Devolver livro");
            System.out.println("9) Salvar e sair");
            System.out.println("10) Listar clientes");
            System.out.println("11) Listar empregados");

            System.out.print("Escolha: ");
            String op = sc.nextLine().trim();

            try {
                switch (op) {
                    case "1":
                        System.out.print("ID cliente: "); String idc = sc.nextLine();
                        System.out.print("Nome: "); String nomec = sc.nextLine();
                        System.out.print("Senha: "); String senhac = sc.nextLine();
                        bib.adicionarCliente(new Cliente(idc, nomec, senhac));
                        System.out.println("Cliente cadastrado.");
                        break;
                    case "2":
                        System.out.print("ID empregado: "); String ide = sc.nextLine();
                        System.out.print("Nome: "); String nomee = sc.nextLine();
                        System.out.print("Senha: "); String senhae = sc.nextLine();
                        bib.adicionarEmpregado(new Empregado(ide, nomee, senhae));
                        System.out.println("Empregado cadastrado.");
                        break;
                    case "3":
                        System.out.print("ID livro: "); String idl = sc.nextLine();
                        System.out.print("Título: "); String tit = sc.nextLine();
                        System.out.print("Autor: "); String aut = sc.nextLine();
                        System.out.print("Gênero: "); String gen = sc.nextLine();
                        System.out.print("Ano: "); int ano = Integer.parseInt(sc.nextLine());
                        bib.adicionarLivro(new Livro(idl, tit, aut, gen, ano));
                        System.out.println("Livro adicionado.");
                        break;
                    case "4":
                        System.out.println("a) Todos  b) Disponíveis  c) Emprestados");
                        String sub = sc.nextLine().trim().toLowerCase();
                        List<Livro> lista = sub.equals("b") ? bib.listarDisponiveis() : sub.equals("c") ? bib.listarEmprestados() : bib.listarTodosLivros();
                        lista.forEach(System.out::println);
                        break;
                    case "5":
                        System.out.println("a) Título  b) Autor  c) Gênero");
                        String bopt = sc.nextLine().trim().toLowerCase();
                        System.out.print("Termo: "); String termo = sc.nextLine();
                        List<Livro> res;
                        if (bopt.equals("b")) res = bib.buscarPorAutor(termo);
                        else if (bopt.equals("c")) res = bib.buscarPorGenero(termo);
                        else res = bib.buscarPorTitulo(termo);
                        res.forEach(System.out::println);
                        break;
                    case "6":
                        System.out.println("a) Título  b) Autor  c) Ano  d) Gênero");
                        String o = sc.nextLine().trim().toLowerCase();
                        List<Livro> ord = o.equals("b") ? bib.ordenarPorAutor()
                                            : o.equals("c") ? bib.ordenarPorAno()
                                            : o.equals("d") ? bib.ordenarPorGenero()
                                            : bib.ordenarPorTitulo();
                        ord.forEach(System.out::println);
                        break;
                    case "7":
                        System.out.print("ID livro: "); String idLe = sc.nextLine();
                        System.out.print("ID cliente: "); String idCl = sc.nextLine();
                        System.out.print("Dias de empréstimo: "); int dias = Integer.parseInt(sc.nextLine());
                        if (bib.emprestarLivro(idLe, idCl, dias)) System.out.println("Empréstimo realizado.");
                        break;
                    case "8":
                        System.out.print("ID livro: "); String idLd = sc.nextLine();
                        if (bib.devolverLivro(idLd)) System.out.println("Devolução realizada.");
                        break;
                    case "9":
                        salvar(bib);
                        System.out.println("Dados salvos. Até mais!");
                        break loop;
                    case "10":
                        bib.listarClientes();
                        break;
                    case "11":
                        bib.listarEmpregados();
                        break;
                    default:
                        System.out.println("Opção inválida.");
                }
            } catch (Exception ex) {
                System.out.println("Erro: " + ex.getMessage());
            }
        }
    }

    private static Biblioteca carregarOuCriar() {
        try {
            return Biblioteca.carregarDe(ARQUIVO);
        } catch (Exception e) {
            Biblioteca b = new Biblioteca("Minha Biblioteca");
            b.adicionarEmpregado(new Empregado("admin", "Administrador", "admin"));
            return b;
        }
    }

    private static void salvar(Biblioteca b) {
        try {
            b.salvarEm(ARQUIVO);
        } catch (Exception e) {
            System.out.println("Falha ao salvar: " + e.getMessage());
        }
    }
}