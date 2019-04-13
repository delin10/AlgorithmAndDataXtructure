package org.delin.Algorithm;


import org.junit.Test;

public class TestKMP {

	@Test
	public void test() {
		TestUtils.printArr("模式串", KMP.getModel("ABADABEF"));
		System.out.println(KMP.match("BBABAG", "BAG", KMP.getModel("BAG")));
	}

}
