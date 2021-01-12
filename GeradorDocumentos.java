package commons.sarh;

import java.util.ArrayList;

public class GeradorDocumentos {

	private ArrayList<Integer> listaAleatoriaCpf = new ArrayList<Integer>();
	private ArrayList<Integer> listaAleatoriaPis = new ArrayList<Integer>();
	private ArrayList<Integer> listaNumMultiplicados = null;

	// Metodo para geracao de um numero aleatorio entre 0 e 9
	public int geraNumAleatorio() {
		int numero = (int) (Math.random() * 10);

		return numero;
	}

	// Metodo para geracao dos 9 primeiros digitos do CPF
	public ArrayList<Integer> geraCPFParcial() {
		for (int i = 0; i < 9; i++) {
			listaAleatoriaCpf.add(geraNumAleatorio());
		}

		return listaAleatoriaCpf;
	}

	// Metodo para geracao do primeiro digito verificador
	public ArrayList<Integer> geraPrimeiroDigito() {
		listaNumMultiplicados = new ArrayList<Integer>();
		int primeiroDigito;
		int totalSomatoria = 0;
		int restoDivisao;
		int peso = 10;

		for (int item : listaAleatoriaCpf) {
			listaNumMultiplicados.add(item * peso);
			peso--;
		}

		for (int item : listaNumMultiplicados) {
			totalSomatoria += item;
		}

		restoDivisao = (totalSomatoria % 11);

		if (restoDivisao < 2) {
			primeiroDigito = 0;
		} else {
			primeiroDigito = 11 - restoDivisao;
		}

		listaAleatoriaCpf.add(primeiroDigito);

		return listaAleatoriaCpf;
	}

	// Metodo para geracao do segundo digito verificador
	public ArrayList<Integer> geraSegundoDigito() {
		listaNumMultiplicados = new ArrayList<Integer>();
		int segundoDigito;
		int totalSomatoria = 0;
		int restoDivisao;
		int peso = 11;

		for (int item : listaAleatoriaCpf) {
			listaNumMultiplicados.add(item * peso);
			peso--;
		}

		for (int item : listaNumMultiplicados) {
			totalSomatoria += item;
		}

		restoDivisao = (totalSomatoria % 11);

		if (restoDivisao < 2) {
			segundoDigito = 0;
		} else {
			segundoDigito = 11 - restoDivisao;
		}

		listaAleatoriaCpf.add(segundoDigito);

		return listaAleatoriaCpf;
	}

	// Gerador final cpf valido convertendo em uma string de 11 numeros
	public String geraCPFFinal() {
		geraCPFParcial();
		geraPrimeiroDigito();
		geraSegundoDigito();

		String cpf = "";

		for (int item : listaAleatoriaCpf) {
			cpf += item;
		}

		return cpf;
	}

	// Metodo para geracao dos 10 primeiros digitos do Pis
	public ArrayList<Integer> geraPisParcial() {
		for (int i = 0; i < 10; i++) {
			listaAleatoriaPis.add(geraNumAleatorio());
		}

		return listaAleatoriaPis;
	}

	// Metodo para geracao do digito verificador do Pis
	public ArrayList<Integer> geraDigitoVerificacao() {
		listaNumMultiplicados = new ArrayList<Integer>();
		int soma = 0;

		int numero1 = listaAleatoriaPis.get(0) * 3;
		listaNumMultiplicados.add(numero1);
		int numero2 = listaAleatoriaPis.get(1) * 2;
		listaNumMultiplicados.add(numero2);
		int numero3 = listaAleatoriaPis.get(2) * 9;
		listaNumMultiplicados.add(numero3);
		int numero4 = listaAleatoriaPis.get(3) * 8;
		listaNumMultiplicados.add(numero4);
		int numero5 = listaAleatoriaPis.get(4) * 7;
		listaNumMultiplicados.add(numero5);
		int numero6 = listaAleatoriaPis.get(5) * 6;
		listaNumMultiplicados.add(numero6);
		int numero7 = listaAleatoriaPis.get(6) * 5;
		listaNumMultiplicados.add(numero7);
		int numero8 = listaAleatoriaPis.get(7) * 4;
		listaNumMultiplicados.add(numero8);
		int numero9 = listaAleatoriaPis.get(8) * 3;
		listaNumMultiplicados.add(numero9);
		int numero10 = listaAleatoriaPis.get(9) * 2;
		listaNumMultiplicados.add(numero10);

		for (int item : listaNumMultiplicados) {
			soma += item;
		}

		int restoDivisao = (soma % 11);

		int resultado = 11 - restoDivisao;

		if (resultado == 10 || resultado == 11) {

			resultado = 0;
		}

		listaAleatoriaPis.add(resultado);

		return listaAleatoriaPis;
	}

	// Gerador final Pis valido convertendo em uma string de 11 numeros
	public String geraPisFinal() {
		geraPisParcial();
		geraDigitoVerificacao();

		String pis = "";
		for (int item : listaAleatoriaPis) {
			pis += item;
		}

		return pis;
	}
	
	// Validador PisPasep
	public boolean isPisPasepValido (String pisPasep) {
		
		String formula = "3298765432";
		int total = 0;
		int multiplicando = 0;
		int multiplicador = 0;
		boolean retorno = false;
		if(pisPasep != null) {
			int tamanho = pisPasep.length();
			if (tamanho != 11) {
				retorno = false;
			}
			else {
				retorno = true;
				for (int i=0; i<10; i++) {
	
					multiplicando = Integer.parseInt(pisPasep.substring(i, i+1));
					multiplicador = Integer.parseInt(formula.substring(i, i+1));
					total += multiplicando * multiplicador;
				}
				
				int resto = 11 - total % 11;
				
				if(resto == 10 || resto == 11) {
					resto = 0;
				}
	
				int digito = Integer.parseInt("" + pisPasep.charAt(10));
				
				if(digito != resto) {
					retorno = false;
				}
			}
		}	
		return retorno;
	}

}
