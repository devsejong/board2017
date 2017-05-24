package net.chandol.study.board.article.service;

import net.chandol.study.board.article.dto.CommentCreateRequest;
import net.chandol.study.board.article.model.Comment;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class CommentServiceTest {
    @Autowired CommentService commentService;

    @Test
    public void addCommentTest(){
        // given
        CommentCreateRequest request = new CommentCreateRequest(1L, 1L, "test comment");

        // when
        Comment savedComment = commentService.createComment(request);

        // then
        Comment comment = commentService.getComment(savedComment.getId());
        System.out.println(comment);
    }
}
