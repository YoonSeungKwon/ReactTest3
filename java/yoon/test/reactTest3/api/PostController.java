package yoon.test.reactTest3.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import yoon.test.reactTest3.exception.valid.PostValidationSequence;
import yoon.test.reactTest3.service.PostService;
import yoon.test.reactTest3.vo.request.PostRequest;
import yoon.test.reactTest3.vo.response.PostResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/posts")
public class PostController {

    private final PostService postService;
    @GetMapping("/")
    public ResponseEntity<?> getPosts(){
        List<PostResponse> result = postService.loadPosts();
        return ResponseEntity.ok(result);
    }

    @PostMapping("/link")
    public ResponseEntity<?> joinPost(@RequestBody @Validated(PostValidationSequence.class) PostRequest postRequest){
        PostResponse result = postService.savePost(postRequest);
        return ResponseEntity.ok(result);
    }

}
