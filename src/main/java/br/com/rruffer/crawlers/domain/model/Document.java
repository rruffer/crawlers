package br.com.rruffer.crawlers.domain.model;

import br.com.rruffer.crawlers.application.crawlers.ExtractedData;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Document implements Serializable {

    private String id;
    private Date data;
    private String link;
    private String fonte;
    private String autor;
    private String titulo;
    private String texto;

    public Document(ExtractedData extractedData) {
//        this.id = "";
        this.data = new Date();
        this.link = "";
        this.fonte = "";
        this.autor = "";
        this.titulo = "";
        this.texto = "";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getFonte() {
        return fonte;
    }

    public void setFonte(String fonte) {
        this.fonte = fonte;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 37 * hash + Objects.hashCode(this.id);
        hash = 37 * hash + Objects.hashCode(this.data);
        hash = 37 * hash + Objects.hashCode(this.link);
        hash = 37 * hash + Objects.hashCode(this.fonte);
        hash = 37 * hash + Objects.hashCode(this.autor);
        hash = 37 * hash + Objects.hashCode(this.titulo);
        hash = 37 * hash + Objects.hashCode(this.texto);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Document other = (Document) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        if (!Objects.equals(this.link, other.link)) {
            return false;
        }
        if (!Objects.equals(this.fonte, other.fonte)) {
            return false;
        }
        if (!Objects.equals(this.autor, other.autor)) {
            return false;
        }
        if (!Objects.equals(this.titulo, other.titulo)) {
            return false;
        }
        if (!Objects.equals(this.texto, other.texto)) {
            return false;
        }
        return true;
    }

}
