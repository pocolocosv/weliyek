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

import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeMsgInputField;
import weliyek.serialization.WkSerdeDtreeMsgInputFieldCore;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntime;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;

public final class WkSerdeUnsignedByteReader
        implements WkSerdeDtreeNumberMsgReader<
                        Integer,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                        WkSerdeDtreeOperationResult<Integer>,
                        WkSerdeUnsignedByte>
{

    final WkSerdeDtreeNumberMsgReaderCoreSimplified<
                        Integer,
                        WkSerdeUnsignedByteReader,
                        WkSerdeUnsignedByte> operationCore;

    WkSerdeUnsignedByteReader(
      int index,
      WkSerdeDtreeOperationSettings settings,
      WkSerdeDtreeBytestreamInputBase<?> parentBytestream,
      WkSerdeDtreeMsgInputFieldCore<?,?,?,?,?,?,?,?> msgFieldCore,
      WkSerdeDtreeNumberDefinitionCoreSimplified<
        Integer,WkSerdeUnsignedByteReader,?,WkSerdeUnsignedByte> definitionCore) {
      operationCore = new WkSerdeDtreeNumberMsgReaderCoreSimplified<
          Integer, WkSerdeUnsignedByteReader, WkSerdeUnsignedByte>(
                                index,
                                settings,
                                parentBytestream,
                                msgFieldCore,
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
    public Optional<WkSerdeDtreeOperationResult<Integer>> result() {
      return this.operationCore.result();
    }

    @Override
    public int index() {
      return this.operationCore.index();
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
    public WkSerdeUnsignedByte definition() {
      return this.operationCore.definition();
    }

    @Override
    public List<WkSerdeDtreeMsgInputField<?,?,?>> subfields() {
      return this.operationCore.subfields();
    }

    @Override
    public WkSerdeDtreeMsgInputField<?,?,?> parentField() {
      return this.operationCore.parentField();
    }

}
