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
package weliyek.util.array;

import java.util.List;
import java.util.Optional;

import weliyek.serialization.WkSerdeDtreeBytestreamInput;
import weliyek.serialization.WkSerdeDtreeBytestreamInputBase;
import weliyek.serialization.WkSerdeDtreeMsgInputField;
import weliyek.serialization.WkSerdeDtreeMsgInputFieldCore;
import weliyek.serialization.WkSerdeDtreeOperationInputRuntimeSequenceCommon;
import weliyek.serialization.WkSerdeDtreeOperationResult;
import weliyek.serialization.WkSerdeDtreeOperationSettings;
import weliyek.serialization.sequence.WkSerdeUtilsPrimitiveArray;

public class WkSerdeDtreeFixedSizeByteArrayReader
    implements WkSerdeDtreeByteArrayReader<
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeOperationInputRuntimeSequenceCommon<WkSerdeDtreeBytestreamInput>,
                        WkSerdeDtreeOperationResult<WkByteArray>,
                        WkSerdeDtreeFixedSizeByteArray>,
               WkSerdeDtreeGenericFixedSizePrimitiveArrayReader<
                        WkByteArray,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeOperationInputRuntimeSequenceCommon<WkSerdeDtreeBytestreamInput>,
                        WkSerdeDtreeOperationResult<WkByteArray>,
                        WkSerdeDtreeFixedSizeByteArray>
{

  final WkSerdeDtreeGenericPrimitiveArrayReaderCoreSimplified<
                        WkByteArray,
                        WkSerdeDtreeOperationSettings,
                        WkSerdeDtreeFixedSizeByteArray,
                        WkSerdeDtreeFixedSizeByteArrayReader> operationCore;

  WkSerdeDtreeFixedSizeByteArrayReader(
    int index,
    WkSerdeDtreeOperationSettings settings,
    WkSerdeDtreeBytestreamInputBase<?> parentBytestream,
    WkSerdeDtreeMsgInputFieldCore<?,?,?,?,?,?,?,?> msgFieldCore,
    WkSerdeDtreeGenericPrimitiveArrayDefinitionCoreSimplified<
      WkByteArray,WkSerdeDtreeOperationSettings,WkSerdeDtreeFixedSizeByteArrayReader,?,?,WkSerdeDtreeFixedSizeByteArray> definitionCore) {
    this.operationCore = new WkSerdeDtreeGenericPrimitiveArrayReaderCoreSimplified<
                                WkByteArray,
                                WkSerdeDtreeOperationSettings,
                                WkSerdeDtreeFixedSizeByteArray,
                                WkSerdeDtreeFixedSizeByteArrayReader>(
                                    index,
                                    settings,
                                    parentBytestream,
                                    msgFieldCore,
                                    definitionCore,
                                    this,
                                    WkSerdeUtilsPrimitiveArray::onFixedSizeDeserilizingInitialization);
  }

  @Override
  public final List<WkSerdeDtreeMsgInputField<?,?,?>> subfields() {
    return this.operationCore.subfields();
  }

  @Override
  public WkSerdeDtreeFixedSizeByteArray definition() {
    return this.operationCore.definition();
  }

  @Override
  public WkSerdeDtreeOperationSettings settings() {
    return this.operationCore.settings();
  }

  @Override
  public WkSerdeDtreeOperationInputRuntimeSequenceCommon<WkSerdeDtreeBytestreamInput> dashboard() {
    return this.operationCore.dashboard();
  }

  @Override
  public Optional<WkSerdeDtreeOperationResult<WkByteArray>> result() {
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
  public String name() {
    return this.operationCore.name();
  }

  @Override
  public int getRequestedLength() {
    return this.operationCore.getRequestedLength();
  }

}
