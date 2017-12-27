package br.com.rruffer.crawlers.application.crawlers.filesystem.extractors;

import br.com.rruffer.crawlers.application.crawlers.ExtractedData;
import br.com.rruffer.crawlers.application.crawlers.FileExtractor;
import br.com.rruffer.crawlers.domain.valueobject.FileData;
import org.apache.poi.POIOLE2TextExtractor;
import org.apache.poi.hpsf.SummaryInformation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

abstract class OLEFileExtractorTemplate implements FileExtractor {

    @Override
    public ExtractedData extract(File file) {
        try (FileInputStream fis = new FileInputStream(file)) {
            POIOLE2TextExtractor extractor = createExtractorFor(fis);

            SummaryInformation summaryInformation = extractor.getSummaryInformation();
            
            ////////
            System.out.println("Path: " + file.getAbsolutePath());
            System.out.println("Texto: " + extractor.getText());
            System.out.println("Data: " + summaryInformation.getCreateDateTime());
            System.out.println("Autor: " + summaryInformation.getAuthor());
            System.out.println("Título: " + summaryInformation.getTitle());
            ///////
            
            return FileData.create(extractor.getText(),
                    summaryInformation.getCreateDateTime(),
                    summaryInformation.getAuthor(),
                    summaryInformation.getTitle()
            );
        } catch (IOException ex) {
            Logger.getLogger(OLEFileExtractorTemplate.class.getName()).log(
                    Level.SEVERE, "Um erro ocorreu!",
                    ex
            );
            throw new RuntimeException(ex);
        }
    }

    protected abstract POIOLE2TextExtractor createExtractorFor(InputStream inputStream) throws IOException;
}
