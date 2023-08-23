package coumba0.flashcash.repository;

import coumba0.flashcash.model.Article;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends CrudRepository<Article, Long> {
    @Query("SELECT a FROM Article a WHERE a.id= :id")
    public Article findArticleById(Integer id);

}
