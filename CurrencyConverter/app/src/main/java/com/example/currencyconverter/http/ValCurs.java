package com.example.currencyconverter.http;

import org.simpleframework.xml.Attribute;
import org.simpleframework.xml.Element;
import org.simpleframework.xml.ElementList;
import org.simpleframework.xml.Root;

import java.util.List;

@Root(name = "ValCurs", strict = false)
public class ValCurs
{
    @Attribute(required = false)
    private String Date;

    @Attribute(required = false)
    private String name;

    @ElementList(inline = true, entry = "Valute", required = false)
    private List<Valute> Valute;

    public ValCurs() {
    }

    public String getDate ()
    {
        return Date;
    }

    public void setDate (String Date)
    {
        this.Date = Date;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public List<Valute> getValute ()
    {
        return Valute;
    }

    public void setValute (List<Valute> Valute)
    {
        this.Valute = Valute;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Date = "+Date+", name = "+name+", Valute = "+Valute+"]";
    }
}

