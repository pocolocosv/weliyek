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

public final class WkSerdeSignedBigEndianShortReader
        implements WkSerdeDtreeNumberMsgReader<
                        Short,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeOperationInputRuntime<WkSerdeDtreeBytestreamInput>,
                        WkSerdeDtreeOperationResult<Short>,
                        WkSerdeSignedBigEndianShort>
{

    final WkSerdeDtreeNumberMsgReaderCoreSimplified<
                        Short,
                        WkSerdeSignedBigEndianShortReader,
                        WkSerdeSignedBigEndianShort> operationCore;

    WkSerdeSignedBigEndianShortReader(
      int index,
      WkSerdeDtreeOperationSettings settings,
      WkSerdeDtreeBytestreamInputBase<?> parentBytestream,
      WkSerdeDtreeMsgInputFieldCore<?,?,?,?,?,?,?,?> msgFieldCore,
      WkSerdeDtreeNumberDefinitionCoreSimplified<
        Short,WkSerdeSignedBigEndianShortReader,?,WkSerdeSignedBigEndianShort> definitionCore) {
      operationCore = new WkSerdeDtreeNumberMsgReaderCoreSimplified<Short,
          WkSerdeSignedBigEndianShortReader,
          WkSerdeSignedBigEndianShort>(
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
      return this.operationCore.dashboard();
    }

    @Override
    public Optional<WkSerdeDtreeOperationResult<Short>> result() {
      return this.operationCore.result();
    }

    @Override
    public int index() {
      return this.operationCore.index();
    }

    @Override
    public WkSerdeDtreeMsgInputField<?,?,?> parentField() {
      return this.operationCore.parentField();
    }

    @Override
    public final List<WkSerdeDtreeMsgInputField<?,?,?>> subfields() {
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
    public WkSerdeSignedBigEndianShort definition() {
      return this.operationCore.definition();
    }

}
