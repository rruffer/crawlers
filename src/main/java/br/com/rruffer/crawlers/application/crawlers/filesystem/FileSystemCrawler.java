package br.com.rruffer.crawlers.application.crawlers.filesystem;

import br.com.rruffer.crawlers.application.crawlers.Crawlable;
import br.com.rruffer.crawlers.application.crawlers.FileExtractor;
import br.com.rruffer.crawlers.domain.repository.DocumentRepository;
import org.apache.commons.lang.Validate;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static br.com.rruffer.crawlers.application.crawlers.filesystem.extractors.FileExtractorInstances.*;

public class FileSystemCrawler implements Crawlable {

    private static final Logger LOGGER = Logger.getLogger(FileSystemCrawler.class.getName());
    private static final Map<String, FileExtractor> DEFAULT_FILE_EXTRACTORS = new HashMap<>();

    private final DocumentRepository documentRepository;
    private final Map<String, FileExtractor> fileExtractors;

    static {
        DEFAULT_FILE_EXTRACTORS.put(".txt", getTxtExtractorInstance());
        DEFAULT_FILE_EXTRACTORS.put(".pdf", getPDFExtractorInstance());
        DEFAULT_FILE_EXTRACTORS.put(".doc", getDocFileExtractorInstance());
        DEFAULT_FILE_EXTRACTORS.put(".xls", getXLSFileExtractorInstance());
        DEFAULT_FILE_EXTRACTORS.put(".ppt", getPPTFileExtractorInstance());
    }

    public FileSystemCrawler(DocumentRepository documentRepository) {
        this(documentRepository, DEFAULT_FILE_EXTRACTORS);
    }

    public FileSystemCrawler(DocumentRepository documentRepository, Map<String, FileExtractor> fileExtractors) {
        Validate.notNull(documentRepository, "Não é possível criar um FileSystemCrawler sem um DocumentRepository.");
        Validate.notEmpty(fileExtractors, "Não é possível criar um FileSystemCrailer sem uma coleção de extrator de arquivos.");
        this.documentRepository = documentRepository;
        this.fileExtractors = fileExtractors;
    }

    @Override
    public void crawl(Map<String, Object> config) {
        final Object initialPath = config.get(Crawlable.INITIAL_PATH_PARAM);
        final Object searchDepth = config.get(Crawlable.SEARCH_DEPTH_PARAM);

        Validate.notNull(initialPath, "Falta o parâmetro necessário: initialPath = " + initialPath);
        Validate.notNull(searchDepth, "Falta o parâmetro necessário: searchDepth = " + searchDepth);

        File file = new File((String) initialPath);
        explore(file, (int) searchDepth);
    }

    private void explore(File file, int depth) {
        if (depth >= 0) {
            if (file.isDirectory()) {
                File[] files = file.listFiles();
                if (files != null)
                    for (File subFile : files)
                        explore(subFile, depth - 1);
            } else {
                extract(file);
            }
        }
    }

    private void extract(File file) {
        final String name = file.getName();
        final String fileExtension = name.substring(name.lastIndexOf("."), name.length());
        if (!file.isDirectory() && fileExtractors.containsKey(fileExtension)) {
            LOGGER.log(Level.INFO, "Abrindo o arquivo: {0}", name);
            FileExtractor fileExtractor = fileExtractors.get(fileExtension);
            documentRepository.insert(fileExtractor.extract(file));
        }
    }

}