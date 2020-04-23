package com.gildedrose;

import java.util.Locale;

class GildedRose {
    public static final String BACKSTAGE_PASSES = "Backstage passes to a TAFKAL80ETC concert";
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";
    public static final String AGED_BRIE = "Aged Brie";
    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        for (Item item : items) {
            decreaseSellInByOne(item);

            if (item.sellIn < 0) {
                adjustItemQualityAfterSellInHasPassed(item);
            } else {
                adjustItemQualityBeforeSellInHasPassed(item);
            }
        }
    }

    private void adjustItemQualityAfterSellInHasPassed(Item item) {
        if (item.sellIn < 0) {
            switch (item.name) {
                case AGED_BRIE:
                    increaseItemQualityByOne(item);
                    break;
                case BACKSTAGE_PASSES:
                    item.quality = 0;
                    break;
                default:
                    if (item.quality > 0 && !item.name.equals(SULFURAS)) {
                        decreaseItemQualityByAmount(item, 2);
                    }
            }
        }
    }

    private void adjustItemQualityBeforeSellInHasPassed(Item item) {
        if (!item.name.equals(AGED_BRIE) && !item.name.equals(BACKSTAGE_PASSES)) {
            decreaseItemQualityByAmount(item, 1);
        } else {
            increaseItemQualityByOne(item);
            if (item.name.equals(BACKSTAGE_PASSES)) {
                if (item.sellIn < 11) {
                    increaseItemQualityByOne(item);
                }

                if (item.sellIn < 6) {
                    increaseItemQualityByOne(item);
                }
            }
        }
    }

    private void decreaseSellInByOne(Item item) {
        if (!item.name.equals(SULFURAS)) {
            item.sellIn = item.sellIn - 1;
        }
    }

    private void increaseItemQualityByOne(Item item) {
        if (item.quality < 50) {
            item.quality = item.quality + 1;
        }
    }

    private void decreaseItemQualityByAmount(Item item, int amountToDecreaseBy) {
        if (item.name.toLowerCase(Locale.ENGLISH).contains("conjured")) {
            amountToDecreaseBy = amountToDecreaseBy * 2;
        }

        if (item.quality > 0 && !item.name.equals(SULFURAS)) {
            item.quality = item.quality - amountToDecreaseBy;
        }
    }
}