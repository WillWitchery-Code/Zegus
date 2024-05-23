package Security_App.Models;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Data
@Document()
//////////////////////////////////////////////////////////////////////////////
public class Permits {
    @Id
    private String _id;
    private String url;
    private String method;

    public Permits(String url, String method) {
        this.url = url;
        this.method = method;
    }

    public String get_id() {
        return _id;
    }
    public String getUrl() {
        return url;
    }
    public void setUrl(String url) {
        this.url = url;
    }
    public String getMethod() {
        return method;
    }
    public void setMethod(String method) {
        this.method = method;
    }
}
