package net.amygdalum.codejewels.refactoringtopatterns;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.junit.jupiter.api.Test;

public class ReplaceImplicitTreeWithCompositeTest {

	@Test
	public void testSerializeOrders() {
		assertThat(new ReplaceImplicitTreeWithComposite().serializeOrders(), equalTo("<orders>"
			+ "<order number='123'>"
			+ "<item number='x1786'>"
			+ "carDoor"
			+ "</item>"
			+ "</order>"
			+ "</orders>"));
	}

}
