package net.amygdalum.codejewels.patternrefactoring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBService {

    private static final Logger LOG = LoggerFactory.getLogger(DBService.class);

    private AuthenticationService auth;

    private ResponseFactory responses;

    private BusinessFacade businessFacade;

    public DBService(AuthenticationService auth, SerializationService serializer, BusinessService business) {
        this.auth = auth;
        this.responses = new ResponseFactory(auth, serializer);
        this.businessFacade = new BusinessFacade(business);
    }

    public DBResponse getServiceVersion(DBRequest request) {
        return exec(request, businessFacade::getServiceVersion);
    }

    public DBResponse addPosition(DBRequest request) {
        return exec(request, businessFacade::addPosition);
    }

    public DBResponse requestOffer(DBRequest request) {
        return exec(request, businessFacade::requestOffer);
    }

    private <T> DBResponse exec(DBRequest request, BusinessCommand<T> command) {
        LOG.info("starting request " + request.getId());
        String user = request.getUser();
        try {
            auth.authenticate(request.getId(), user);
            try {
                T object = command.exec(request.getAttributes());
                return responses.success(user, object);
            } catch (BusinessException e) {
                return responses.businessError(user, e.getMessage());
            } catch (Exception e) {
                LOG.warn("unexpected exception: " + e.getMessage());
                return responses.unexpectedError(user, e.getMessage());
            }
        } catch (UnauthorizedException e) {
            LOG.warn("user " + user + " is not authorized");
            return responses.unauthorized();
        } finally {
            LOG.info("finished request " + request.getId());
        }
    }

}
