package net.amygdalum.codejewels.patternrefactoring;

public interface BusinessService {

    ServiceVersion getServiceVersion();

    Offer getOffer(long offerId);

    Price addPosition(long offerId, double price);

}
