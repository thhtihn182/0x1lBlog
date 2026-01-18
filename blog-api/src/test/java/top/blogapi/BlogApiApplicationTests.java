package top.blogapi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import top.blogapi.mapper.BlogMapper;
import top.blogapi.model.entity.Blog;
import top.blogapi.model.entity.Comment;
import top.blogapi.model.vo.ArchiveBlog;
import top.blogapi.model.vo.BlogInfo;
import top.blogapi.repository.BlogRepository;
import top.blogapi.repository.CategoryRepository;
import top.blogapi.repository.CommentRepository;
import top.blogapi.service.impl.orchestration.SiteSettingOrchestrator;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;

@SpringBootTest
class BlogApiApplicationTests {

	@Autowired
	private DataSource dataSource;
	@Autowired
	private BlogMapper blogMapper;

	@Test
	void testConnection() throws SQLException {
		try (Connection conn = dataSource.getConnection()) {
			System.out.println("✅ Connected to DB: " + conn.getMetaData().getURL());
		}
	}
	@Autowired
	BlogRepository blogRepository;
	@Autowired
	CommentRepository commentRepository;
	@Autowired
	CategoryRepository categoryRepository;
	@Autowired
	SiteSettingOrchestrator siteSettingOrchestrator;



	@Test
	void test11(){

	}

	@Test
	void test10(){
		List<String > yearMonths = blogRepository.getGroupYearMonth();
		List<ArchiveBlog> archiveBlogs = blogRepository.getArchiveBlogListByYearMonth(yearMonths);
		Map<String, List<ArchiveBlog>> map = new LinkedHashMap<>();
		for (int i = archiveBlogs.size()-1; i >= 0 ; i--) {
			String key = archiveBlogs.get(i).getYM();
			map.computeIfAbsent(key,k -> new ArrayList<>()).add(archiveBlogs.get(i));
		}

		for(Map.Entry<String, List<ArchiveBlog>> entry : map.entrySet()){
			System.out.println(entry.getKey()+ " | " + entry.getValue());
		}
		for (ArchiveBlog a : archiveBlogs)
			System.out.println(blogMapper.toArchiveBlogResponse(a));
	}

	@Test
	void test1 (){
		Optional<Blog> blog = blogRepository.getBlogById(35L);
        blog.ifPresent(blog1 -> {
			blog1.setTags(blogRepository.findTagsByBlogId(35L));
		});
		System.out.println(blog);

	}

	@Test
	void test9(){
		System.out.println(commentRepository.commentTreeFlat().size());
	}
	@Test
	void test8(){
		System.out.println(blogRepository.getIdAndTitleListByIsPublishedAndIsRecommend());
	}

	@Test
	void test7(){
		List<BlogInfo> list = blogRepository.getBlogInfoListByIsPublished();
		System.out.println(list);
		System.out.println(list.getLast());
	}

	@Test
	void test5(){
		List<Comment> commentList = commentRepository.getListByPageAndParentCommentId(0,null,2L);
		for (Comment comment : commentList) {
			comment.setReplyComments(commentRepository.getListByPageAndParentCommentId(0,comment.getId(),2L));
		}
		System.out.println(commentList);
	}
	@Test
	void test6(){
		System.out.println(blogRepository.getIdAndTitleList().size());
	}

	@Test
	void test2(){
		System.out.println(blogRepository.findTagsByBlogId(35L));
	}


	@Test
	void test3(){
//		Optional<Blog> blog = blogRepository.getBlogById(35L);
//		Category category = categoryRepository.getCategoryById(1L);
//		blog.ifPresent(blog1 -> {blog1.setTitle("aaaa");
//			blog1.setCategory(category);
//			blogRepository.updateBlog(blog1);
//
//		});
//		System.out.println(blog);
	}
//	@Test
//	void test() {
//		List<Blog> blogs = blogMapper.getBlogList();
//		System.out.println(blogs);
//	}
}
