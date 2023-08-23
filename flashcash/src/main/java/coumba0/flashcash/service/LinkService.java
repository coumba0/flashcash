package coumba0.flashcash.service;

import coumba0.flashcash.model.Link;
import coumba0.flashcash.model.User;
import coumba0.flashcash.repository.LinkRepository;
import coumba0.flashcash.repository.UserRepository;
import coumba0.flashcash.service.flashcash.AddContactForm;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LinkService {
    private final LinkRepository linkRepository;
    private final SessionService sessionService;

    private final UserRepository userRepository;


    public LinkService(LinkRepository linkRepository, SessionService sessionService, UserRepository userRepository) {
        this.linkRepository = linkRepository;
        this.sessionService = sessionService;
        this.userRepository = userRepository;
    }

    public void addContact(AddContactForm form) {
        Link link = new Link();
        link.setUser1(sessionService.sessionUser());
        link.setUser2(userRepository.findUserByMail(form.getMail()).get());

        linkRepository.save(link);
    }
    public List<String> findLinksEmail() {
        return linkRepository.findLinksByUser1Email(sessionService.sessionUser().getMail()).stream().map(Link::getUser2).map(User::getMail).collect(Collectors.toList());
    }
}