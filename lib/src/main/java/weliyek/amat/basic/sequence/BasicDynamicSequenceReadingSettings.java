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
package weliyek.amat.basic.sequence;

import weliyek.amat.basic.dynamic.sequence.WkSzVariableLengthOperationSettings;

public class BasicDynamicSequenceReadingSettings
        implements WkSzVariableLengthOperationSettings
{

    private final int requestedSize;

    public BasicDynamicSequenceReadingSettings(int sequenceSize) {
      this.requestedSize = sequenceSize;
    }

    @Override
    public int getRequestedLength() {
      return this.requestedSize;
    }

    /*
    static public boolean lengthIsNotInBounds(long length, VariableSizeSequenceDefinition<?> dynamicProtocolField) {
      final long min = dynamicProtocolField.minimalSize();
      final long max = dynamicProtocolField.maximalSize();
      return (length < min) || (max < length);
    }
    */

}
