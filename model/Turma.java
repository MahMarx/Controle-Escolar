package model;
import java.util.ArrayList;
import java.util.List;

public class Turma {
    private String codigo;
    private Professor professor;
    private Curso curso;
    private List<Aluno> listaAlunos = new ArrayList<>();

    public Turma(String codigo, Professor professor, Curso curso) {
        this.codigo = codigo;
        this.professor = professor;
        this.curso = curso;
    }

    public void adicionarAluno(Aluno aluno) { listaAlunos.add(aluno); }
    public void removerAluno(Aluno aluno) { listaAlunos.remove(aluno); }

    public void resumoTurma() {
        System.out.println("Turma: " + codigo);
        System.out.println("Curso: " + curso.getNome());
        System.out.println("Professor: " + professor.getNome());
        System.out.println("Alunos matriculados: " + listaAlunos.size());
    }
}