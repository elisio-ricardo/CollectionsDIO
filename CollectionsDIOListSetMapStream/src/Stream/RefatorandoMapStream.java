package Stream;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/*Dadas as seguintes informa��es  de id e contato, crie um dicion�rio e
ordene este dicion�rio exibindo (Nome id - Nome contato);
id = 1 - Contato = nome: Simba, numero: 2222;
id = 4 - Contato = nome: Cami, numero: 5555;
id = 3 - Contato = nome: Jon, numero: 1111;
*/

public class RefatorandoMapStream {

	public static void main(String[] args) {

		System.out.println("--\tOrdem aleat�ria\t--");
		Map<Integer, Contato> agenda = new HashMap<>() {
			{
				put(1, new Contato("Simba", 5555));
				put(4, new Contato("Cami", 1111));
				put(3, new Contato("Jon", 2222));
			}
		};
		System.out.println(agenda);
		for (Map.Entry<Integer, Contato> entry : agenda.entrySet()) {
			System.out.println(entry.getKey() + " - " + entry.getValue().getNome());
		}

		// Refatorado para Stream
		System.out.println("--\tOrdem aleat�ria\t--");
		Map<Integer, Contato> agenda5 = new HashMap<>() {
			{
				put(1, new Contato("Simba", 5555));
				put(4, new Contato("Cami", 1111));
				put(3, new Contato("Jon", 2222));
			}
		};
		System.out.println(agenda5);
		for (Map.Entry<Integer, Contato> entry : agenda5.entrySet()) {
			System.out.println(entry.getKey() + " - " + entry.getValue().getNome());
		}

		System.out.println("--\tOrdem Inser��o\t--");
		Map<Integer, Contato> agenda1 = new LinkedHashMap<>() {
			{
				put(1, new Contato("Simba", 5555));
				put(4, new Contato("Cami", 1111));
				put(3, new Contato("Jon", 2222));
			}
		};
		System.out.println(agenda1);
		for (Map.Entry<Integer, Contato> entry : agenda1.entrySet()) {
			System.out.println(entry.getKey() + " - " + entry.getValue().getNome());
		}

		System.out.println("--\tOrdem id\t--");
		Map<Integer, Contato> agenda2 = new TreeMap<>(agenda);
		System.out.println(agenda2);
		for (Map.Entry<Integer, Contato> entry : agenda2.entrySet()) {
			System.out.println(entry.getKey() + " - " + entry.getValue().getNome());
		}

		System.out.println("--\tOrdem n�mero telefone\t--");

		// Antes usando a classe construida abaixo no codigo
		System.out.println("Antes");
		Set<Map.Entry<Integer, Contato>> set5 = new TreeSet<>(new ComparatorOrdemNumerica());
		set5.addAll(agenda.entrySet());
		for (Map.Entry<Integer, Contato> entry : set5) {
			System.out.println( entry.getKey() + " - " + entry.getValue().getNumero() + ": "
					+ entry.getValue().getNome());
		}

		// precisamos organizar os valores. Logo:
		// Depois usando classe anonima.
		System.out.println("Depois");
		Set<Map.Entry<Integer, Contato>> set9 = new TreeSet<>(new Comparator<Map.Entry<Integer, Contato>>() {
			@Override
			public int compare(Map.Entry<Integer, Contato> cont1, Map.Entry<Integer, Contato> cont2) {
				return Integer.compare(cont1.getValue().getNumero(), cont2.getValue().getNumero());
			}
		});

		/*
		 * Set<Map.Entry<Integer, Contato>> set = new TreeSet<>(Comparator.comparing(
		 * new Function<Map.Entry<Integer, Contato>, Integer>() {
		 * 
		 * @Override public Integer apply(Map.Entry<Integer, Contato> cont) { return
		 * cont.getValue().getNumero(); } }));
		 */
		
		
		//SIMPLIFICANDO PARA O LAMBDA /////////////////////////////////////////////////////////

		Set<Map.Entry<Integer, Contato>> set7 = new TreeSet<>(
				Comparator.comparing(cont -> cont.getValue().getNumero()));
		
		
		set7.addAll(agenda.entrySet());
		for (Map.Entry<Integer, Contato> entry : set7) {
			System.out.println( entry.getKey() + " - " + entry.getValue().getNumero() + ": "
					+ entry.getValue().getNome());
		}
		
		

		System.out.println("--\tOrdem nome contato\t--");
		// precisamos organizar os valores. Logo:
		Set<Map.Entry<Integer, Contato>> set1 = new TreeSet<>(Comparator.comparing(cont -> cont.getValue().getNome()));
		set1.addAll(agenda.entrySet());
		// imprimindo usando forEach
		set1.forEach(entry -> System.out.println(entry.getKey() + " - " + entry.getValue().getNome()));

	}

}

class ComparatorOrdemNumerica implements Comparator<Map.Entry<Integer, Contato>> {
	@Override
	public int compare(Map.Entry<Integer, Contato> cont1, Map.Entry<Integer, Contato> cont2) {
		return Integer.compare(cont1.getValue().getNumero(), cont2.getValue().getNumero());
	}
}

class ComparatorOrdemNomeContato implements Comparator<Map.Entry<Integer, Contato>> {
	@Override
	public int compare(Map.Entry<Integer, Contato> cont1, Map.Entry<Integer, Contato> cont2) {
		return cont1.getValue().getNome().compareToIgnoreCase(cont2.getValue().getNome());
	}
}