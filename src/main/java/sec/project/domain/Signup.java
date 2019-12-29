package sec.project.domain;

public class Signup {
    private long id;
    private String username;
    private String address;
    private String event;
    private boolean anonymous;

    public Signup(long id, String username, String address, String event, boolean anonymous) {
        this.id = id;
        this.username = username;
        this.address = address;
        this.event = event;
        this.anonymous = anonymous;
    }
    
    public Signup(String username, String address, String event, boolean anonymous) {
        this.username = username;
        this.address = address;
        this.event = event;
        this.anonymous = anonymous;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getEvent() {
        return event;
    }
    
    public void setEvent(String event) {
        this.event = event;
    }
    
    public boolean getAnonymous() {
        return anonymous;
    }
    
    public void setAnonymous(boolean anonymous) {
        this.anonymous = anonymous;
    }
}
