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
package weliyek.serialization;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WkBasicResultSrlzPacketOperationData<X>
  implements WkResultSrlzPacketOperationData<X>
{

    private static final Logger logger = LoggerFactory.getLogger(WkBasicResultSrlzPacketOperationData.class);

    private final Optional<X> serializable;

    public WkBasicResultSrlzPacketOperationData(
            WkSerdeDtreeNodeDataOperation<?,?,?,? extends WkSerdeDtreeNodeStructDefinition<X>,?> operation,
            X serializable) {
        this.serializable = Optional.ofNullable(serializable);
    }

    @Override
    public Optional<X> serializable() {
        return this.serializable;
    }

    @Override
    public String toString() {
      if (logger.isDebugEnabled()) {
        StringBuilder strB = new StringBuilder(super.toString());
        strB.append("<{");
        if (serializable().isPresent()) {
          strB.append(serializable().get());
        }
        strB.append("}>");
        return strB.toString();
      }
      return super.toString();
    }

}
