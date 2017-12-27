package br.com.rruffer.crawlers.domain.valueobject;

import br.com.rruffer.crawlers.application.crawlers.ExtractedData;

import java.util.Date;

public class FileData extends ExtractedData {

    protected FileData(String text, Date date, String author, String title) {
        super(text, date, author, title);
    }

    public static FileData create(String text, Date date, String author, String title) {
        return new FileData(
                text,
                date,
                author,
                title
        );
    }

}
