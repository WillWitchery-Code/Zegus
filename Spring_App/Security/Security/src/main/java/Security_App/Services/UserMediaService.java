package Security_App.Services;

import com.mongodb.client.gridfs.model.GridFSFile;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import Security_App.Models.User_Media;
import Security_App.Repositories.User_MediaRepository;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

@Service
public class UserMediaService {

    @Autowired
    private GridFsTemplate gridFsTemplate;

    @Autowired
    private GridFsOperations operations;

    @Autowired
    private User_MediaRepository userMediaRepository;

    public User_Media saveProfilePicture(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        ObjectId gridFsFileId = gridFsTemplate.store(inputStream, file.getOriginalFilename(), file.getContentType());


        User_Media userMedia = new User_Media();
        userMedia.setProfilePictureId(gridFsFileId.toString());
        userMedia.setProfilePictureName(file.getOriginalFilename());
        userMedia.setProfilePictureContentType(file.getContentType());
        userMedia.setCoverPictureId(gridFsFileId.toString());
        userMedia.setCoverPictureName(file.getOriginalFilename());
        userMedia.setCoverPictureContentType(file.getContentType());

        return userMediaRepository.save(userMedia);
    }

    public User_Media updateProfilePicture(String userId, MultipartFile file) throws IOException {
        if (userId == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }

        // Store the new profile picture in GridFS
        InputStream inputStream = file.getInputStream();
        ObjectId gridFsFileId = gridFsTemplate.store(inputStream, file.getOriginalFilename(), file.getContentType());

        // Retrieve the existing User_Media document
        Optional<User_Media> optionalUserMedia = userMediaRepository.findById(userId);
        if (!optionalUserMedia.isPresent()) {
            throw new IllegalArgumentException("User_Media with ID " + userId + " not found");
        }

        User_Media userMedia = optionalUserMedia.get();

        if (userMedia.getProfilePictureId() != null) {
            gridFsTemplate.delete(new Query(Criteria.where("_id").is(userMedia.getProfilePictureId())));
        }

        userMedia.setProfilePictureId(gridFsFileId.toString());
        userMedia.setProfilePictureName(file.getOriginalFilename());
        userMedia.setProfilePictureContentType(file.getContentType());

        return userMediaRepository.save(userMedia);
    }

    public User_Media updateCoverPicture(String userId, MultipartFile file) throws IOException {
        if (userId == null) {
            throw new IllegalArgumentException("User ID must not be null");
        }

        // Store the new profile picture in GridFS
        InputStream inputStream = file.getInputStream();
        ObjectId gridFsFileId = gridFsTemplate.store(inputStream, file.getOriginalFilename(), file.getContentType());

        // Retrieve the existing User_Media document
        Optional<User_Media> optionalUserMedia = userMediaRepository.findById(userId);
        if (!optionalUserMedia.isPresent()) {
            throw new IllegalArgumentException("User_Media with ID " + userId + " not found");
        }

        User_Media userMedia = optionalUserMedia.get();

        // Delete the existing profile picture if present
        if (userMedia.getProfilePictureId() != null) {
            gridFsTemplate.delete(new Query(Criteria.where("_id").is(userMedia.getProfilePictureId())));
        }

        // Update the profile picture fields
        userMedia.setCoverPictureId(gridFsFileId.toString());
        userMedia.setCoverPictureName(file.getOriginalFilename());
        userMedia.setCoverPictureContentType(file.getContentType());

        // Save the updated User_Media document
        return userMediaRepository.save(userMedia);
    }






    public GridFSFile getProfilePicture(String profilePictureId) {
        return gridFsTemplate.findOne(new Query(Criteria.where("_id").is(profilePictureId)));
    }

    public void deleteProfilePicture(String profilePictureId) {
        gridFsTemplate.delete(new Query(Criteria.where("_id").is(profilePictureId)));
    }

    public void deleteCoverPicture(String coverPictureId) {
        gridFsTemplate.delete(new Query(Criteria.where("_id").is(coverPictureId)));
    }

    public GridFSFile getCoverPicture(String coverPictureId) {
        return gridFsTemplate.findOne(new Query(Criteria.where("_id").is(coverPictureId)));
    }
}
