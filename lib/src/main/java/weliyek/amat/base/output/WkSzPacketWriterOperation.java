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
package weliyek.amat.base.output;

import java.util.List;

import weliyek.serialization.WkSzDefinition;
import weliyek.serialization.WkSzPacketOperation;
import weliyek.amat.base.OperationSettings;

public interface WkSzPacketWriterOperation<
                        T,
                        S extends OperationSettings,
                        Q extends SerializingRuntime<?>,
                        R extends SerializingResult,
                        D extends WkSzDefinition<T,?>>
        extends WkSzPacketOperation<
                        S, Q, R, D,
                        WkSzPacketWriterField<T,D,?>>,
                WkSzPacketWriterSegment
{

  T serializable();

  @Override
  List<WkSzPacketWriterSubfield<?,?,?>> subfields();

}
