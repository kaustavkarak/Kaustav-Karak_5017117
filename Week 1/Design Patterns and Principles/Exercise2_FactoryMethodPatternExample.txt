// Exercise 2: Implementing the FACTORY METHOD PATTERN

import java.util.HashMap;
import java.util.Map;

// Document Interface
interface Document {
    void open();
}

// Concrete Document Classes
class WordDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening a Word document.");
    }
}

class PdfDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening a PDF document.");
    }
}

class ExcelDocument implements Document {
    @Override
    public void open() {
        System.out.println("Opening an Excel document.");
    }
}

abstract class DocumentFactory {
    // createDocument() Method
    public abstract Document createDocument();
}

// Concrete Factory Classes extends DocumentFactory for Word Document
class WordDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new WordDocument();
    }
}

// Concrete Factory Classes extends DocumentFactory for PDF Document
class PdfDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new PdfDocument();
    }
}

// Concrete Factory Classes extends DocumentFactory for Excel Document
class ExcelDocumentFactory extends DocumentFactory {
    @Override
    public Document createDocument() {
        return new ExcelDocument();
    }
}

// Test Class to to demonstrate the Factory Pattern Method
public class Exercise2_FactoryMethodPatternExample {
    public static void main(String[] args) {
        Map<String, DocumentFactory> factoryMap = new HashMap<>();
        factoryMap.put("word", new WordDocumentFactory());
        factoryMap.put("pdf", new PdfDocumentFactory());
        factoryMap.put("excel", new ExcelDocumentFactory());

        //Word Document
        DocumentFactory wordFactory = factoryMap.get("word");
        Document wordDoc = wordFactory.createDocument();
        wordDoc.open(); 

        //PDF Document
        DocumentFactory pdfFactory = factoryMap.get("pdf");
        Document pdfDoc = pdfFactory.createDocument();
        pdfDoc.open(); 

        //Excel Document
        DocumentFactory excelFactory = factoryMap.get("excel");
        Document excelDoc = excelFactory.createDocument();
        excelDoc.open(); 
    }
}



// OUTPUT:
// Opening a Word document.
// Opening a PDF document.
// Opening an Excel document.