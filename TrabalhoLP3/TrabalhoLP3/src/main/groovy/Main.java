import controller.LivroController;
import controller.UsuarioController;
import model.LivroModel;
import model.UsuarioModel;

import javax.swing.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        UsuarioController usuarioController = new UsuarioController();
        LivroController livroController = new LivroController();

        String menu = "";
        do {
            menu = JOptionPane.showInputDialog(
                    "=== MENU CRUD ===\n" +
                    "1 - Cadastrar Usuário\n" +
                    "2 - Listar Usuários\n" +
                    "3 - Editar Usuário\n" +
                    "4 - Remover Usuário\n" +
                    "5 - Cadastrar Livro\n" +
                    "6 - Listar Livros\n" +
                    "7 - Editar Livro\n" +
                    "8 - Remover Livro\n" +
                    "9 - Sair"
            );

            switch (menu) {
                case "1":
                    UsuarioModel novoUsuario = new UsuarioModel();
                    novoUsuario.setNome(JOptionPane.showInputDialog("Nome:"));
                    novoUsuario.setEmail(JOptionPane.showInputDialog("Email:"));
                    JOptionPane.showMessageDialog(null, usuarioController.salvar(novoUsuario));
                    break;

                case "2":
                    List<UsuarioModel> usuarios = usuarioController.buscarTodos();
                    String listaU = "";
                    for (UsuarioModel u : usuarios) {
                        listaU += u.getIdUsuario() + " - " + u.getNome() + " (" + u.getEmail() + ")\n";
                    }
                    JOptionPane.showMessageDialog(null, listaU.isEmpty() ? "Nenhum usuário encontrado." : listaU);
                    break;

                case "3":
                    Long idEditarU = Long.parseLong(JOptionPane.showInputDialog("ID do usuário para editar:"));
                    List<UsuarioModel> todosU = usuarioController.buscarTodos();
                    UsuarioModel usuarioEncontrado = null;
                    for (UsuarioModel u : todosU) {
                        if (u.getIdUsuario().equals(idEditarU)) {
                            usuarioEncontrado = u;
                            break;
                        }
                    }
                    if (usuarioEncontrado != null) {
                        usuarioEncontrado.setNome(JOptionPane.showInputDialog("Novo nome:", usuarioEncontrado.getNome()));
                        usuarioEncontrado.setEmail(JOptionPane.showInputDialog("Novo email:", usuarioEncontrado.getEmail()));
                        JOptionPane.showMessageDialog(null, usuarioController.editar(usuarioEncontrado));
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuário não encontrado.");
                    }
                    break;

                case "4":
                    Long idExcluirU = Long.parseLong(JOptionPane.showInputDialog("ID do usuário para excluir:"));
                    JOptionPane.showMessageDialog(null, usuarioController.remover(idExcluirU));
                    break;

                case "5":
                    LivroModel novoLivro = new LivroModel();
                    novoLivro.setTitulo(JOptionPane.showInputDialog("Título do livro:"));
                    novoLivro.setAutor(JOptionPane.showInputDialog("Autor:"));
                    JOptionPane.showMessageDialog(null, livroController.salvar(novoLivro));
                    break;

                case "6":
                    List<LivroModel> livros = livroController.buscarTodos();
                    String listaL = "";
                    for (LivroModel l : livros) {
                        listaL += l.getIdLivro() + " - " + l.getTitulo() + " (" + l.getAutor() + ")\n";
                    }
                    JOptionPane.showMessageDialog(null, listaL.isEmpty() ? "Nenhum livro encontrado." : listaL);
                    break;

                case "7":
                    Long idEditarL = Long.parseLong(JOptionPane.showInputDialog("ID do livro para editar:"));
                    List<LivroModel> todosL = livroController.buscarTodos();
                    LivroModel livroEncontrado = null;
                    for (LivroModel l : todosL) {
                        if (l.getIdLivro().equals(idEditarL)) {
                            livroEncontrado = l;
                            break;
                        }
                    }
                    if (livroEncontrado != null) {
                        livroEncontrado.setTitulo(JOptionPane.showInputDialog("Novo título:", livroEncontrado.getTitulo()));
                        livroEncontrado.setAutor(JOptionPane.showInputDialog("Novo autor:", livroEncontrado.getAutor()));
                        JOptionPane.showMessageDialog(null, livroController.editar(livroEncontrado));
                    } else {
                        JOptionPane.showMessageDialog(null, "Livro não encontrado.");
                    }
                    break;

                case "8":
                    Long idExcluirL = Long.parseLong(JOptionPane.showInputDialog("ID do livro para excluir:"));
                    JOptionPane.showMessageDialog(null, livroController.remover(idExcluirL));
                    break;

                case "9":
                    JOptionPane.showMessageDialog(null, "Encerrando o sistema...");
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opção inválida!");
            }
        } while (!menu.equals("9"));
    }
}
