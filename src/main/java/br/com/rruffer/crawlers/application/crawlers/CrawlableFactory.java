package br.com.rruffer.crawlers.application.crawlers;

import static br.com.rruffer.crawlers.domain.repository.RepositoryInstances.getDocumentRepositoryInstance;

import br.com.rruffer.crawlers.application.crawlers.filesystem.FileSystemCrawler;
import br.com.rruffer.crawlers.application.crawlers.gmail.GmailCrawler;
import br.com.rruffer.crawlers.application.crawlers.web.WebPageCrawler;

public enum CrawlableFactory {

	FILE_SYSTEM(1, new FileSystemCrawler(getDocumentRepositoryInstance())),
    WEB_PAGES(2, new WebPageCrawler()),
    GMAIL(3, new GmailCrawler());

    private final int tipo;
    private final Crawlable crawlable;

    CrawlableFactory(int tipo, Crawlable crawlable) {
        this.tipo = tipo;
        this.crawlable = crawlable;
    }

    public static Crawlable createCrawlableFrom(int tipo) {
        return from(tipo).crawlable;
    }

    private static CrawlableFactory from(int tipo) {
        for (CrawlableFactory dce : CrawlableFactory.values()) {
            if (dce.tipo == tipo) {
                return dce;
            }
        }

        return null;
    }

}
