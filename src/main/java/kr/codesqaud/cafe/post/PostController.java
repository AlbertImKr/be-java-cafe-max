package kr.codesqaud.cafe.post;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PostController {

	private final PostsRepository postsRepository;

	public PostController(PostsRepository postsRepository) {
		this.postsRepository = postsRepository;
	}

	@GetMapping("/post")
	public String showPostPage(Model model) {
		model.addAttribute(new PostForm());
		return "/post/form";
	}

	@PostMapping("/post")
	public String addPost(PostForm postForm) {
		long newId = Post.createNewId();
		Post post = new Post(newId);
		post.setNickname(postForm.getNickname());
		post.setTitle(postForm.getTitle());
		post.setTextContent(postForm.getTextContent());
		post.setCreatedDateTime(LocalDateTime.now());
		postsRepository.add(post);
		return "redirect:/";
	}
}
