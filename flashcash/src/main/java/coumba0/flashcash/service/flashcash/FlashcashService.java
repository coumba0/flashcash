package coumba0.flashcash.service.flashcash;

import coumba0.flashcash.model.Transfer;
import coumba0.flashcash.model.User;
import coumba0.flashcash.model.UserAccount;
import coumba0.flashcash.repository.TransferRepository;
import coumba0.flashcash.repository.UserAccountRepository;
import coumba0.flashcash.repository.UserRepository;
import coumba0.flashcash.service.SessionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class FlashcashService {
    private final UserAccountRepository userAccountRepository;
    private final SessionService sessionService;
    private final TransferRepository transferRepository;
    private final UserRepository userRepository;

    public FlashcashService(UserAccountRepository userAccountRepository, SessionService sessionService, TransferRepository transferRepository, UserRepository userRepository) {
        this.userAccountRepository = userAccountRepository;
        this.sessionService = sessionService;
        this.transferRepository = transferRepository;
        this.userRepository = userRepository;
    }

    public void addIban(final AddIbanForm form) {

        UserAccount account = sessionService.sessionUser().getAccount();
        account.setIban(form.getIban());

        userAccountRepository.save(account);
    }


    public void tranferToAccount(TransferToFlashcashForm form) {
        userAccountRepository.save(sessionService.sessionUser().getAccount().plus(form.getAmount()));
    }

    public void transferToBank(TransferToBankForm form) {

        if (form != null) {
            // get the account of the connected user
            userAccountRepository.save(sessionService.sessionUser().getAccount().minus(form.getAmount()));

        } else {

        }
    }

    public List<Transfer> findTransactions() {
        List<Transfer> transfers = transferRepository.findTransferByUserId(sessionService.sessionUser().getId());
        Collections.reverse(transfers);
        return transfers;
    }

    public void transfer(TransferForm form) {
        if (form != null) {
            User to = userRepository.findUserByMail(form.getContactEmail())
                    .orElseThrow(() -> new RuntimeException("user with email not found"));
            Transfer transfer = new Transfer();
            transfer.setDate(LocalDateTime.now());
            transfer.setAmountBeforeFee(form.getAmount());
            transfer.setAmountAfterFee(form.getAmount() + form.getAmount() * 0.005);
            transfer.setFrom(sessionService.sessionUser());
            transfer.setTo(to);

            userAccountRepository.save(sessionService.sessionUser().getAccount().minus(transfer.getAmountAfterFee()));
            userAccountRepository.save(to.getAccount().plus(transfer.getAmountBeforeFee()));
            transferRepository.save(transfer);

        }
    }
}
