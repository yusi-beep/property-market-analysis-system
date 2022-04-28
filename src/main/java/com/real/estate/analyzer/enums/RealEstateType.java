package com.real.estate.analyzer.enums;

import java.util.NoSuchElementException;

public enum RealEstateType {
	
    EDNOSTAEN(new String[] {"Едностаен", "1-СТАЕН"}),
    DVUSTAEN(new String[] {"Двустаен", "2-СТАЕН"}),
    TRISTAEN(new String[] {"Тристаен", "3-СТАЕН"}),
    CHETIRISTAEN(new String[] {"Четиристаен", "4-СТАЕН"}),
    MNOGOSTAEN(new String[] {"Многостаен", "МНОГОСТАЕН"}),				//caps
    MEZONET(new String[] {"Мезонет"}),
    GARAJ(new String[] {"Гараж"}),
    OFIS(new String[] {"Офис", "ОФИС"}),								//caps
    ATELIE_TAVAN(new String[] {"Ателие", "Таван", "Таванска"}),
    MAGAZIN(new String[] {"Магазин","МАГАЗИН"}),						//caps
    ZAVEDENIE(new String[] {"Заведение"}),
    SKLAD(new String[] {"Склад"}),
    HOTEL(new String[] {"Хотел"}),
    ETAJ_OT_KUSHTA(new String[] {"Етаж от къща", "ЕТАЖ ОТ КЪЩА"}),
    KUSHTA(new String[] {"Къща", "КЪЩА"}),								//caps
    VILA(new String[] {"Вила"}),
    PARCEL(new String[] {"Парцел","ПАРЦЕЛ"}),							//caps
    ZEMEDELSKA_ZEMQ(new String[] {"Земеделска земя"}),
    BIZNES_IMOT(new String[] {"Бизнес имот"}),
    PROM_POMESHTENIE(new String[] {"Пром. помещение"}),
    DRUGI(new String[] {"", "Други"});

    String[] estateTypes;

    RealEstateType(String[] estateTypes) {
        this.estateTypes = estateTypes;
    }

    public static RealEstateType getTypeFrom(String name) {
        for (RealEstateType realEstateType : RealEstateType.values()) {
        	
        	for (String estateType : realEstateType.estateTypes) {
	            
        		if (name.contains(estateType)) {
	                return realEstateType;
	            }
        	}
        }
        
        throw new NoSuchElementException(String.format("%s is not found", name));
    }
}
