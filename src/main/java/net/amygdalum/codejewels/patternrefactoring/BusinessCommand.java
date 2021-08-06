package net.amygdalum.codejewels.patternrefactoring;

import java.util.Map;

public interface BusinessCommand<T> {

    T exec(Map<String, String> attributes) throws BusinessException;
}
