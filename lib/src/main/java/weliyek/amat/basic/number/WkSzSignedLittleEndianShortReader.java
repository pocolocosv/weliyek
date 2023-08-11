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
package weliyek.amat.basic.number;

import java.util.List;
import java.util.Optional;

import weliyek.amat.base.WkSzOperationSettings;
import weliyek.amat.base.input.WkSzPacketReaderField;
import weliyek.amat.base.input.WkSzPacketReaderFieldCore;
import weliyek.amat.base.input.WkSzReadingResult;
import weliyek.amat.base.input.WkSzReadingRuntime;
import weliyek.amat.base.input.WkSzInputBytestream;
import weliyek.amat.base.input.WkSzInputBytestreamBase;
import weliyek.amat.base.input.WkSzPacketReaderSubfield;

public final class WkSzSignedLittleEndianShortReader
        implements WkSzNumberReader<
                        Short,
                        WkSzOperationSettings,
                        WkSzReadingRuntime<WkSzInputBytestream>,
                        WkSzReadingResult<Short>,
                        WkSzSignedLittleEndianShort>
{

    final WkSzSimplifiedNumberReaderCore<
                        Short,
                        WkSzSignedLittleEndianShortReader,
                        WkSzSignedLittleEndianShort> operationCore;

    WkSzSignedLittleEndianShortReader(
      int index,
      WkSzOperationSettings settings,
      WkSzInputBytestreamBase<?> parentBytestream,
      WkSzPacketReaderFieldCore<
        Short,?,WkSzSignedLittleEndianShort,?,?,?> deserializingpacketCore,
      WkSzSimplifiedNumberSerializerDefinitionCore<
        Short,WkSzSignedLittleEndianShortReader,?,WkSzSignedLittleEndianShort> definitionCore) {
      this.operationCore = new WkSzSimplifiedNumberReaderCore<>(
                                    index, settings, parentBytestream,
                                    deserializingpacketCore,
                                    definitionCore,
                                    this);
    }

    @Override
    public WkSzOperationSettings settings() {
      return this.operationCore.settings();
    }

    @Override
    public WkSzReadingRuntime<WkSzInputBytestream> dashboard() {
      return this.operationCore.dashboard();
    }

    @Override
    public Optional<WkSzReadingResult<Short>> result() {
      return this.operationCore.result();
    }

    @Override
    public int index() {
      return this.operationCore.index();
    }

    @Override
    public WkSzPacketReaderField<Short, WkSzSignedLittleEndianShort, ?> packetField() {
      return this.operationCore.packet();
    }

    @Override
    public final List<WkSzPacketReaderSubfield<?,?,?>> subfields() {
      return this.operationCore.subfields();
    }

    @Override
    public String name() {
      return this.operationCore.name();
    }

    @Override
    public String toString() {
      return this.operationCore.toString();
    }

    @Override
    public WkSzSignedLittleEndianShort definition() {
      return this.operationCore.definition();
    }

}
