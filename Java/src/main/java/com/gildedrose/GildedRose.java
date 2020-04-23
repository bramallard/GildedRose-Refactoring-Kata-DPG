package com.gildedrose;

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
            adjustItemQualityByItemName(item);
            decreaseSellInByOne(item);
            adjustItemQualityBelowSellIn(item);
        }
    }

    private void adjustItemQualityBelowSellIn(Item item) {
        if (item.sellIn < 0) {
            if (!item.name.equals(AGED_BRIE)) {
                if (!item.name.equals(BACKSTAGE_PASSES)) {
                    if (item.quality > 0 && !item.name.equals(SULFURAS)) {
                        decreaseItemQualityByAmount(item, 1);
                    }
                } else {
                    item.quality = 0;
                }
            } else {
                if (item.quality < 50) {
                    increaseItemQualityByAmount(item, 1);
                }
            }
        }
    }

    private void adjustItemQualityByItemName(Item item) {
        if (!item.name.equals(AGED_BRIE) && !item.name.equals(BACKSTAGE_PASSES)) {
            decreaseItemQualityByAmount(item, 1);
        } else {
            if (item.quality < 50) {
                increaseItemQualityByAmount(item, 1);

                if (item.name.equals(BACKSTAGE_PASSES)) {
                    if (item.sellIn < 11 && item.quality < 50) {
                        increaseItemQualityByAmount(item, 1);
                    }

                    if (item.sellIn < 6 && item.quality < 50) {
                        increaseItemQualityByAmount(item, 1);
                    }
                }
            }
        }
    }

    private void decreaseSellInByOne(Item item) {
        if (!item.name.equals(SULFURAS)) {
            item.sellIn = item.sellIn - 1;
        }
    }

    private void increaseItemQualityByAmount(Item item, int amountToIncreaseBy) {
        item.quality = item.quality + amountToIncreaseBy;
    }

    private void decreaseItemQualityByAmount(Item item, int amountToDecreaseBy) {
        if (item.quality > 0 && !item.name.equals(SULFURAS)) {
            item.quality = item.quality - amountToDecreaseBy;
        }
    }
}