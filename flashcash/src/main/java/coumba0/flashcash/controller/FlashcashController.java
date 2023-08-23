package coumba0.flashcash.controller;

import coumba0.flashcash.model.Transfer;
import coumba0.flashcash.model.User;
import coumba0.flashcash.service.LinkService;
import coumba0.flashcash.service.SessionService;
import coumba0.flashcash.service.UserService;

import coumba0.flashcash.service.flashcash.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class FlashcashController {
    private final SessionService sessionService;
    private final UserService userService;
    private final FlashcashService flashcashService;
    private final LinkService linkService;


    public FlashcashController(SessionService sessionService, UserService userService, FlashcashService flashcashService, LinkService linkService) {
        this.sessionService = sessionService;
        this.userService = userService;
        this.flashcashService = flashcashService;
        this.linkService = linkService;
    }

    @GetMapping("/flashcash")
    public String flashcash() {
        return "flashcash/flashcash";
    }

//----------------------------AJOUT IBAN----------------------------------------------------------
    @GetMapping("/add-iban")
    public ModelAndView showAddIbanForm() {
        return new ModelAndView("flashcash/add-iban", "addIban", new AddIbanForm());
    }
    @PostMapping("/add-iban")
    public ModelAndView addIban(Model model, @ModelAttribute("addIban") AddIbanForm form) {
        flashcashService.addIban(form);
        User user = sessionService.sessionUser();
        model.addAttribute("user", user);
        return new ModelAndView("profile");
    }

    //----------------------------TRANSFER A FLASHCASH----------------------------------------------------------
    @GetMapping("/transfer-to-flashcash")
    public ModelAndView getTransferToFlashcash(Model model) {
        User sessionUser = sessionService.sessionUser();
        return new ModelAndView("flashcash/transfer-to-flashcash", "transferToFlashcash", new TransferToFlashcashForm());
    }
    @PostMapping("/transfer-to-flashcash")
    public ModelAndView postTransferToFlashcash(Model model, @ModelAttribute("tranferToFlashcashForm") TransferToFlashcashForm form) {
        flashcashService.tranferToAccount(form);
        User sessionUser = sessionService.sessionUser();
        model.addAttribute("user", sessionUser);
        return new ModelAndView("profile");
    }
    //----------------------------TRANSFER A LA BANQUE----------------------------------------------------------
    @GetMapping("/transfer-to-bank")
    public ModelAndView getTransfertoBank(Model model) {
        User sessionUser = sessionService.sessionUser();
        return new ModelAndView("flashcash/transfer-to-bank", "transferToBank", new TransferToBankForm());
    }
    @PostMapping("/transfer-to-bank")
    public ModelAndView postTransferToBank(Model model, @ModelAttribute("tranferToBankForm") TransferToBankForm form) {
        flashcashService.transferToBank(form);
        User sessionUser = sessionService.sessionUser();
        model.addAttribute("user", sessionUser);
        return new ModelAndView("profile");
    }
    //----------------------------PAGE TRANSFER---------------------------------------------------------

    @GetMapping("/transfer")
    public ModelAndView transfer(Model model) {
        List<Transfer> transfers = flashcashService.findTransactions();
        model.addAttribute("transfers", transfers);
        return new ModelAndView("flashcash/transfer");

    }

    //----------------------------TRANSFER AUX CONTACTS---------------------------------------------------------

    @GetMapping("/transfer-to-contact")
    public ModelAndView getTransferToContact(Model model) {
        List<String> linksEmail = linkService.findLinksEmail();
        model.addAttribute("linksEmail", linksEmail);
        return new ModelAndView( "flashcash/transfer-to-contact", "transferForm", new TransferForm());
    }

    @PostMapping("transfer-to-contact")
    public ModelAndView transfer(Model model, @ModelAttribute("transferForm") TransferForm form) {
        flashcashService.transfer(form);
        List<Transfer> transfers = flashcashService.findTransactions();
        model.addAttribute("transfers", transfers);
        return new ModelAndView("flashcash/transfer");
    }
}
