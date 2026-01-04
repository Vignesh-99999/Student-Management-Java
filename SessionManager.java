import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class SessionManager {
    private static SessionManager instance;
    private Map<String, SessionData> sessions;

    private SessionManager() {
        sessions = new HashMap<>();
    }

    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager();
        }
        return instance;
    }

    public String createSession() {
        String sessionId = generateSessionId();
        sessions.put(sessionId, new SessionData());
        return sessionId;
    }

    public void setUsername(String sessionId, String username) {
        SessionData sessionData = sessions.get(sessionId);
        if (sessionData != null) {
            sessionData.setUsername(username);
        }
    }

    public String getUsername(String sessionId) {
        SessionData sessionData = sessions.get(sessionId);
        return sessionData != null ? sessionData.getUsername() : null;
    }

    private String generateSessionId() {
        // Generate a unique session ID (e.g., using UUID)
        return UUID.randomUUID().toString();
    }

    private static class SessionData {
        private String username;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }
    }
}
