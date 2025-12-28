
import eureka_server.credit_score_service.service.CreditScoreService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/credit-score")
public class CreditScoreController {

    private final CreditScoreService creditScoreService;

    public CreditScoreController(CreditScoreService creditScoreService) {
        this.creditScoreService = creditScoreService;
    }

    @GetMapping("/{customerId}")
    public int getCreditScore(@PathVariable Long customerId) {
        return creditScoreService.calculateScore(customerId);
    }
}
