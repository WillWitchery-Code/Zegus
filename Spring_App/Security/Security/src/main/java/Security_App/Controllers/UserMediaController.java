package Security_App.Controllers;

import Security_App.Models.User_Media;
import Security_App.Services.UserMediaService;
import com.mongodb.client.gridfs.model.GridFSFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.data.mongodb.gridfs.GridFsOperations;

import java.io.IOException;
import Security_App.Repositories.User_MediaRepository;
@RestController
@RequestMapping("/user-media")
public class UserMediaController {

    @Autowired
    private UserMediaService userMediaService;

    @Autowired
    private GridFsOperations gridFsOperations;

    @Autowired
    private User_MediaRepository Repository_UM;

    @PostMapping("/{id}")
    public User_Media uploadProfilePicture(@RequestParam("file") MultipartFile file) throws IOException {
        return userMediaService.saveProfilePicture(file);
    }

    @PutMapping("/ProfilePicture/{id}")
    public User_Media updateProfilePicture(@PathVariable String id, User_Media userMedia, @RequestParam("file") MultipartFile file) throws IOException {
        User_Media newuserMedia = this.Repository_UM
                .findById(id)
                .orElse(null);

        if (userMedia != null) {
            userMediaService.deleteProfilePicture(userMedia.getProfilePictureId());
            return userMediaService.updateProfilePicture(id, file);

        } else {
            return null;
        }


    }

    @PutMapping("/CoverPicture/{id}")
    public User_Media updateCoverPicture(@PathVariable String id, User_Media userMedia, @RequestParam("file") MultipartFile file) throws IOException {
        User_Media newuserMedia = this.Repository_UM
                .findById(id)
                .orElse(null);

        if (userMedia != null) {
            userMediaService.deleteCoverPicture(userMedia.getProfilePictureId());
            return userMediaService.updateCoverPicture(id, file);

        } else {
            return null;
        }
    }






    @GetMapping("/ProfilePicture/{iid}")
    public ResponseEntity<InputStreamResource> getProfilePicture(@PathVariable String iid, User_Media userMedia) throws IOException {

        GridFSFile gridFsFile = userMediaService.getProfilePicture(iid);
        if (gridFsFile == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(gridFsFile.getMetadata().getString("_contentType")))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + gridFsFile.getFilename() + "\"")
                .body(new InputStreamResource(gridFsOperations.getResource(gridFsFile).getInputStream()));
    }

    @GetMapping("/CoverPicture/{_id}")
    public ResponseEntity<InputStreamResource> getCoverPicture(@PathVariable String _id, User_Media userMedia) throws IOException {
        GridFSFile gridFsFile = userMediaService.getCoverPicture(_id);
        if (gridFsFile == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(gridFsFile.getMetadata().getString("_contentType")))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + gridFsFile.getFilename() + "\"")
                .body(new InputStreamResource(gridFsOperations.getResource(gridFsFile).getInputStream()));
    }
}
