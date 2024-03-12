package dev.ozz.service.pdf.components;

import com.itextpdf.commons.actions.IEvent;
import com.itextpdf.commons.actions.IEventHandler;

import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;

import com.itextpdf.kernel.pdf.xobject.PdfFormXObject;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;


public class ReceiptPDF implements IEventHandler  {
    protected Document document;
    protected PdfFormXObject placeholder;
    protected float side;
    protected float x;
    protected float y;
    protected float space;
    protected float descent;

    public ReceiptPDF(Document doc) {
        this.document = doc;
        side = 20;
        x = 300;
        y = 25;
        space = 4.5f;
        descent = 3;
        placeholder = new PdfFormXObject(new Rectangle(0, 0, side, side));
    }

    // @Override
    // public void handleEvent(Event event) {
    //     PdfDocumentEvent documentEvent = (PdfDocumentEvent) event;
    //     PdfDocument pdf = documentEvent.getDocument();
    //     PdfPage page = documentEvent.getPage();
    //     Rectangle pageSize = page.getPageSize();
    //     int pageNumber = pdf.getPageNumber(page);
    //     PdfCanvas pdfCanvas = new PdfCanvas(page.getLastContentStream(), page.getResources(), pdf);

    //     Paragraph p = new Paragraph("Page ").add(String.valueOf(pageNumber)).add(" of");
    //     Canvas canvas = new Canvas(documentEvent.getPage(), pageSize);

    //     PdfPage firstPage = pdf.getFirstPage();

    //     if (firstPage == documentEvent.getPage())
    //         return;

    //     // canvas
    //     //         .showTextAligned(p.setFontSize(Invoice.FONT_MEDIUM), x, y, TextAlignment.RIGHT)
    //     //         .close();

    //     pdfCanvas.addXObjectAt(placeholder, x + space, y - descent);
    //     pdfCanvas.release();
    // }

    public void writeTotal(PdfDocument pdf) {
        Canvas canvas = new Canvas(placeholder, pdf);
        // canvas.showTextAligned(String.valueOf(pdf.getNumberOfPages()), 0, descent, TextAlignment.LEFT);
        canvas.close();
    }

    @Override
    public void onEvent(IEvent arg0) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'onEvent'");
    }
    
}
