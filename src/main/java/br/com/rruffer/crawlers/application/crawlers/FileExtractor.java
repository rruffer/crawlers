package br.com.rruffer.crawlers.application.crawlers;

import java.io.File;

public interface FileExtractor extends Extractor {

    ExtractedData extract(File file);

}
