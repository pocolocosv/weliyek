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
package weliyek.serialization.number;

import java.util.List;
import java.util.Optional;

import weliyek.serialization.WkDecodingRuntimeSrlzPacketOperationData;
import weliyek.serialization.WkResultSrlzPacketOperationData;
import weliyek.serialization.WkSettingsSrlzPacketOperationData;
import weliyek.serialization.WkSrlzInputPacketFieldFrameNode;
import weliyek.serialization.WkSrlzInputPacketFieldFrameNodeCore;
import weliyek.serialization.WkSrlzInputPacketSubfieldFrameNode;
import weliyek.serialization.WkSzInputBytestream;
import weliyek.serialization.WkSzInputBytestreamBase;

public final class WkSerdeSignedByteReader
        implements WkSerdeDtreeNumberReader<
                        Byte,
                        WkSettingsSrlzPacketOperationData,
                        WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream>,
                        WkResultSrlzPacketOperationData<Byte>,
                        WkSerdeSignedByte>
{

    final WkSerdeDtreeNumberReaderCoreSimplified<
                        Byte,
                        WkSerdeSignedByteReader,
                        WkSerdeSignedByte> operationCore;

    WkSerdeSignedByteReader(
      int index,
      WkSettingsSrlzPacketOperationData settings,
      WkSzInputBytestreamBase<?> parentBytestream,
      WkSrlzInputPacketFieldFrameNodeCore<Byte,?,WkSerdeSignedByte,?,?,?> readingfieldCore,
      WkSerdeDtreeNumberDefinitionCoreSimplified<
        Byte,WkSerdeSignedByteReader,?,WkSerdeSignedByte> definitionCore) {
      operationCore = new WkSerdeDtreeNumberReaderCoreSimplified<
                            Byte, WkSerdeSignedByteReader, WkSerdeSignedByte>(
                                index,
                                settings,
                                parentBytestream,
                                readingfieldCore,
                                definitionCore,
                                this);
    }

    @Override
    public WkSettingsSrlzPacketOperationData settings() {
      return this.operationCore.settings();
    }

    @Override
    public WkDecodingRuntimeSrlzPacketOperationData<WkSzInputBytestream> dashboard() {
      return this.operationCore.getRuntimeControl().asRuntime();
    }

    @Override
    public Optional<WkResultSrlzPacketOperationData<Byte>> result() {
      return this.operationCore.result();
    }

    @Override
    public int index() {
      return this.operationCore.index();
    }

    @Override
    public WkSrlzInputPacketFieldFrameNode<Byte, WkSerdeSignedByte, ?> packetField() {
      return this.operationCore.packetField();
    }

    @Override
    public List<WkSrlzInputPacketSubfieldFrameNode<?,?,?>> subfields() {
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
    public WkSerdeSignedByte definition() {
      return this.operationCore.definition();
    }

}
