package com.wch.test.disruptor.disruptor;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class LongEvent
{
    private long value;
    
    private String name;

    public void set(long value)
    {
        this.value = value;
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ToStringBuilder.reflectionToString(this);
	}
}