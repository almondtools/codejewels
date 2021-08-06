package net.amygdalum.codejewels.patternrefactoring;

public interface AuthenticationService {

    @Deprecated
    boolean isAllowed(String user);

    void authenticate(String id, String user) throws UnauthorizedException;

    Token checkAuthentication();

}
