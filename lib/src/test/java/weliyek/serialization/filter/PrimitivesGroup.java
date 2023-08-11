/*
 * Weliyek Java Library
 * Copyright (C) 2023  Ricardo Villalobos - All Rights Reserved
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package weliyek.serialization.filter;

import java.util.Objects;

public class PrimitivesGroup
{

    public final byte b;
    public final short s;
    public final int i;
    public final long l;
    public final String fixedLengthStr;
    public final String varLengthStr;

    public static final int FIXED_STRING_LENGTH = 10;

    PrimitivesGroup(int i, String str) {
        this.b = (byte)i;
        this.s = (short)(10*i);
        this.i = 100*i;
        this.l = 1000L*i;
        this.fixedLengthStr = Objects.requireNonNull(str);
        this.varLengthStr = Objects.requireNonNull(str);
    }

    PrimitivesGroup(PrimitivesGroupDeserializer deserializer) {
      this(
          deserializer.byteReadingSubfield.field().get().firstOperation().get().result().get().deserialized().get().byteValue(),
          deserializer.shortReadingSubfield.field().get().firstOperation().get().result().get().deserialized().get().shortValue(),
          deserializer.intReadingSubfield.field().get().firstOperation().get().result().get().deserialized().get().intValue(),
          deserializer.longReadingSubfield.field().get().firstOperation().get().result().get().deserialized().get().longValue(),
          deserializer.fixedStrReadingSubfield.field().get().firstOperation().get().result().get().deserialized().get(),
          deserializer.dynStrReadingSubfield.field().get().firstOperation().get().result().get().deserialized().get());
    }

    PrimitivesGroup(byte b, short s, int i, long l, String fixedLengthStr, String varLengthStr) {
        this.b = b;
        this.s = s;
        this.i = i;
        this.l = l;
        this.fixedLengthStr = Objects.requireNonNull(fixedLengthStr);
        this.varLengthStr = Objects.requireNonNull(varLengthStr);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + b;
        result = prime * result + fixedLengthStr.hashCode();
        result = prime * result + i;
        result = prime * result + (int) (l ^ (l >>> 32));
        result = prime * result + s;
        result = prime * result + varLengthStr.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof PrimitivesGroup))
            return false;
        PrimitivesGroup other = (PrimitivesGroup) obj;
        if (b != other.b)
            return false;
        if (!fixedLengthStr.equals(other.fixedLengthStr))
            return false;
        if (i != other.i)
            return false;
        if (l != other.l)
            return false;
        if (s != other.s)
            return false;
        if (!varLengthStr.equals(other.varLengthStr))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "{" + b + "|" + s + "|" + i + "|" + l + "|" + fixedLengthStr + "|" + varLengthStr + "}";
    }

}
