package kr.codesqaud.cafe.post;

import kr.codesqaud.cafe.exception.ErrorCode;
import kr.codesqaud.cafe.post.exception.InvalidPostIdFailedException;
import kr.codesqaud.cafe.post.dto.PostForm;
import kr.codesqaud.cafe.post.dto.SimplePostForm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    private static final Logger logger = LoggerFactory.getLogger(PostService.class);
    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post createNewPost(PostForm postForm) {
        Post post = postForm.toPost();
        int saveId = postRepository.save(post);
        return postRepository.findById((long) saveId).get();
    }

    public List<SimplePostForm> mappingSimpleForm(List<Post> posts) {
        return posts.stream()
                .map(SimplePostForm::from)
                .collect(Collectors.toList());
    }

    public Post findById(Long postId) {

        Optional<Post> optionalPost = postRepository.findById(postId);
        if (optionalPost.isEmpty()) {
            logger.info(ErrorCode.INVALID_POST_ID_CODE.getMessage());
            throw new InvalidPostIdFailedException(ErrorCode.INVALID_POST_ID_CODE);
        }
        return optionalPost.get();
    }
}
