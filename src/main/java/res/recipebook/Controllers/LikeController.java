package res.recipebook.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import res.recipebook.Models.Like;
import res.recipebook.Payload.Requests.LikeRequest;
import res.recipebook.Services.LikeService;

import java.util.List;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/api/likes")
public class LikeController {
    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping(value="/getAllUserLikes", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Like> getAllUserLikes(@RequestBody LikeRequest likeRequest) {
        return likeService.getUserLikes(likeRequest.getUser_id());
    }

    @PostMapping(value="/getAllRecipeLikes", produces = {MediaType.APPLICATION_JSON_VALUE})
    public List<Like> getAllRecipeLikes(@RequestBody LikeRequest likeRequest) {
        return likeService.getRecipeLikes(likeRequest.getRecipe_id());
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping(value="/addUserLike")
    public ResponseEntity<?> addUserLike(@RequestBody LikeRequest likeRequest) {
        likeService.addUserLike(likeRequest.getUser_id(), likeRequest.getRecipe_id());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PreAuthorize("hasRole('USER')")
    @DeleteMapping(value="/deleteUserLike")
    public ResponseEntity<?> deleteUserLike(@RequestBody LikeRequest likeRequest) {
        likeService.removeUserLike(likeRequest.getUser_id(), likeRequest.getRecipe_id());
        return new ResponseEntity<>(HttpStatus.OK);
    }
}