import org.aeonbits.owner.Config;

public interface ServerConfig extends Config {
    int port();
    String hostname();
    @DefaultValue("42")
    int maxThreads();

    String URL();
    String URL_TELE2();

}
