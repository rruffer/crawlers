package br.com.rruffer.crawlers;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import br.com.rruffer.crawlers.application.crawlers.Crawlable;
import br.com.rruffer.crawlers.application.crawlers.CrawlableFactory;

public class Main {

	public static void main(String[] args) throws IOException {

		Scanner entrada = new Scanner(System.in);

		System.out.println("Escolha uma das opções abaixo para fazer o crawler:");
		System.out.println("1 - FileSystem");
		System.out.println("2 - WebPages");
		System.out.println("3 - Gmail");

		System.out.print("Escolha sua opção: ");
		int opcao = entrada.nextInt();

		Map<String, Object> map = new HashMap<>();

		switch (opcao) {
		case 1:
			System.out.print("Informe o caminho da pasta: ");
			String path = entrada.next();

			File pasta = new File(path);
			
//			File pasta = new File("documentos");

			if (pasta.exists()) {
				map.put(Crawlable.INITIAL_PATH_PARAM, new File("documentos"));
				map.put(Crawlable.INITIAL_PATH_PARAM, pasta.getAbsolutePath());
				map.put(Crawlable.SEARCH_DEPTH_PARAM, 2);
			} else {
				System.out.println("Pasta não  encontrada!");
				return;
			}
			break;
		case 2:

			System.out.print("Informe o link: ");
			String link = entrada.next();

			System.out.print("Informe a profundidade: ");
			int profundidade = entrada.nextInt();

			// map.put(Crawlable.URL_PARAM, "http://www.globo.com");
			map.put(Crawlable.URL_PARAM, "http://" + link);
			map.put(Crawlable.SEARCH_DEPTH_PARAM, profundidade);
			break;
		case 3:

			System.out.print("Informe o usuário: ");
			String usuario = entrada.next();

			System.out.print("Informe a senha: ");
			String senha = entrada.next();

			map.put(Crawlable.USER_PARAM, usuario);
			map.put(Crawlable.PASSWORD_PARAM, senha);
			break;
		default:
			System.out.println("Opção inválida!");
			break;
		}

		Crawlable crawlable = CrawlableFactory.createCrawlableFrom(opcao);
		crawlable.crawl(map);
	}

}
