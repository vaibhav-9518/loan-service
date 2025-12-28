
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class CreditScoreService {

    private final Random random = new Random();

    public int calculateScore(Long customerId) {

        // ⏳ Artificial delay (2–4 seconds)
        try {
            Thread.sleep(2000 + random.nextInt(2000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // 🎯 Random credit score (300–900)
        return 300 + random.nextInt(600);
    }
}
