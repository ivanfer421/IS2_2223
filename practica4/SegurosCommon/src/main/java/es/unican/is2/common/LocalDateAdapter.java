package es.unican.is2.common;



import java.time.LocalDate;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Clase necesaria para realizar el mapeado de fechas a LocalDate
 * desde XML
 */
public class LocalDateAdapter extends XmlAdapter<String, LocalDate> {
    @Override
	public LocalDate unmarshal(String v) throws Exception {
        return LocalDate.parse(v);
    }

    @Override
	public String marshal(LocalDate v) throws Exception {
        return v.toString();
    }
}