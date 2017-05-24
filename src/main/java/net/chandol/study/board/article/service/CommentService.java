package net.chandol.study.board.article.service;

import net.chandol.study.board.article.dto.CommentCreateRequest;
import net.chandol.study.board.article.model.Article;
import net.chandol.study.board.article.model.Comment;
import net.chandol.study.board.article.repository.ArticleRepository;
import net.chandol.study.board.article.repository.CommentRepository;
import net.chandol.study.board.user.User;
import net.chandol.study.board.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CommentService {
    @Autowired CommentRepository commentRepository;
    @Autowired ArticleRepository articleRepository;
    @Autowired UserService userService;

    @Transactional
    public Comment createComment(CommentCreateRequest request){
        Article article = articleRepository.getOne(request.getArticleId());
        User user = userService.getUser(request.getUserId());
        Comment comment = new Comment(article, user, request.getBody());

        return commentRepository.save(comment);
    }

    public Comment getComment(Long commentId){
        return commentRepository.getOne(commentId);
    }
}
