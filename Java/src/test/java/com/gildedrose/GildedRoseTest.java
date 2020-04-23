package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void item_quality_never_below_zero_with_sell_in_above_zero() {
        Item[] items = new Item[] {new Item("item", 0, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("item", app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void item_quality_and_sell_in_lowered_by_one() {
        Item[] items = new Item[] {new Item("item", 5, 5)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("item", app.items[0].name);
        assertEquals(4, app.items[0].sellIn);
        assertEquals(4, app.items[0].quality);
    }

    @Test
    void item_quality_degrades_twice_as_fast_when_sell_in_passed() {
        Item[] items = new Item[] {new Item("item", -1, 10)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("item", app.items[0].name);
        assertEquals(-2, app.items[0].sellIn);
        assertEquals(8, app.items[0].quality);
    }

    @Test
    void item_quality_and_sell_in_lowered_by_one_edge_case() {
        Item[] items = new Item[] {new Item("item", 0, 10)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("item", app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(8, app.items[0].quality);
    }

    @Test
    void aged_brie_quality_increases_sell_in_passed() {
        Item[] items = new Item[] {new Item("Aged Brie", 0, 0)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Aged Brie", app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(1, app.items[0].quality);
    }

    @Test
    void aged_brie_quality_increases_sell_in_not_passed() {
        Item[] items = new Item[] {new Item("Aged Brie", 5, 5)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Aged Brie", app.items[0].name);
        assertEquals(4, app.items[0].sellIn);
        assertEquals(6, app.items[0].quality);
    }

    @Test
    void aged_brie_quality_never_increases_above_fifty_sell_in_not_passed() {
        Item[] items = new Item[] {new Item("Aged Brie", 5, 50)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Aged Brie", app.items[0].name);
        assertEquals(4, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void aged_brie_quality_never_increases_above_fifty_sell_in_passed() {
        Item[] items = new Item[] {new Item("Aged Brie", -1, 50)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Aged Brie", app.items[0].name);
        assertEquals(-2, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void backstage_passes_quality_never_increases_above_fifty_sell_in_passed() {
        Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", -1, 50)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[0].name);
        assertEquals(-2, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void backstage_passes_quality_never_increases_above_fifty_sell_in_not_passed() {
        Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 5, 50)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[0].name);
        assertEquals(4, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void backstage_passes_quality_increases_by_two_with_sell_in_ten_days_or_less() {
        Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[0].name);
        assertEquals(9, app.items[0].sellIn);
        assertEquals(22, app.items[0].quality);
    }

    @Test
    void backstage_passes_quality_increases_by_two_with_sell_in_ten_days_or_less_edge_case() {
        Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 11, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[0].name);
        assertEquals(10, app.items[0].sellIn);
        assertEquals(22, app.items[0].quality);
    }

    @Test
    void backstage_passes_quality_increases_by_two_with_sell_in_ten_days_or_less_quality_not_above_50() {
        Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[0].name);
        assertEquals(9, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void backstage_passes_quality_increases_by_two_with_sell_in_five_days_or_less() {
        Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[0].name);
        assertEquals(4, app.items[0].sellIn);
        assertEquals(23, app.items[0].quality);
    }

    @Test
    void backstage_passes_quality_increases_by_two_with_sell_in_five_days_or_less_edge_case() {
        Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 6, 20)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[0].name);
        assertEquals(5, app.items[0].sellIn);
        assertEquals(23, app.items[0].quality);
    }

    @Test
    void backstage_passes_quality_increases_by_two_with_sell_in_five_days_or_less_quality_not_above_50() {
        Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[0].name);
        assertEquals(4, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void backstage_passes_quality_increases_by_two_with_sell_in_five_days_or_less_quality_not_above_50_edge_case() {
        Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 1, 50)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[0].name);
        assertEquals(0, app.items[0].sellIn);
        assertEquals(50, app.items[0].quality);
    }

    @Test
    void backstage_passes_quality_zero_when_sell_in_passed() {
        Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", 0, 50)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Backstage passes to a TAFKAL80ETC concert", app.items[0].name);
        assertEquals(-1, app.items[0].sellIn);
        assertEquals(0, app.items[0].quality);
    }

    @Test
    void sulfuras_quality_and_sell_in_never_change() {
        Item[] items = new Item[] {new Item("Sulfuras, Hand of Ragnaros", 50, 80)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Sulfuras, Hand of Ragnaros", app.items[0].name);
        assertEquals(50, app.items[0].sellIn);
        assertEquals(80, app.items[0].quality);
    }

    @Test
    void conjured_item_quality_and_sell_in_lowered_with_sell_in_above_zero() {
        Item[] items = new Item[] {new Item("Conjured item", 5, 5)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Conjured item", app.items[0].name);
        assertEquals(4, app.items[0].sellIn);
        assertEquals(3, app.items[0].quality);
    }

    @Test
    void conjured_item_quality_and_sell_in_lowered_with_sell_in_below_zero() {
        Item[] items = new Item[] {new Item("Conjured item", -2, 5)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Conjured item", app.items[0].name);
        assertEquals(-3, app.items[0].sellIn);
        assertEquals(1, app.items[0].quality);
    }

    @Test
    void conjured_item_quality_and_sell_in_lowered_with_sell_in_above_zero_different_item() {
        Item[] items = new Item[] {new Item("Conjured Old Sock", 5, 5)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Conjured Old Sock", app.items[0].name);
        assertEquals(4, app.items[0].sellIn);
        assertEquals(3, app.items[0].quality);
    }

    @Test
    void conjured_item_quality_and_sell_in_lowered_with_sell_in_below_zero_different_item() {
        Item[] items = new Item[] {new Item("Conjured Old Sock", -2, 5)};
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("Conjured Old Sock", app.items[0].name);
        assertEquals(-3, app.items[0].sellIn);
        assertEquals(1, app.items[0].quality);
    }
}