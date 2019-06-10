package rest.api.forms;

import org.jboss.resteasy.annotations.providers.multipart.PartType;

import javax.ws.rs.FormParam;

public class FileUploadForm {

    public FileUploadForm() {
    }

    @FormParam("uploadedFile")
    @PartType("application/octet-stream")
    private byte[] data;

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

}