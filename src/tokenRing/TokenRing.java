package tokenRing;

import support.Message;
import support.Stat;

public interface TokenRing {
    Stat getStats();

    void addMessage(Message message);
}
