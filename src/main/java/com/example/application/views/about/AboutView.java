package com.example.application.views.about;

import com.example.application.views.MainLayout;
import com.vaadin.flow.component.html.Anchor;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.StreamRegistration;
import com.vaadin.flow.server.StreamResource;
import com.vaadin.flow.server.VaadinSession;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

import org.apache.poi.xwpf.usermodel.XWPFDocument;   // Wird aus Navigationsgründen benötigt.

@PageTitle("About")
@Route(value = "about", layout = MainLayout.class)
public class AboutView extends VerticalLayout {

    public AboutView() {
        setSpacing(false);

        Image img = new Image("images/empty-plant.png", "placeholder plant");
        img.setWidth("200px");
        add(img);

        add(new H2("This place intentionally left empty"));
        add(new Paragraph("It’s a place where you can grow your own UI 🤗"));

        add(getDownloadAnchor());

        setSizeFull();
        setJustifyContentMode(JustifyContentMode.CENTER);
        setDefaultHorizontalComponentAlignment(Alignment.CENTER);
        getStyle().set("text-align", "center");
    }

    private Anchor getDownloadAnchor(){

        //final StreamResource resource = new StreamResource("kurzertext.txt",
        //        () -> new ByteArrayInputStream("Kurzer Text".getBytes(StandardCharsets.UTF_8)));
        ByteArrayOutputStream docStream = POIProbe.getDocx();
        final StreamResource resource = new StreamResource("simple.docx",
                () -> new ByteArrayInputStream(docStream.toByteArray()) );
        final StreamRegistration registration = VaadinSession.getCurrent().getResourceRegistry().registerResource(resource);
        Anchor anchor = new Anchor(registration.getResource(),"Click");

        return anchor;
    }



}
