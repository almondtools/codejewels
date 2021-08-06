package net.amygdalum.codejewels.patternrefactoring;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DBService {

    private static final Logger LOG = LoggerFactory.getLogger(DBService.class);

    private AuthenticationService auth;
    private SerializationService serializer;
    private BusinessService business;

    public DBService(AuthenticationService auth, SerializationService serializer, BusinessService business) {
        this.auth = auth;
        this.serializer = serializer;
        this.business = business;
    }

    public DBResponse getServiceVersion(DBRequest request) {
        LOG.info("starting request " + request.getId());
        DBResponse response = new DBResponse();
        String user = request.getUser();
        if (!auth.isAllowed(user)) {
            LOG.warn("user " + user + " is not authorized");
            response.setStatus(401);
            response.setPayload("Access denied");
        } else {
            try {
                auth.authenticate(request.getId(), user);
                response.setUser(user);
                response.setRole(auth.checkAuthentication().getRole());
                ServiceVersion serviceVersion = business.getServiceVersion();
                if (serviceVersion == null) {
                    response.setStatus(422);
                    response.setPayload("cannot find service version");
                } else {
                    response.setStatus(200);
                    response.setPayload(serviceVersion);
                }
            } catch (Exception e) {
                LOG.warn("unexpected exception: " + e.getMessage());
                response.setStatus(500);
                response.setPayload(e.getMessage());
            }
        }
        if (response.getPayload() instanceof ServiceVersion) {
            response.setPayload(serializer.serialize(response.getPayload()));
        }
        LOG.info("finished request " + request.getId());
        return response;
    }

    public DBResponse addPosition(DBRequest request) {
        LOG.info("starting request " + request.getId());
        DBResponse response = new DBResponse();
        String user = request.getUser();
        if (!auth.isAllowed(user)) {
            response.setStatus(401);
            response.setPayload("Access denied");
            LOG.warn("user " + user + " is not authorized");
        } else {
            try {
                auth.authenticate(request.getId(), user);

                response.setUser(user);
                response.setRole(auth.checkAuthentication().getRole());

                if (isLong(request.getAttributes().get("offerId"))) {
                    long offerId = Long.parseLong(request.getAttributes().get("offerId"));
                    double price = Double.parseDouble(request.getAttributes().get("price"));
                    Price finalPrice = business.addPosition(offerId, price);
                    if (finalPrice == null) {
                        response.setStatus(422);
                        response.setPayload("cannot find price for id " + offerId);
                    } else {
                        response.setStatus(200);
                        response.setPayload(finalPrice);
                    }
                }
            } catch (Exception e) {
                LOG.warn("unexpected exception: " + e.getMessage());
                response.setStatus(500);
                response.setPayload(e.getMessage());
            }
        }
        if (response.getPayload() instanceof Price) {
            response.setPayload(serializer.serialize(response.getPayload()));
        }
        LOG.info("finished request " + request.getId());
        return response;
    }

    public DBResponse requestOffer(DBRequest request) {
        LOG.info("starting request " + request.getId());
        DBResponse response = new DBResponse();
        String user = request.getUser();
        if (!auth.isAllowed(user)) {
            response.setStatus(401);
            response.setPayload("Access denied");
            LOG.warn("user " + user + " is not authorized");
        } else {
            try {
                auth.authenticate(request.getId(), user);

                response.setUser(user);
                response.setRole(auth.checkAuthentication().getRole());

                long offerId = Long.parseLong(request.getAttributes().get("offerId"));
                Offer offer = business.getOffer(offerId);
                if (offer == null) {
                    response.setStatus(422);
                    response.setPayload("cannot find offer for id " + offerId);
                } else {
                    response.setStatus(200);
                    response.setPayload(offer);
                }
            } catch (Exception e) {
                LOG.warn("unexpected exception: " + e.getMessage());
                response.setStatus(500);
                response.setPayload(e.getMessage());
            }
        }
        if (response.getPayload() instanceof Offer) {
            response.setPayload(serializer.serialize(response.getPayload()));
        }
        LOG.info("finished request " + request.getId());
        return response;
    }

    private boolean isLong(String str) {
        return str.matches("\\d+");
    }

}
