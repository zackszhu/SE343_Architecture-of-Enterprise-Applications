package com.bookstore.security;

import java.io.Serializable;
import java.security.Principal;

public class MyPrincipal implements Principal, Serializable {
	private static final long serialVersionUID = 1L;
    private String name;

    public MyPrincipal(String name)
    {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof Principal))
            return false;
        Principal other = (Principal) obj;
        if (name == null)
        {
            if (other.getName() != null)
                return false;
        } else if (!name.equals(other.getName()))
            return false;
        return true;
    }

    public String getName()
    {
        return name;
    }

    @Override
    public int hashCode() {
        assert name != null;
        return name.hashCode();
    }
    public String toString() {
        return name;
    }
}
