package coumba0.flashcash.controller;


import coumba0.flashcash.model.Article;
import coumba0.flashcash.model.User;
import coumba0.flashcash.repository.ArticleRepository;
import coumba0.flashcash.service.AddArticleForm;
import coumba0.flashcash.service.LinkService;
import coumba0.flashcash.service.SessionService;
import coumba0.flashcash.service.UserService;
import coumba0.flashcash.service.flashcash.AddContactForm;
import coumba0.flashcash.service.form.SignUpForm;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class UserController {

    private final UserService userService;
    private final SessionService sessionService;
    private final ArticleRepository articleRepository;
    private final LinkService linkService;
    public UserController(UserService userService, SessionService sessionService, ArticleRepository articleRepository, LinkService linkService) {
        this.userService = userService;
        this.sessionService = sessionService;


        this.articleRepository = articleRepository;
        this.linkService = linkService;
    }

    /*
    ------------------------------ACCUEIL-------------------------------------------
     */
    @GetMapping("/")
    public ModelAndView home(Model model) {
        User user = sessionService.sessionUser();
        model.addAttribute("user", user);
        return new ModelAndView("profile");
    }

    /*
    ------------------------------CONNEXION-------------------------------------------
     */
    @GetMapping("/signup")
    public ModelAndView showRegisterForm() {
        return new ModelAndView("signup", "signupform", new SignUpForm());
    }
    @PostMapping("/signup")
    public ModelAndView processRequest(@ModelAttribute("signupform") SignUpForm form, Model model) {
        userService.registration(form);
        return new ModelAndView("signin");
    }
    @GetMapping("/home")
    public String logOff(Model model) {
        return "home";
    }

    /*
    ------------------------------NAVBAR-------------------------------------------
     */
    @GetMapping("/profile")
    public ModelAndView profile(Model model) {
        User user = sessionService.sessionUser();
        model.addAttribute("user", user);
        return new ModelAndView("profile");
    }
    @GetMapping("/personal")
    public String personal() {
        return "personal";
    }

    @GetMapping("/politics")
    public String politics() {
        return "politics";
    }
    @GetMapping("/science")
    public String science() {
        return "science";
    }
    @GetMapping("/other")
    public String other() {
        return "other";
    }


    /*
    ------------------------------BLOG-------------------------------------------
     */
    @GetMapping("/blog")
    public ModelAndView blog(Model model) {

        User user = sessionService.sessionUser();
        model.addAttribute("user", user);

        Article article = articleRepository.findArticleById(1);
        model.addAttribute("article", article);

        Article article2 = articleRepository.findArticleById(2);
        model.addAttribute("article2", article2);

        return new ModelAndView("blog/blog");
    }
    @GetMapping("/create-article")
    public ModelAndView showArticleCreation(Model model) {
        User sessionUser = sessionService.sessionUser();
        return new ModelAndView("blog/create-article", "addarticle", new AddArticleForm());
    }

    @PostMapping("/create-article")
    public ModelAndView addArticle(Model model, @ModelAttribute("addarticle")AddArticleForm form) {
        userService.addArticle(form);
        return new ModelAndView("blog/create-article");
    }

     /*
    ------------------------------AJOUT CONTACT-------------------------------------------
     */
     @GetMapping("/add-contact")
     public ModelAndView showAddRecipientForm() {
         return new ModelAndView("flashcash/add-contact", "addContact", new AddContactForm());
     }
    @PostMapping("/add-contact")
    public ModelAndView addRecipient(Model model, @ModelAttribute("addContact") AddContactForm form) {
        linkService.addContact(form);
        User user = sessionService.sessionUser();
        model.addAttribute("user", user);
        return new ModelAndView("profile");
    }



}
