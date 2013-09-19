package ar.fiuba.tecnicas.tp1.test;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.fiuba.tecnicas.tp1.inventory.Inventory;
import ar.fiuba.tecnicas.tp1.item.Item;

public class GiledRoseTest {

	private static final String CONJURED_ITEM = "Conjured Mana Cake";
	private static final String BACKSTAGE_ITEM = "Backstage passes to a TAFKAL80ETC concert";
	private static final String SULFURAS_ITEM = "Sulfuras, Hand of Ragnaros";
	private static final String AGED_BRIE_ITEM = "Aged Brie";
	private static final String DEXTERITY_ITEM = "+5 Dexterity Vest";

	@Test
	public void testQualtyDecrementationWithinADay() {
		Item items[] = new Item[] { new Item(DEXTERITY_ITEM, 10, 20) };
		this.advanceADayAndAssertQuality(19, items);
	}

	@Test
	public void testSellInDecrementationWithinADay() {
		Item items[] = new Item[] { new Item(DEXTERITY_ITEM, 10, 20) };
		Inventory inventory = new Inventory(items);
		inventory.updateQuality();
		assertEquals(9, items[0].getSellIn());
	}

	@Test
	public void testQualtyDecrementationWithinTwoDays() {
		Item items[] = new Item[] { new Item(DEXTERITY_ITEM, 10, 20) };
		Inventory inventory = new Inventory(items);
		inventory.updateQuality();
		inventory.updateQuality();
		assertEquals(18, items[0].getQuality());
	}

	@Test
	public void testSellInDecrementationWithinTwoDays() {
		Item items[] = new Item[] { new Item(DEXTERITY_ITEM, 10, 20) };
		Inventory inventory = new Inventory(items);
		inventory.updateQuality();
		inventory.updateQuality();
		assertEquals(8, items[0].getSellIn());
	}

	@Test
	public void testQualtyDecrementsWithDoubleRateWithinADay() {
		Item items[] = new Item[] { new Item(DEXTERITY_ITEM, 0, 20) };
		this.advanceADayAndAssertQuality(18, items);
	}

	@Test
	public void testQualtyDecrementsWithDoubleRateWithinTwoDays() {
		Item items[] = new Item[] { new Item(DEXTERITY_ITEM, 0, 20) };
		Inventory inventory = new Inventory(items);
		inventory.updateQuality();
		inventory.updateQuality();
		assertEquals(16, items[0].getQuality());
	}

	@Test
	public void testQualtyIsNotNegative() {
		Item items[] = new Item[] { new Item(DEXTERITY_ITEM, 0, 0) };
		this.advanceADayAndAssertQuality(0, items);
	}

	@Test
	public void testAgedBrieQualtyIncrementsWithinADay() {
		Item items[] = new Item[] { new Item(AGED_BRIE_ITEM, 10, 20) };
		this.advanceADayAndAssertQuality(21, items);
	}

	@Test
	public void testQualtyIsAlwaysUnderFifty() {
		Item items[] = new Item[] { new Item(AGED_BRIE_ITEM, 10, 50) };
		this.advanceADayAndAssertQuality(50, items);
	}

	@Test
	public void testSulfurasQualtyIsAlwaysTheSame() {
		Item items[] = new Item[] { new Item(SULFURAS_ITEM, 10, 20) };
		this.advanceADayAndAssertQuality(20, items);
	}

	// Review: Si uno no indica que es cero desde un principio, se mantiene
	// constante
	@Test
	public void testSulfurasSellInIsAlwaysCero() {
		Item items[] = new Item[] { new Item(SULFURAS_ITEM, 0, 20) };
		Inventory inventory = new Inventory(items);
		inventory.updateQuality();
		assertEquals(0, items[0].getSellIn());
	}

	@Test
	public void testBackstagePassesQualtyIncrementsByOneIfSellInUpperTen() {
		Item items[] = new Item[] { new Item(BACKSTAGE_ITEM, 11, 20) };
		this.advanceADayAndAssertQuality(21, items);
	}

	@Test
	public void testBackstagePassesQualtyIncrementsByTwoIfSellInUnderTen() {
		Item items[] = new Item[] { new Item(BACKSTAGE_ITEM, 10, 20) };
		this.advanceADayAndAssertQuality(22, items);
	}

	@Test
	public void testBackstagePassesQualtyIncrementsByThreeIfSellInUnderFive() {
		Item items[] = new Item[] { new Item(BACKSTAGE_ITEM, 5, 20) };
		this.advanceADayAndAssertQuality(23, items);
	}

	@Test
	public void testBackstagePassesQualtyIsCeroIfSellInUnderCero() {
		Item items[] = new Item[] { new Item(BACKSTAGE_ITEM, 0, 20) };
		this.advanceADayAndAssertQuality(0, items);
	}

	// Este test sirve para probar el refactor que vamos a hacer en la clase.
	// @Test
	// public void testConjuredQualtyDecrementsWithDoubleRate() {
	// Item items[] = new Item[]{ new Item(CONJURED_ITEM, 10, 20) };
	// this.advanceADayAndAssertQuality(18, items);
	// }

	private void advanceADayAndAssertQuality(int expectedValue, Item items[]) {
		Inventory inventory = new Inventory(items);
		inventory.updateQuality();
		assertEquals(expectedValue, items[0].getQuality());
	}

}
