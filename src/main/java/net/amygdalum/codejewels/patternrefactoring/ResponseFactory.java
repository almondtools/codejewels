package net.amygdalum.codejewels.patternrefactoring;

public class ResponseFactory {

    private AuthenticationService auth;
    private SerializationService serializer;

    public ResponseFactory(AuthenticationService auth, SerializationService serializer) {
        this.auth = auth;
        this.serializer = serializer;
    }

    public DBResponse unexpectedError(String user, String msg) {
        DBResponse response = new DBResponse();
        response.setUser(user);
        response.setRole(auth.checkAuthentication().getRole());
        response.setStatus(500);
        response.setPayload(msg);
        return response;
    }

    public DBResponse businessError(String user, String msg) {
        DBResponse response = new DBResponse();
        response.setUser(user);
        response.setRole(auth.checkAuthentication().getRole());
        response.setStatus(422);
        response.setPayload(msg);
        return response;
    }

    public <T> DBResponse success(String user, T serviceVersion) {
        DBResponse response = new DBResponse();
        response.setUser(user);
        response.setRole(auth.checkAuthentication().getRole());
        response.setStatus(200);
        response.setPayload(serviceVersion);
        response.setPayload(serializer.serialize(serviceVersion));
        return response;
    }

    public DBResponse unauthorized() {
        DBResponse response = new DBResponse();
        response.setStatus(401);
        response.setPayload("Access denied");
        return response;
    }

}
