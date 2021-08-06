package net.amygdalum.codejewels.patternrefactoring.impl;

import net.amygdalum.codejewels.patternrefactoring.AuthenticationService;
import net.amygdalum.codejewels.patternrefactoring.BusinessService;
import net.amygdalum.codejewels.patternrefactoring.Offer;
import net.amygdalum.codejewels.patternrefactoring.Price;
import net.amygdalum.codejewels.patternrefactoring.ServiceVersion;
import net.amygdalum.codejewels.patternrefactoring.Token;

public class FakeBusinessService implements BusinessService {

    private AuthenticationService auth;

    public FakeBusinessService(AuthenticationService auth) {
        this.auth = auth;
    }

    @Override
    public ServiceVersion getServiceVersion() {
        Token token = auth.checkAuthentication();
        long id = Long.parseLong(token.getId());
        if (id < 10L) {
            return new FakeServiceVersion(id);
        } else if (id < 100L) {
            throw new RuntimeException("unexpected exception");
        } else {
            return null;
        }
    }

    @Override
    public Offer getOffer(long offerId) {
        Token token = auth.checkAuthentication();
        long id = Long.parseLong(token.getId());
        if (id < 10L) {
            return new FakeOffer(offerId);
        } else if (id < 100L) {
            throw new RuntimeException("unexpected exception");
        } else {
            return null;
        }
    }

    @Override
    public Price addPosition(long offerId, double price) {
        Token token = auth.checkAuthentication();
        long id = Long.parseLong(token.getId());
        if (id < 10L) {
            return new FakePrice(offerId, price);
        } else if (id < 100L) {
            throw new RuntimeException("unexpected exception");
        } else {
            return null;
        }
    }

}
