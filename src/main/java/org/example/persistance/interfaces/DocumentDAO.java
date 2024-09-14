package org.example.persistance.interfaces;

import org.example.metier.abstracts.Documents;

import java.util.List;

public interface DocumentDAO <T> {
    void createDocument(T document);
    void updateDocument(T document);
    void deleteDocument(int documentId);
    Documents displayDocument(int documentId);
    List<T> displayAllDocuments();

}
