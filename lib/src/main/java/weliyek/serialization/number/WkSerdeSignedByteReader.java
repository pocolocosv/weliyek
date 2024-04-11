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

import weliyek.serialization.WkSerdeDtreeOperationInputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.WkSerdeDtreeNodeDataInputComponent;
import weliyek.serialization.WkSerdeDtreeNodeDataInputComponentCore;
import weliyek.serialization.WkSrlzInputPacketSubfieldFrameNode;
import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;

public final class WkSerdeSignedByteReader
        implements WkSerdeDtreeNumberReader<
                        Byte,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                        WkSerdeDtreeOperationResult<Byte>,
                        WkSerdeSignedByte>
{

    final WkSerdeDtreeNumberReaderCoreSimplified<
                        Byte,
                        WkSerdeSignedByteReader,
                        WkSerdeSignedByte> operationCore;

    WkSerdeSignedByteReader(
      int index,
      WkSerdeDtreeOperationSettings settings,
      WkSerdeDtreeBytestreamInputBase<?> parentBytestream,
      WkSerdeDtreeNodeDataInputComponentCore<Byte,?,WkSerdeSignedByte,?,?,?> readingfieldCore,
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
    public WkSerdeDtreeOperationSettings settings() {
      return this.operationCore.settings();
    }

    @Override
    public WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput> dashboard() {
      return this.operationCore.getRuntimeControl().asRuntime();
    }

    @Override
    public Optional<WkSerdeDtreeOperationResult<Byte>> result() {
      return this.operationCore.result();
    }

    @Override
    public int index() {
      return this.operationCore.index();
    }

    @Override
    public WkSerdeDtreeNodeDataInputComponent<Byte, WkSerdeSignedByte, ?> packetField() {
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
