package kr.codesqaud.cafe.post;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import kr.codesqaud.cafe.post.form.PostForm;

@Controller
public class PostController {

	private final PostService postService;

	public PostController(PostService postService) {
		this.postService = postService;
	}

	@GetMapping(value = {"/posts/new", "/posts/form"})
	public String showNewPage(@ModelAttribute PostForm postForm) {
		return "/post/formInvalid";
	}

	@PostMapping("/posts")
	public String addPost(@Valid PostForm postForm, Errors errors, Model model) {
		if (errors.hasErrors()) {
			return "/post/formInvalid";
		}
		Post post = postService.createNewPost(postForm);
		model.addAttribute(post);
		return "/post/postDetail";
	}

	@GetMapping("/posts/{postId}")
	public String showPostPage(Model model, @PathVariable Long postId) {
		Optional<Post> optionalPost = postService.findById(postId);
		if (optionalPost.isEmpty()) {
			return "redirect:/";
		}
		Post post = optionalPost.get();
		model.addAttribute(post);
		return "/post/postDetail";
	}
}
