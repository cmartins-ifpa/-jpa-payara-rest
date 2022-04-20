package entidades;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity 
public class Aluno {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
    private String nome;
    
    @Transient
    private Integer idade;
    
    @Temporal(TemporalType.DATE)
    private Date dtnasc;

    public Aluno() {}
    
	public Aluno(String nome2, Date dataNasc) {
		this.nome= nome2;
		this.dtnasc = dataNasc;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Integer getIdade() {
		LocalDate dtLocalNasc = LocalDate.ofInstant(this.dtnasc.toInstant(), ZoneId.systemDefault());
		this.idade = Period.between(dtLocalNasc, LocalDate.now()).getYears();
		return idade;
	}
	public Date getDtnasc() {
		return dtnasc;
	}
	public void setDtnasc(Date dtnasc) {
		this.dtnasc = dtnasc;
	}

	@Override
	public String toString() {
		return "Aluno [id=" + id + ", nome=" + nome + ", dtnasc=" + dtnasc + "]";
	}    
}
