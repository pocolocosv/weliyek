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

import java.util.List;
import java.util.Optional;

import weliyek.amat.base.WkSzCommonOperationRuntime;
import weliyek.amat.base.WkSzOperationResult;
import weliyek.amat.base.WkSzOperationSettings;

/**
 * An operation is in charge of managing the data and logic needed to serialize
 * a particular type.
 *
 * @param <S> Settings supplied during the instantiation of the operation.
 * @param <Q> Data generated during serialization. Only available while active.
 * @param <R> Data generated after serialization is completed.
 * @param <D> Protocol definition type for this operation.
 * @param <K> Packet handler objects of this operation.
 */
public interface WkSzPacketOperation<
                        S extends WkSzOperationSettings,
                        Q extends WkSzCommonOperationRuntime<?>,
                        R extends WkSzOperationResult,
                        D extends WkSzDefinition<?,?>,
                        K extends WkSzPacketField<?,?,?>>
        extends WkSzPacketSegment,
                WkSzDatatypeSegment
{

  D definition();

  S settings();

  Q dashboard();

  /**
   * A result will be available once all the bytes required for this field have been processed.
   *
   * @return an Optional with the result object once the field has been processed.
   *         Returns an empty Optional otherwise.
   */
  Optional<R> result();

  int index();

  K packetField();

  List<? extends WkSzPacketSubfield<?>> subfields();

  String name();

  @Override
  default SegmentType type() {
    return SegmentType.PACKET;
  }

}
