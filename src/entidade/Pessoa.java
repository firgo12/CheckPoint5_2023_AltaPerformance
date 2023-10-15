package entidade;

public class Pessoa {
	private String nome;
	private String cpfCnpj;
	private String tipoConta;
	private int numeroConta;
	private double saldo;
	
	public Pessoa(String nome, String cpfCnpj, String tipoConta, int numeroConta, double saldo) {
		super();
		this.nome = nome;
		this.cpfCnpj = cpfCnpj;
		this.tipoConta = tipoConta;
		this.numeroConta = numeroConta;
		this.saldo = saldo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpfCnpj() {
		return cpfCnpj;
	}

	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	public String getTipoConta() {
		return tipoConta;
	}

	public void setTipoConta(String tipoConta) {
		this.tipoConta = tipoConta;
	}

	public int getNumeroConta() {
		return numeroConta;
	}

	public void setNumeroConta(int numeroConta) {
		this.numeroConta = numeroConta;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	@Override
	public String toString() {
		return "Pessoa [nome=" + nome + ", cpfCnpj=" + cpfCnpj + ", tipoConta=" + tipoConta + ", numeroConta="
				+ numeroConta + ", saldo=" + saldo + "]";
	}
	
	
	
}
