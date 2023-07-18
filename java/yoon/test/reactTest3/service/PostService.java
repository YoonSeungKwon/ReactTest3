package yoon.test.reactTest3.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yoon.test.reactTest3.domain.Members;
import yoon.test.reactTest3.domain.Posts;
import yoon.test.reactTest3.repository.MemberRepository;
import yoon.test.reactTest3.repository.PostRepository;
import yoon.test.reactTest3.vo.request.PostRequest;
import yoon.test.reactTest3.vo.response.PostDetailResponse;
import yoon.test.reactTest3.vo.response.PostResponse;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    private PostResponse toResponse(Posts post){
        return new PostResponse(post.getIdx(), post.getTitle(), post.getMember().getName(), post.getRegdate(), post.getHit());
    }

    private PostDetailResponse toDetailResponse(Posts post){
        return new PostDetailResponse(post.getIdx(), post.getTitle(), post.getContent(), post.getMember().getName(), post.getRegdate(), post.getHit());
    }

    public List<PostResponse> loadPosts(){
        List<Posts> tempList = postRepository.findAll();
        List<PostResponse> list = new ArrayList<>();
        for(Posts post: tempList){
            list.add(toResponse(post));
        }
        return list;
    }

    @Transactional
    public PostResponse savePost(PostRequest postRequest){
        Members member = (Members) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        postRequest.setEmail(member.getEmail());
        Posts post = Posts.builder()
                .title(postRequest.getTitle())
                .content(postRequest.getContent())
                .member(memberRepository.findMembersByEmail(postRequest.getEmail()))
                .build();

        return toResponse(postRepository.save(post));

    }

    public PostDetailResponse loadPostDetail(long idx){
        return toDetailResponse(postRepository.findPostsByIdx(idx));
    }
}
