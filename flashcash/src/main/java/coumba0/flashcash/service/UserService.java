package coumba0.flashcash.service;

import coumba0.flashcash.model.Article;
import coumba0.flashcash.model.User;
import coumba0.flashcash.model.UserAccount;
import coumba0.flashcash.repository.ArticleRepository;
import coumba0.flashcash.repository.UserAccountRepository;
import coumba0.flashcash.repository.UserRepository;
import coumba0.flashcash.service.form.SignUpForm;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service("UserService")
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final SessionService sessionService;
    private final UserAccountRepository userAccountRepository;
    private final ArticleRepository articleRepository;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, SessionService sessionService, UserAccountRepository userAccountRepository, ArticleRepository articleRepository) {
        super();
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.sessionService = sessionService;
        this.userAccountRepository = userAccountRepository;
        this.articleRepository = articleRepository;
    }


    public User registration(SignUpForm form) {
        User user = new User();
        UserAccount account = new UserAccount();
        account.setAmount(0.0);
        user.setAccount(account);
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        user.setMail(form.getMail());
        user.setPassword(passwordEncoder.encode(form.getPassword()));

        return userRepository.save(user);
    }

    public void addArticle(final AddArticleForm form) {

        Article article = new Article();
        article.setTitle(form.getTitle());
        article.setText(form.getText());

        articleRepository.save(article);


    }

}


