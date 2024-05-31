package com.example.server.Requests;

public class ImageDTO {
    private String uri;

    public ImageDTO() {
    }

    public ImageDTO(String uri) {
        this.uri = uri;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
