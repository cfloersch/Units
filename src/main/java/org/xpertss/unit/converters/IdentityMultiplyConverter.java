package org.xpertss.unit.converters;

import xpertss.measure.UnitConverter;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 */
final class IdentityMultiplyConverter implements MultiplyConverter {


    final static IdentityMultiplyConverter INSTANCE = new IdentityMultiplyConverter();

    private IdentityMultiplyConverter()
    {
        // hidden
    }
    
    @Override
    public boolean isIdentity()
    {
        return true;
    }

    @Override
    public UnitConverter inverse()
    {
        return this;
    }

    @Override
    public Number convert(Number value)
    {
        return value;
    }



    @Override
    public UnitConverter concatenate(UnitConverter converter)
    {
        return converter;
    }

    @Override
    public List<? extends UnitConverter> getConversionSteps()
    {
        return Collections.emptyList();
    }

    public Number getValue()
    {
        return 1;
    }
    
    @Override
    public int compareTo(UnitConverter o)
    {
        return AbstractConverter.IDENTITY.compareTo(o);
    }
    
    @Override
	public boolean equals(Object obj)
    {
		if (this == obj) return true;
		if (obj instanceof IdentityMultiplyConverter) {
			IdentityMultiplyConverter other = (IdentityMultiplyConverter) obj;
			if(this.isIdentity() && other.isIdentity()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int hashCode()
    {
		return Objects.hashCode(getValue());
	}
}
