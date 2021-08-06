package net.amygdalum.codejewels.patternrefactoring;

import java.util.Map;

public class BusinessFacade {

    private BusinessService business;

    public BusinessFacade(BusinessService business) {
        this.business = business;
    }

    public ServiceVersion getServiceVersion(Map<String, String> attributes) throws BusinessException {
        ServiceVersion serviceVersion = business.getServiceVersion();
        if (serviceVersion == null) {
            throw new BusinessException("cannot find service version");
        }
        return serviceVersion;
    }

    public Price addPosition(Map<String, String> attributes) throws BusinessException {
        if (!isLong(attributes.get("offerId"))) {
            throw new BusinessException("offerId is not a number");
        }
        long offerId = Long.parseLong(attributes.get("offerId"));
        double price = Double.parseDouble(attributes.get("price"));
        Price finalPrice = business.addPosition(offerId, price);
        if (finalPrice == null) {
            throw new BusinessException("cannot find price for id " + offerId);
        }
        return finalPrice;
    }

    public Offer requestOffer(Map<String, String> attributes) throws BusinessException {
        long offerId = Long.parseLong(attributes.get("offerId"));
        Offer offer = business.getOffer(offerId);
        if (offer == null) {
            throw new BusinessException("cannot find offer for id " + offerId);
        }
        return offer;

    }

    private boolean isLong(String str) {
        return str.matches("\\d+");
    }

}
