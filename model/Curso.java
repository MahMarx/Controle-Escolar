package model;
public class Curso {
    protected String nome;
    protected String codigo;
    protected int cargaHoraria;

    public Curso(String nome, String codigo, int cargaHoraria) {
        this.nome = nome;
        this.codigo = codigo;
        this.cargaHoraria = cargaHoraria;
    }

    public void detalharCurso() {
        System.out.println("Curso: " + nome + " | Código: " + codigo + " | Carga Horária: " + cargaHoraria);
    }

    public String getNome() { return nome; }
}