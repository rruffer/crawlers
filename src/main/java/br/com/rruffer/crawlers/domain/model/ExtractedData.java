package br.com.rruffer.crawlers.domain.model;

import java.io.Serializable;
import java.util.Date;

public interface ExtractedData extends Serializable {

    Date getDate();

    String getAuthor();

    String getTitle();

    String getText();

}
