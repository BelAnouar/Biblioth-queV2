package org.example.persistance.interfaces;

import java.util.List;

public interface DocumentDAO <T> {
    void createDocument(T document);
    void updateDocument(T document);
    void deleteDocument(int documentId);
    T displayDocument(int documentId);
    List<T> displayAllDocuments();
    void  empruntDocument(int id);
    void annulerEmpruntDocument(int id);
}
