package com.haselt.debtcollecting.mapper;

import com.haselt.debtcollecting.dto.InvoiceItemDto;
import com.haselt.debtcollecting.entity.InvoiceItem;

public class InvoiceItemMapper {

    public static InvoiceItem dtoToEntity(InvoiceItemDto invoiceItemDto) {
        InvoiceItem invoiceItem = new InvoiceItem()
                .setItemName(invoiceItemDto.getItemName())
                .setItemType(invoiceItemDto.getItemType())
                .setItemDate(invoiceItemDto.getItemDate())
                .setItemDescription(invoiceItemDto.getItemDescription())
                .setItemPriceWithoutVat(invoiceItemDto.getItemPriceWithoutVat())
                .setItemVatPercentage(invoiceItemDto.getItemVatPercentage());
        return invoiceItem;
    }

    public static InvoiceItemDto entityToDto(InvoiceItem invoiceItem) {
        InvoiceItemDto invoiceItemDto = InvoiceItemDto.builder()
                .itemName(invoiceItem.getItemName())
                .itemType(invoiceItem.getItemType())
                .itemDate(invoiceItem.getItemDate())
                .itemDescription(invoiceItem.getItemDescription())
                .itemPriceWithoutVat(invoiceItem.getItemPriceWithoutVat())
                .itemVatPercentage(invoiceItem.getItemVatPercentage())
                .priceWithVat(invoiceItem.getPriceWithVat())
                .vatFromPrice(invoiceItem.getVatFromPrice())
                .build();
        return invoiceItemDto;
    }

}
