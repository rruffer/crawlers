package br.com.rruffer.crawlers.domain.repository;

import br.com.rruffer.crawlers.domain.repository.shared.Repository;

public enum RepositoryInstances {

    DOCUMENT_REPOSITORY(new DocumentRepositoryImpl());

    private final Repository repository;

    RepositoryInstances(Repository repository) {
        this.repository = repository;
    }

    public static final DocumentRepository getDocumentRepositoryInstance() {
        return (DocumentRepository) DOCUMENT_REPOSITORY.repository;
    }
}
