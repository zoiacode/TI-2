package exercicio01;

import java.util.*;

class SomarDoisNumeros {
	public static Scanner scanf = new Scanner(System.in);
	
	public static void main (String args[]) {
		int num1, num2, soma;
		
		System.out.println("Digite um número");
		num1 = scanf.nextInt();
		System.out.println("Digite um número");
		num2 = scanf.nextInt();
		
		soma = num1 + num2;
		
		System.out.println("Soma:" + soma);
	}
}
