package principal;

import dao.AlunoDAO;
import model.Aluno;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AlunoDAO dao = new AlunoDAO();
        int opc;

        do {
            System.out.println("\n===== MENU =====");
            System.out.println("1 - Listar");
            System.out.println("2 - Inserir");
            System.out.println("3 - Atualizar");
            System.out.println("4 - Excluir");
            System.out.println("5 - Sair");
            System.out.print("Escolha uma opção: ");
            opc = sc.nextInt();
            sc.nextLine(); // limpar buffer

            switch (opc) {
                case 1:
                    List<Aluno> alunos = dao.listar();
                    System.out.println("\n--- Lista de Alunos ---");
                    for (Aluno a : alunos) {
                        System.out
                                .println(a.getId() + " - " + a.getNome() + " - " + a.getIdade() + " - " + a.getCurso());
                    }
                    break;
                case 2:
                    System.out.print("Nome: ");
                    String nome = sc.nextLine();
                    System.out.print("Idade: ");
                    int idade = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Curso: ");
                    String curso = sc.nextLine();
                    dao.inserir(new Aluno(0, nome, idade, curso));
                    break;
                case 3:
                    System.out.print("ID do aluno a atualizar: ");
                    int idAt = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Novo nome: ");
                    String n = sc.nextLine();
                    System.out.print("Nova idade: ");
                    int i = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Novo curso: ");
                    String c = sc.nextLine();
                    dao.atualizar(new Aluno(idAt, n, i, c));
                    break;
                case 4:
                    System.out.print("ID do aluno a excluir: ");
                    int idEx = sc.nextInt();
                    dao.excluir(idEx);
                    break;
                case 5:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (opc != 5);

        sc.close();
    }
}
