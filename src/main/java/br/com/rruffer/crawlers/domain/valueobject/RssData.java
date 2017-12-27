package br.com.rruffer.crawlers.domain.valueobject;

import br.com.rruffer.crawlers.application.crawlers.ExtractedData;

import java.util.Date;

public class RssData extends ExtractedData {

    private final String link;
    private final Date updatedDate;

    public RssData(String text, Date date, String author, String title,
                   String link, Date updatedDate) {
        super(text, date, author, title);
        this.link = link;
        this.updatedDate = updatedDate;
    }

}
