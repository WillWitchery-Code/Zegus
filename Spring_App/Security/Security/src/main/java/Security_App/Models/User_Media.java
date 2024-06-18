package Security_App.Models;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "user_media")
public class User_Media {
    @Id
    private String _id;

    private String profilePictureId;
    private String profilePictureName;
    private String profilePictureContentType;

    private String coverPictureId;
    private String coverPictureName;
    private String coverPictureContentType;

    public User_Media() {
        this.profilePictureId = profilePictureId;
        this.profilePictureName = profilePictureName;
        this.profilePictureContentType = profilePictureContentType;
        this.coverPictureId = coverPictureId;
        this.coverPictureName = coverPictureName;
        this.coverPictureContentType = coverPictureContentType;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getProfilePictureId() {
        return profilePictureId;
    }

    public void setProfilePictureId(String profilePictureId) {
        this.profilePictureId = profilePictureId;
    }

    public String getProfilePictureName() {
        return profilePictureName;
    }

    public void setProfilePictureName(String profilePictureName) {
        this.profilePictureName = profilePictureName;
    }

    public String getProfilePictureContentType() {
        return profilePictureContentType;
    }

    public void setProfilePictureContentType(String profilePictureContentType) {
        this.profilePictureContentType = profilePictureContentType;
    }

    public String getCoverPictureId() {
        return coverPictureId;
    }

    public void setCoverPictureId(String coverPictureId) {
        this.coverPictureId = coverPictureId;
    }

    public String getCoverPictureName() {
        return coverPictureName;
    }

    public void setCoverPictureName(String coverPictureName) {
        this.coverPictureName = coverPictureName;
    }

    public String getCoverPictureContentType() {
        return coverPictureContentType;
    }

    public void setCoverPictureContentType(String coverPictureContentType) {
        this.coverPictureContentType = coverPictureContentType;
    }
}

