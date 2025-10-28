package ui;

import model.*;
import java.util.*;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static List<Aluno> alunos = new ArrayList<>();
    private static List<Professor> professores = new ArrayList<>();
    private static List<Curso> cursos = new ArrayList<>();
    private static List<Turma> turmas = new ArrayList<>();

    public static void main(String[] args) {
        int opcao;
        do {
            exibirMenu();
            opcao = sc.nextInt();
            sc.nextLine();
            switch(opcao) {
                case 1: cadastrarAluno(); break;
                case 2: cadastrarProfessor(); break;
                case 3: cadastrarCurso(); break;
                case 4: criarTurma(); break;
                case 5: registrarAvaliacao(); break;
                case 6: gerarRelatorios(); break;
                case 0: System.out.println("Saindo do sistema..."); break;
                default: System.out.println("Opção inválida!");
            }
        } while(opcao != 0);
    }

    private static void exibirMenu() {
        System.out.println("\n=== SGE EduConnect ===");
        System.out.println("1. Cadastrar Aluno");
        System.out.println("2. Cadastrar Professor");
        System.out.println("3. Cadastrar Curso");
        System.out.println("4. Criar Turma");
        System.out.println("5. Registrar Avaliação");
        System.out.println("6. Gerar Relatórios");
        System.out.println("0. Sair");
        System.out.print("Escolha uma opção: ");
    }

    private static void cadastrarAluno() {
        System.out.print("Nome do aluno: ");
        String nome = sc.nextLine();
        System.out.print("Matrícula: ");
        String matricula = sc.nextLine();
        System.out.print("Curso: ");
        String curso = sc.nextLine();
        alunos.add(new Aluno(nome, matricula, curso));
        System.out.println("Aluno cadastrado com sucesso!");
    }

    private static void cadastrarProfessor() {
        System.out.print("Nome do professor: ");
        String nome = sc.nextLine();
        System.out.print("Especialidade: ");
        String especialidade = sc.nextLine();
        System.out.print("Registro: ");
        String registro = sc.nextLine();
        professores.add(new Professor(nome, especialidade, registro));
        System.out.println("Professor cadastrado com sucesso!");
    }

    private static void cadastrarCurso() {
        System.out.print("Nome do curso: ");
        String nome = sc.nextLine();
        System.out.print("Código: ");
        String codigo = sc.nextLine();
        System.out.print("Carga horária: ");
        int ch = sc.nextInt();
        sc.nextLine(); // Consumir \n
        System.out.print("Tipo (1-Presencial / 2-EAD): ");
        int tipo = sc.nextInt();
        sc.nextLine();
        if(tipo == 1) {
            System.out.print("Sala: ");
            String sala = sc.nextLine();
            cursos.add(new CursoPresencial(nome, codigo, ch, sala));
        } else {
            System.out.print("Plataforma: ");
            String plataforma = sc.nextLine();
            cursos.add(new CursoEAD(nome, codigo, ch, plataforma));
        }
        System.out.println("Curso cadastrado com sucesso!");
    }

    private static void criarTurma() {
        if(cursos.isEmpty() || professores.isEmpty()) {
            System.out.println("É necessário ter ao menos um curso e um professor cadastrados!");
            return;
        }
        System.out.print("Código da turma: ");
        String codigo = sc.nextLine();
        System.out.println("Selecione o professor:");
        for(int i=0;i<professores.size();i++) {
            System.out.println(i + " - " + professores.get(i).getNome());
        }
        int idxProf = sc.nextInt(); sc.nextLine();
        Professor professor = professores.get(idxProf);

        System.out.println("Selecione o curso:");
        for(int i=0;i<cursos.size();i++) {
            System.out.println(i + " - " + cursos.get(i).getNome());
        }
        int idxCurso = sc.nextInt(); sc.nextLine();
        Curso curso = cursos.get(idxCurso);

        Turma turma = new Turma(codigo, professor, curso);

        System.out.println("Deseja adicionar alunos? (s/n)");
        String resp = sc.nextLine();
        while(resp.equalsIgnoreCase("s") && !alunos.isEmpty()) {
            for(int i=0;i<alunos.size();i++) {
                System.out.println(i + " - " + alunos.get(i).getNome());
            }
            System.out.print("Escolha o aluno pelo índice: ");
            int idxAluno = sc.nextInt(); sc.nextLine();
            turma.adicionarAluno(alunos.get(idxAluno));
            System.out.println("Aluno adicionado. Adicionar outro? (s/n)");
            resp = sc.nextLine();
        }

        turmas.add(turma);
        System.out.println("Turma criada com sucesso!");
        turma.resumoTurma();
    }

    private static void registrarAvaliacao() {
        if(turmas.isEmpty()) {
            System.out.println("Não há turmas cadastradas!");
            return;
        }
        System.out.println("Selecione a turma:");
        for(int i=0;i<turmas.size();i++) {
            System.out.println(i + " - " + turmas.get(i).toString());
        }
        int idxTurma = sc.nextInt(); sc.nextLine();
        Turma turma = turmas.get(idxTurma);

        System.out.print("Descrição da avaliação: ");
        String desc = sc.nextLine();
        Avaliacao av = new Avaliacao(desc);

        System.out.print("Nota (0 a 10): ");
        double nota = sc.nextDouble(); sc.nextLine();
        av.atribuirNota(nota);

        System.out.println("Avaliação registrada para todos os alunos da turma!");
    }

    private static void gerarRelatorios() {
        System.out.println("=== Relatórios ===");
        System.out.println("Alunos:");
        for(Aluno a : alunos) System.out.println(a);
        System.out.println("Professores:");
        for(Professor p : professores) System.out.println(p);
        System.out.println("Cursos:");
        for(Curso c : cursos) c.detalharCurso();
        System.out.println("Turmas:");
        for(Turma t : turmas) t.resumoTurma();
    }
}
