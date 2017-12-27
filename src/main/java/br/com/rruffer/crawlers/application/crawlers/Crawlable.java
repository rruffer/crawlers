package br.com.rruffer.crawlers.application.crawlers;

import java.util.Map;

public interface Crawlable {

    // Parâmetros utilizados pelos crawlers
	String URL_PARAM = "urlParam";

    String REGEX_LINKS_VALIDOS_PARAM = "regexLinksValidosParam";

    String SEARCH_DEPTH_PARAM = "profundidadeParam";

    String INITIAL_PATH_PARAM = "pathInicialParam";

    String TEXTO_BUSCA_PARAM = "textoBuscaParam";

    String USER_PARAM = "userParam"; 
    
    String PASSWORD_PARAM = "passwordParam";
    
    void crawl(Map<String, Object> config);

}
